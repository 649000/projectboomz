<html>

  <head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create Message</title>

    <g:javascript library="scriptaculous" />
    <g:javascript library="prototype" />

    <script type="text/javascript" src="${resource(dir: 'js', file: 'messageScripts.js')}" ></script>

  </head>

  <body onload="initialiseCreateMessagePage()">

    <h1>Create a Message</h1>

    <div id="sendMessageStatus">${params.errors}</div>

    <p>Hi, ${session.user.name}. Time to compose messages eh?</p>

    <g:form>
      <p>To: <g:textField name="receiverUserID" value="${params.receiverUserID}" size="25" onfocus="toggleControl('createMessageToHelpText')" onblur="toggleControl('createMessageToHelpText')" /><span id="createMessageToHelpText"> Enter the recipient's User ID.</span></p>
      <p>Subject: <g:textField name="subject" value="${params.subject}" size="40" maxlength="100" /></p>
      <p>Your Message: </p>
      <p><g:textArea name="message" value="${params.message}" rows="5" cols="30" maxlength="3000" /></p>
      <g:actionSubmit value="Send" action="send" />
    </g:form>

  </body>

</html>
