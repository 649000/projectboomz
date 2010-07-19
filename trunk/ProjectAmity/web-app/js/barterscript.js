function barterlistData(response)
{
    var results = eval( '(' + response.responseText + ')')

    var html="<table><tr><td>Item Date</td><td>Name</td><td>Photo</td><td>Descrition</td><td>Category</td></tr>"
    for(var i=0 ; i < results.length ; i++)
    {
        html+="<tr><td>"+results[i].itemDatePosted+"</td><td>"+results[i].itemName+"</td><td>"+results[i].itemPhoto+"</td><td>"+results[i].itemDescription+"</td><td>"+results[i].itemCategory+"</td></tr>"
    }

    html+="</table>"    

    $('divBarterResults').innerHTML = html
}

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