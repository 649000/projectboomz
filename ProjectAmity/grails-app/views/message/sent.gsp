<%@ page contentType="text/html;charset=UTF-8" %>

<html>

  <head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Messages - Sent Messages</title>

  </head>

  <body>

    <h1>View Your Sent Messages</h1>

    <h2><a href="${createLink(controller: 'message', action:'index')}">Inbox</a> | <a href="${createLink(controller: 'message', action:'sent')}">Sent Messages</a></h2>

    <g:if test="${sentMessages}">
      <table border="1">

        <tr>
          <td>To</td>
          <td>Subject</td>
          <td>Time Sent</td>
        </tr>

        <g:each in="${sentMessages}" var="m">
            <tr>
              <td>${m?.receiver.name} (<g:link action="create" params="[receiverUserID: m?.receiver.userid]">${m?.receiver.userid}</g:link>)</td>
              <td><g:link action="view" id="${m.id}">${m?.subject}</g:link></td>
              <td>${m?.timeStamp}</td>
            </tr>
        </g:each>

      </table>
      <g:paginate total="${params.totalResults}" controller="message" action="sent" />
    </g:if>

  </body>

</html>