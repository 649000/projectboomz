package app

class MessageMobileController
{

    def send =
    {
        def sender = params.sender
        def receiver = params.receiver
        def subject = params.subject
        def message = params.message

        def errors = ''

        // Check if recipient exists
        if( receiver == null || Resident.findByUserid(receiver) == null )
        {
            errors += 'Your recipient\'s User ID|'
        }
        else
        {
            receiver = Resident.findByUserid(receiver)
        }

        // Check if the subject is specified.
        if( subject == null || subject.trim().equals("") )
        {
            errors += 'Subject|'
        }

        // Check if the message is specified.
        if( message == null || message.trim().equals("") )
        {
            errors += 'Message|'
        }

        if( errors.length() > 0 )
        {
            errors = '@|' + errors.substring(0, (errors.length() - 1) )
            // If there are missing fields, throw back the error message.
            render errors
        }
        else if(sender != null)
        {
            def newMessage = new Message()
            newMessage.sender = Resident.findByUserid(sender)
            newMessage.receiver = receiver
            newMessage.subject = subject
            newMessage.message = message
            newMessage.timeStamp = new Date()
            newMessage.isRead = false

            if( newMessage.save() )
            {
                render 'T'
            }
            else
            {
                render 'F'
            }
        }
    }

}
