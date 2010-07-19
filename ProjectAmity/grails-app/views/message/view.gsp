<html>

  <head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View Message</title>

  </head>

  <body>

    <h1>${message.subject}</h1>

    <table>
      <tr>
        <td>From: ${message.sender.name} (${message.sender.userid})</td>
      </tr>
      <tr>
        <td>To: ${message.receiver.name} (${message.receiver.userid})</td>
      </tr>
      <tr>
        <td>Time Sent: ${message.timeStamp}</td>
      </tr>
      <tr>
        <td>Subject: ${message.subject}</td>
      </tr>
      <tr>
        <td>${message.message}</td>
      </tr>
    </table>

    <g:if test="${session.user.userid == message.receiver.userid}">
      <p><g:link action="create" params="[receiverUserID: message?.sender.userid, subject: 'Re: ' + message.subject , message: '\n\n\nOn ' + message.timeStamp + ', ' + message.sender.name + ' wrote: \n\n' + message.message]">Reply</g:link></p>
    </g:if>
    <g:if test="${session.user.userid == message.sender.userid}">
        <p><g:link action="create" params="[receiverUserID: message?.receiver.userid, subject: 'Re: ' + message.subject , message: '\n\n\nOn ' + message.timeStamp + ', ' + message.sender.name + ' wrote: \n\n' + message.message]">Reply</g:link></p>
    </g:if>
    
  </body>

</html>