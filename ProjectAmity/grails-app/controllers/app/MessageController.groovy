package app

import java.text.SimpleDateFormat
import java.util.Calendar

class MessageController
{

    def messageCheckingService

    // Load the user's inbox
    def index =
    {
        session.user = Resident.get(7)
        def currentUser = session.user

        params.max = 5
        def inboxMessages = Message.createCriteria().list(params)
        {
            and
            {
                eq("receiver", currentUser)
            }
            order("timeStamp", "desc")
        }

        println(inboxMessages.totalCount)
        params.totalResults = inboxMessages.totalCount
        params.unreadCount = messageCheckingService.getUnreadMessages(currentUser)
        [inboxMessages : inboxMessages, params : params]
    }

    // Get the number of unread messages
    def int getUnreadMessages(Resident currentUser)
    {
        def unread = Message.createCriteria().list()
        {
            and
            {
                eq("isRead", false)
                eq("receiver", currentUser)
            }
        }
        return unread.size()
    }

    // load the list of sent messages
    def sent =
    {
        session.user = Resident.get(7)
        def currentUser = session.user

        params.max = 5
        def sentMessages = Message.createCriteria().list(params)
        {
            and
            {
                eq("sender", currentUser)
            }
            order("timeStamp", "desc")
        }

        println(sentMessages.totalCount)
        params.totalResults = sentMessages.totalCount
        [sentMessages : sentMessages, params : params]
    }

    // view a message
    def view =
    {
        session.user = Resident.get(7)
        def currentUser = session.user

        def messageToView = Message.findById(params.id)

        if(messageToView == null)
        {
            flash.messageOperationStatus = '<br/><p>The message you are trying to view does not exist!</p>'
            redirect(action: 'index')
        }
        else if( currentUser != messageToView.sender && currentUser != messageToView.receiver )
        {
            flash.messageOperationStatus = '<br/><p>You are not authorised to view this message!</p>'
            redirect(action: 'index')
        }
        else if( currentUser != messageToView.sender )
        {
            messageToView.isRead = true
            [message : messageToView, params : params]
        }
        else
        {
            [message : messageToView, params : params]
        }
    }

    // closure to call before create.gsp is loaded.
    def create =
    {
        def currentUser = Resident.get(7)
        session.user = currentUser

        if(params.receiverUserID == null)
        {
            //params.receiverUserID = "Enter the recipient's User ID."
        }

        [params : params]
    }

    // action that is triggered once user chooses to send a message
    def send =
    {
        def errors = ''
        def recipient
        def subject
        def message

        // Check if recipient exists
        recipient = params.receiverUserID
        if( Resident.findByUserid(recipient) == null )
        {
            errors += '<li>A recipient by the name of ' + recipient + ' does not exist!<br/>Are you sure you have typed the recipient\'s User ID correctly?'
        }
        else
        {
            recipient = Resident.findByUserid(recipient)
        }

        // Check if the subject is specified.
        if( !params.subject.trim().equals("") )
        {
            subject = params.subject
        }
        else
        {
            errors += '<li>You did not specify the subject of the message.</li>'
        }

        // Check if the message is specified.
        if( !params.message.trim().equals("") )
        {
            message = params.message
        }
        else
        {
            errors += '<li>You cannot send an empty message.</li>'
        }

        if( errors.length() > 0 )
        {
            errors = '<p>Some Errors Occured!</p><ul>' + errors
            errors += '</ul>'
            params.errors = errors
            // If there are missing fields, throw back the error message.
            render(view: 'create', params : params )
        }
        else
        {
            def currentUser = session.user

            def newMessage = new Message()
            newMessage.sender = currentUser
            newMessage.receiver = recipient
            newMessage.subject = subject
            newMessage.message = message
            newMessage.timeStamp = new Date()
            newMessage.isRead = false

            if( newMessage.save() )
            {
                flash.messageOperationStatus = '<br/><p>Your message has been sent!</p>'
                redirect( action: 'index' )
            }
            else
            {
                println( newMessage.errors )
                params.errors = '<p>An error occured when sending your message. Would you like to try again?</p>'
                render(view: 'create', params : params )
            }
        }
    }

}
