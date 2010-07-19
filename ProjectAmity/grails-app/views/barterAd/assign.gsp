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
alert(allTrades)
${remoteFunction(controller:"barterAd", action:"startAssign", params:"'toAssign='+allTrades")}
}
</script>
<style type="text/css">
body
{
	font-family:Helvetica, Arial;
	font-size:14px;
}
#youritems {
	height:310px;
	width:550px;
	background-color:#39C;
        overflow: auto;
}
#peopleitems {
	height:310px;
	width:550px;
	background-color:#3C0;
        overflow: auto;
}
#tradepeopleitems
{
  background-color:#FC0;
   overflow: auto;
   width:275px;
   height:200px;
}
#tradeyouritems
{
  background-color:#C9F;
   overflow: auto;
   width:275px;
   height:200px;
}
</style>
</head>

<body onload="hideStuff()">
<table width="600" height="570" border="1" align="center">
  <tr>
    <td width="50" rowspan="4">&nbsp;<g:actionSubmit value="Your items" onclick="showYourItems()"/><g:actionSubmit value="Browse items" onclick="showPeopleItems()"/></td>
    <td height="30" colspan="2">Search: <g:textField name="search"/></td>
  </tr>
  <tr>
    <td colspan="2">
      
      <div id="youritems">Your items:<br/>
        <table border="1" width="530">
          <tr height="280"><td  width="270"><g:each in="${barters[0]}" var="barter">
  <div id="MEM_${barter.id}" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px; width:200px; height:100px; cursor:pointer; background:#88da5d; border:1px solid #333;" class="draggable">
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
  <div id="MEM_${barter.id}" style="font-family: Arial, Helvetica, sans-serif;font-size: 12px; width:200px; height:100px; cursor:pointer; background:#88da5d; border:1px solid #333;" class="draggable">
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
    <td><g:actionSubmit value="Trade" onclick="confirmTrade()"/></td>
  </tr>
</table>
</body>
</html>
