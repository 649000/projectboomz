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