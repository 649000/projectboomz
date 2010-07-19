<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sample title</title>
            <g:javascript library="scriptaculous" />
        <g:javascript library="prototype" />
        <script type="text/javascript" src="${resource(dir: 'js', file: 'barterscript.js')}" ></script>
  </head>
  <body>
    <h1>Sample line</h1>
    <g:form>
        Search: <g:textField name="search"></g:textField><br/>
        <g:submitToRemote value="Search" url="[controller: 'barterAd', action: 'startSearch']"
                            onSuccess="barterlistData(e)"/>
    </g:form>

  <div id="divBarterResults"/>
  </body>
</html>
