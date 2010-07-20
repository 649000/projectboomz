<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link rel="stylesheet" href="${resource(dir:'css',file:'layout.css')}" />
                <link rel="stylesheet" href="${resource(dir:'css',file:'style.css')}" />
<g:javascript library="scriptaculous" />
<script  type="text/javascript">
  var youritemsArray=new Array()
  var peopleitemsArray=new Array()

function hideStuff()
{
  $('youritems').hide()
  $('peopleitems').hide()
}

function showYourItems()
{
  $('youritems').show()
  $('peopleitems').hide()
}
function showPeopleItems()
{
  $('youritems').hide()
  $('peopleitems').show()
}
function movePeopleItem(divID, barterID)
{
  var asdf=$(divID)
  $(divID).remove()
  $('tradepeopleitems').insert(asdf)
  peopleitemsArray.push(barterID)
}
function moveYourItem(divID, barterID)
{
  var asdf=$(divID)
  $(divID).remove()
  $('tradeyouritems').insert(asdf)
  youritemsArray.push(barterID)
}
function moveYourItemBack(divID)
{
  var asdf=$(divID)
  $(divID).remove()
  $('youritems').insert(asdf)
  peopleitemsArray.push(barterID)
}
function confirmTrade()
{
alert('Your items: '+ youritemsArray)
alert('To trade: '+ peopleitemsArray)
var allTrades=youritemsArray+','+peopleitemsArray
alert(allTrade)
${remoteFunction(controller:"barterAd", action:"startAssign", params:"'toAssign='+allTrades")}
}
function confirmCancel()
{
}
function searchItems()
{
  
}
</script>
<style type="text/css">

</style>
</head>

<body class="thrColFixHdr" onload="hideStuff()">

		<div class="wrapper">

			<div id="container">
            <img src="${resource(dir:'images/amity',file:'logo3.PNG')}" id="logo"/>
            <img src="${resource(dir:'images/amity',file:'header.png')}" id="headerIMG"/>
            <img src="${resource(dir:'images/amity',file:'bg.jpg')}" id="background"/>
            <img src="${resource(dir:'images/amity',file:'home.png')}" id="home"/>
            <a href="${createLink(controller: 'report', action:'index')}" >
            <img src="${resource(dir:'images/amity',file:'report.png')}" border="0" id="report"/></a>
            <a href="${createLink(controller: 'carpoolListing', action:'index')}" >
            <img src="${resource(dir:'images/amity',file:'carpool.png')}" border="0" id="carpool"/></a>
            <a href="${createLink(controller: 'barterAd', action:'index')}" >
            <img src="${resource(dir:'images/amity',file:'barter.png')}" border="0" id="barter"/></a>
            <img src="${resource(dir:'images/amity',file:'bbarter.png')}" border="0" id="pageTitle"/>
  <div id="header">
    <h1>test</h1>
  <!-- end #header --></div>
  <div id="banner">&nbsp;</div>
  <div id="navi">Welcome <a href="asdf">Lim Yuan Jie</a>
    <span id="navi2"><a href="asdf"><img src="${resource(dir:'images/amity',file:'mail.png')}" border="0"/><span style="vertical-align:top;" >Message</span></a><a href="asdf"><img src="${resource(dir:'images/amity',file:'logout.png')}" border="0"/><span style="vertical-align:top;" >Logout</span></a></span>
  </div>
  <div id="mainContent">

  <!--CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE  -->
<table width="600" height="570" border="1" align="center">
  <tr>
    <td width="50" rowspan="4">&nbsp;<g:actionSubmit value="Your items" onclick="showYourItems()"/><g:actionSubmit value="Browse items" onclick="showPeopleItems()"/></td>
    <td height="30" colspan="2">Search: <g:textField name="search"/><g:actionSubmit value="Search" onclick="searchItems()"/></td>
  </tr>
  <tr>
    <td colspan="2">
      
      <div id="youritems">Your items:<br/>
        <table border="1" width="530">
          <tr height="280"><td  width="270"><g:each in="${barters[0]}" var="barter">
  <div id="MEM_${barter.id}" style="width:200px; height:150px; cursor:pointer; background:#88da5d; border:1px solid #333;" class="draggable">
    Name : ${barter.itemName}<br/>
    Description : ${barter.itemDescription}<br/>
    Category : ${barter.itemCategory}<br/>
    Date posted : <g:formatDate date="${barter.itemDatePosted}" format="dd MMM yyyy"/><br/>
    <g:actionSubmit value="Add item" onclick="moveYourItem('MEM_${barter.id}', '${barter.id}')"/>
          <g:actionSubmit value="Remove item" onclick="moveYourItemBack('MEM_${barter.id}')"/>
  </div>

</g:each></td><td>&nbsp;</td></tr>
        </table>

      </div>
      
      <div id="peopleitems">Your items:<br/>
        <table border="1" width="530">
          <tr height="280"><td  width="270"><g:each in="${barters[1]}" var="barter">
  <div id="MEM_${barter.id}" style="width:200px; height:150px; cursor:pointer; background:#88da5d; border:1px solid #333;" class="draggable">
    Name : ${barter.itemName}<br/>
    Description : ${barter.itemDescription}<br/>
    Category : ${barter.itemCategory}<br/>
    Date posted : <g:formatDate date="${barter.itemDatePosted}" format="dd MMM yyyy"/><br/>
    <g:actionSubmit value="Add item" onclick="movePeopleItem('MEM_${barter.id}', '${barter.id}')"/>
          <g:actionSubmit value="Remove item" onclick="moveItemBack('MEM_${barter.id}')"/>
  </div>

</g:each></td><td>&nbsp;</td></tr>
        </table>

      </div>

      

  </tr>
  <tr>
    <td height="200"><div id="tradepeopleitems">
        OTHER PEOPLE ITEMS:


      </div></td>
    <td><div id="tradeyouritems">
        YOUR ITEMS HERE:


      </div></td>
  </tr>
  <tr>
    <td height="30">&nbsp;</td>
    <td><g:actionSubmit value="Trade" onclick="confirmTrade()"/>
    <g:actionSubmit value="Cancel" onclick="confirmCancel()"/></td>
  </tr>
</table>

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