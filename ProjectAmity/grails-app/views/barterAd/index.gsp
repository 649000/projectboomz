<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>


        <title>Barter Advertisements</title>

        <g:javascript library="scriptaculous" />
        <g:javascript library="prototype" />

		<meta http-equiv="content-type" content="text/html; charset=utf-8" />

                <link rel="stylesheet" href="${resource(dir:'css',file:'layout.css')}" />
                <link rel="stylesheet" href="${resource(dir:'css',file:'style.css')}" />


                <script type="text/javascript">
                    var youritemsArray=new Array()
  var peopleitemsArray=new Array()
  var peopleitemsResidentIDArray=new Array()
  var peopleTradeID=""
  var userID=""
                  function viewItemData(response)
                  {
                     var results = eval( '(' + response.responseText + ')' )
                     var html=""
                     html+="<b>"+results[0].itemName+"</b>"
                     html+="<p><b>Date Posted: </b>"+results[0].itemDatePosted
                     html+="<br/><b>Item Category: </b>"+results[0].itemCategory
                     html+="<br/><b>Item Description: </b>"+results[0].itemDescription
                     html+="<br/><b>Item resident: </b>"+results[1].name
                     html+="</p>"
                     $('itemStatusContainer').innerHTML=html
                  }
                  function clearData()
                  {
                    $('itemStatusContainer').innerHTML=""
                  }
                  function moveyouritem(barterDiv, barterID, residentID)
                  {
                    if($(barterDiv).parentNode.id=='itemListContainer')
                      {
                        var tempDiv=$(barterDiv)
                    $(barterDiv).remove()
                    $('tradeContainerRight').insert(tempDiv)
                    youritemsArray.push(barterID)
                    userID=residentID
                      }
                      else if($(barterDiv).parentNode.id=='tradeContainerRight')

                      {
                        var tempDiv=$(barterDiv)
                    $(barterDiv).remove()
                    $('itemListContainer').insert(tempDiv)
                      }
                    
                  }
                  function movepeopleitem(barterDiv, barterID, residentID)
                  {
                    if($(barterDiv).parentNode.id=='peopleItemListContainer')
                      {
                        if(peopleitemsResidentIDArray.length>0)
                          {
                            
                            for(var i=0; i<peopleitemsResidentIDArray.length;i++)
                              {
                                
                                if(peopleitemsResidentIDArray[i]!=residentID)
                                  {
                                    alert('Cannot trade with more than 2 different people!')
                                    break;
                                  }
                                  else
                                    {
                                      
                                      var tempDiv=$(barterDiv)
                    $(barterDiv).remove()
                    $('tradeContainerLeft').insert(tempDiv)
                    peopleitemsArray.push(barterID)
                    peopleitemsResidentIDArray.push(residentID)
                    break;
                                    }
                              }
                          }

                          else
                            {
 
                                                      var tempDiv=$(barterDiv)
                    $(barterDiv).remove()
                    $('tradeContainerLeft').insert(tempDiv)
                    peopleitemsArray.push(barterID)
                    peopleitemsResidentIDArray.push(residentID)
                    peopleTradeID=residentID
                            }
                            
                            

                      }
                      else if($(barterDiv).parentNode.id=='tradeContainerLeft')
                      {
                        var tempDiv=$(barterDiv)
                    $(barterDiv).remove()
                    $('peopleItemListContainer').insert(tempDiv)
                      }

                  }
                  function hideContainers(){
                    $('itemListContainer').show()
                    $('peopleItemListContainer').hide()
                    $('addItemContainer').hide()
                    $('checkRequestsContainer').hide()
                  }
                  function showBrowseItem()
                  {
                    $('itemListContainer').hide()
                    $('peopleItemListContainer').show()
                    $('addItemContainer').hide()
                    $('checkRequestsContainer').hide()
                  }
                  function showAddItem()
                  {
                    $('itemListContainer').hide()
                    $('peopleItemListContainer').hide()
                    $('addItemContainer').show()
                    $('checkRequestsContainer').hide()
                  }
                  function showInventory()
                  {
                    $('itemListContainer').show()
                    $('peopleItemListContainer').hide()
                    $('addItemContainer').hide()
                    $('checkRequestsContainer').hide()
                  }
                  function viewRequests(response)
                  {
                    $('itemListContainer').hide()
                    $('peopleItemListContainer').hide()
                    $('addItemContainer').hide()
                    $('checkRequestsContainer').show()

                    var html=""

                    var results = eval( '(' + response.responseText + ')' )
                    //first array list to display each message
                    for(var i=0; i<results[0].length; i++)
                      {
                        html+='<b>'+results[1][i].userid +'</b> wants to trade with you, <b>'+results[0][i].userid + '</b><br/>'
                        html+='<br/>'
                        html+="Your items to trade:<br/>"
                        for(var j=0; j<results[2][i].length; j++)
                          {
                            html+='<img src=\"../images/database/'+results[2][i][j].itemPhoto+'\"/>'
                          }
                          html+="<br/><br/>Items you want to trade with:<br/>"
                        for(var j=0; j<results[3][i].length; j++)
                          {
                            html+='<img src=\"../images/database/'+results[3][i][j].itemPhoto+'\"/>'
                          }
                        
                        html+='<br/><br/><hr/>'
                      }

                    $('checkRequestsContainer').innerHTML=html
                  }
                function confirmTrade()
{
//alert('Your items: '+ youritemsArray)
//alert('To trade: '+ peopleitemsArray)
var allTrades=youritemsArray+'/'+peopleitemsArray+'/'+userID+'/'+peopleTradeID
alert(allTrades)
${remoteFunction(action:'startAssign', params:'\'data=\'+allTrades')}

//    youritemsArray=[]
//    peopleitemsArray=[]
//    `var allTrades=youritemsArray+','+peopleitemsArray
//alert(allTrades)
}
                  </script>

	</head>
  <body class="thrColFixHdr" onload="hideContainers()" >

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
  <div id="navi">Welcome, <a href="#">${session.user.name}</a>.&nbsp;
    <g:if test="${params.messageModuleUnreadMessages > 1}">
      You have <a href="${createLink(controller: 'message', action:'index')}">${params.messageModuleUnreadMessages} unread messages</a>.
    </g:if>
    <g:elseif test="${params.messageModuleUnreadMessages == 1}">
      You have <a href="${createLink(controller: 'message', action:'index')}">1 unread message</a>.
    </g:elseif>
    <span id="navi2"><a href="${createLink(controller: 'message', action:'index')}"><img src="${resource(dir:'images/amity',file:'mail.png')}" border="0"/><span style="vertical-align:top;" >Message</span></a><a href="asdf"><img src="${resource(dir:'images/amity',file:'logout.png')}" border="0"/><span style="vertical-align:top;" >Logout</span></a></span>
  </div>
  <div id="mainContent">

  <!--CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE  -->


<div id="mainItemContainer">

<div id="listHeader">
  <span style="cursor: pointer; color: #06a0f0; text-decoration:none;" onclick="showBrowseItem()" >[Browse Items]</span>
  <span style="cursor: pointer; color: #06a0f0; text-decoration:none;" onclick="showAddItem()">[ [Add Item]</span>
  <span style="cursor: pointer; color: #06a0f0; text-decoration:none;" onclick="showInventory()">[ [Inventory]</span>
 
</div>

  <div id="itemListContainer">
<g:each in="${barters[0]}" var="barter">
  <div class="box" id="BART_${barter.id}">

    <div style="width: 100px;height: 100px;z-index:1;background-image:url(../images/database/${barter.itemPhoto});background-repeat:no-repeat;">
		<div onmouseout="clearData()" onclick="moveyouritem('BART_${barter.id}','${barter.id}','${barter.resident.id}')" onmouseover="${remoteFunction(onSuccess:'viewItemData(e)', action:'viewItem', params:'\''+'barter=' + barter.id+'\'')}"  id="top" style="width:56px;height:100px;z-index:3;background-image:url(../images/amity/linkclick.png);background-repeat:no-repeat;float: left;"></div>
                <div onmouseout="clearData()" onclick="" onmouseover="${remoteFunction(onSuccess:'viewItemData(e)', action:'viewItem', params:'\''+'barter=' + barter.id+'\'')}"  id="top" style="width:22px;height:22px;z-index:4;background-image:url(../images/amity/delete.png);background-repeat:no-repeat; float: right"></div>
                <div onmouseout="clearData()" onclick="" onmouseover="${remoteFunction(onSuccess:'viewItemData(e)', action:'viewItem', params:'\''+'barter=' + barter.id+'\'')}"  id="top" style="width:22px;height:22px;z-index:4;background-image:url(../images/amity/edit.png);background-repeat:no-repeat; float: right"></div>
                <div onmouseout="clearData()" onclick="moveyouritem('${barter.id}')" onmouseover="${remoteFunction(onSuccess:'viewItemData(e)', action:'viewItem', params:'\''+'barter=' + barter.id+'\'')}"  id="top" style="width:44px;height:72px;z-index:3;background-image:url(../images/amity/linkclick.png);background-repeat:no-repeat;float: right;"></div>
</div>
  </div>
  </g:each>
</div>

<div id="peopleItemListContainer" onmouseover="clearData()">
<g:each in="${barters[1]}" var="barter">
  <div class="box" id="BART_${barter.id}">
    <div id="bottom" style="width: 100px;height: 100px;z-index:1;background-image:url(../images/database/${barter.itemPhoto});background-repeat:no-repeat; border:1px solid black">
		<div  onmouseout="clearData()" onclick="movepeopleitem('BART_${barter.id}','${barter.id}','${barter.resident.id}')" onmouseover="${remoteFunction(onSuccess:'viewItemData(e)', action:'viewItem', params:'\''+'barter=' + barter.id+'\'')}"  id="top" style="width:100px;height:100px;z-index:3;background-image:url(../images/amity/linkclick.png);background-repeat:no-repeat;"></div>
</div>
  </div>
  </g:each>
</div>

  <div id="addItemContainer" onmouseover="clearData()">
    <g:form>
      <table width="300" border="0">
  <tr>
    <td width="150">&nbsp;Item name:</td>
    <td>&nbsp;<g:textField name="itemName"></g:textField></td>
  </tr>
  <tr>
    <td>&nbsp;Item category:</td>
    <td>&nbsp;<g:textField name="itemCategory"></g:textField></td>
  </tr>
  <tr>
    <td>&nbsp;Item description:</td>
    <td>&nbsp;<g:textArea name="itemDescription"></g:textArea></td>
  </tr>
  <tr>
    <td>&nbsp;Item photo:</td>
    <td>&nbsp;<g:textField name="itemPhoto"></g:textField></td>
  </tr>
</table>
        <br/><g:actionSubmit value="Save" action="save"/>
    </g:form>
</div>

    <div id="checkRequestsContainer" onmouseover="clearData()">
</div>

<div id="itemStatusContainer"></div>
<div id="tradeHeader"> Trade</div>

<div id="tradeContainerLeft"></div>

<div id="tradeContainerRight"></div>

<div id="tradeButton">
<g:actionSubmit value="Trade" onclick="confirmTrade()" />
<g:actionSubmit value="Cancel"  />
</div>


</div>




  <!--CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE  -->
    </div>
	<!-- This clearing element should immediately follow the #mainContent div in order to force the #container div to contain all child floats --><br class="clearfloat" />
<!-- end #container --></div>

			<div class="push"></div>

		</div>

		<div class="footer">
			<p>Copyright &copy; 2010 Team Smiley Face</p>
		</div>
	</body>
</html>