<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>


        <title>Create Barter Advertisement</title>

        <g:javascript library="scriptaculous" />
        <g:javascript library="prototype" />

		<meta http-equiv="content-type" content="text/html; charset=utf-8" />

                <link rel="stylesheet" href="${resource(dir:'css',file:'layout.css')}" />
                <link rel="stylesheet" href="${resource(dir:'css',file:'style.css')}" />

	</head>
<body class="thrColFixHdr">

		<div class="wrapper">

			<div id="container">
            <img src="${resource(dir:'images/amity',file:'logo3.PNG')}" id="logo"/>
            <img src="${resource(dir:'images/amity',file:'header.png')}" id="headerIMG">
            <img src="${resource(dir:'images/amity',file:'bg.jpg')}" id="background"/>
            <img src="${resource(dir:'images/amity',file:'home.png')}" id="home"/>
            <img src="${resource(dir:'images/amity',file:'report.png')}" id="report"/>
            <img src="${resource(dir:'images/amity',file:'carpool.png')}" id="carpool"/>
            <img src="${resource(dir:'images/amity',file:'barter.png')}" id="barter"/>
            <img src="${resource(dir:'images/amity',file:'bcarpool.png')}" id="pageTitle"/>
  <div id="header">
    <h1>test</h1>
  <!-- end #header --></div>
  <div id="banner">&nbsp;</div>
  <div id="navi">&nbsp; You are here: Testing</div>
  <div id="mainContent">

  <!--CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE  -->

    <g:form>
        Item name: <g:textField name="itemName"></g:textField>
        <br/>Item category: <g:textField name="itemCategory"></g:textField>
        <br/>Item description: <g:textArea name="itemDescription"></g:textArea>
        <br/>Item photo: <g:textField name="itemPhoto"></g:textField>
        <br/><g:actionSubmit value="Save" action="save"/>
    </g:form>
    </div>
	<!-- This clearing element should immediately follow the #mainContent div in order to force the #container div to contain all child floats --><br class="clearfloat" />
<!-- end #container --></div>

			<div class="push"></div>


  <!--CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE  -->
</div>

		<div class="footer">
			<p>Copyright &copy; 2010 Team Smiley Face</p>
		</div>
	</body>
</html>