<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sample title</title>
  </head>
  <body>
    <h1>Sample line</h1>
    <g:form>
        Item name: <g:textField name="itemName"></g:textField>
        <br/>Item category: <g:textField name="itemCategory"></g:textField>
        <br/>Item description: <g:textArea name="itemDescription"></g:textArea>
        <br/>Item photo: <g:textField name="itemPhoto"></g:textField>
        <br/><g:actionSubmit value="Save" action="save"/>
    </g:form>
  </body>
</html>
