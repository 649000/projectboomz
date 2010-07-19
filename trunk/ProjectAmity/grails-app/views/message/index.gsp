<%@ page contentType="text/html;charset=UTF-8" %>

<html>

  <head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Messages - Inbox</title>

  </head>

  <body>

    <h1>View Your Inbox Messages</h1>

    <g:if test="${params.unreadCount > 0}">
      <p>You have ${params.unreadCount} unread messages.</p>
    </g:if>

    <div id="messageOperationStatus">${flash.messageOperationStatus}</div>

    <h2><a href="${createLink(controller: 'message', action:'index')}">Inbox</a> | <a href="${createLink(controller: 'message', action:'sent')}">Sent Messages</a></h2>

    <g:if test="${inboxMessages}">
      <table border="1">

        <tr>
          <td>From</td>
          <td>Subject</td>
          <td>Time Sent</td>
        </tr>

        <g:each in="${inboxMessages}" var="m">
            <tr>
              <td>${m?.sender.name} (<g:link action="create" params="[receiverUserID: m?.sender.userid]">${m?.sender.userid}</g:link>)</td>
              <g:if test="${m?.isRead}">
                <td><g:link action="view" id="${m.id}">${m?.subject}</g:link></td>
              </g:if>
              <g:else>
                <td><b><g:link action="view" id="${m.id}">${m?.subject}</g:link></b></td>
              </g:else>
              <td>${m?.timeStamp}</td>
            </tr>
        </g:each>

      </table>
      <g:paginate total="${params.totalResults}" controller="message" action="index" />
    </g:if>

  </body>

</html>
