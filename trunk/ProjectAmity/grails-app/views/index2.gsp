<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome to Project Amity!</title>
  <g:javascript library="scriptaculous" />
  <g:javascript library="prototype" />
  <script type="text/javascript" src="${resource(dir: 'js', file: 'loginscript.js')}" ></script>
</head>
<body>
  <h1>Login or Die</h1>
<g:form>
  NRIC: <g:textField name="nric" /><br/>
  Password: <g:passwordField name="password" /><br/>
  <g:submitToRemote value="Login" url="[controller:'resident', action:'checkPassword']" onSuccess="checkLogin(e)"/>
</g:form>

</body>
</html>
