function initialisePage()
{
    $('share_form_header').hide()
    $('share_form_content').hide()
}

var map=null;
var markers;

// Maps API JS
function load(response)
{
  if (GBrowserIsCompatible())
  {
    map = new GMap2(document.getElementById("map"));
    map.setCenter(new GLatLng(1.358058,103.879166), 11);
    map.addControl(new GLargeMapControl3D());
    map.addControl(new GMapTypeControl());

    addPins(map, response)

    for (var i = 0; i < results.length; i++)
    {
       var HTML=( "<div class=\"right_container_content_content\"/><b>" + results[i].buildingName + "</b>" +
                                    "<br/><b>Type : </b>" + results[i].type +
                                    "<br/><b>Noise Level : </b><br/>" + results[i].noiseLevel +
                                    "</div>");
        $('right_container_content_content').innerHTML=HTML;
    }
  }
}

function addPins(map, response)
{
    var results = eval( '(' + response.responseText + ')')
    var HTML='';
    markers=[];

    for (var i = 0; i < results.length; i++)
    {
        var marker = new GMarker(   new GLatLng( results[i].latitude, results[i].longitude )   )
        marker.bindInfoWindowHtml( "<font face=\"Arial\" size=\"2em\"><b>" + results[i].buildingName + "</b>" +
                                    "<br/><br/><b>Type:</b><br/>" + results[i].type +
                                    "<br/><br/><b>Noise Level:</b><br/>" + results[i].noiseLevel +
                                    "<br/><br/><table width=\"100%\"><tr>" +
                                    "<td><b><a href=\"#\" onClick=\"prepareShare('" + results[i].buildingName + "', '" + results[i].type + "', '" + results[i].noiseLevel + "'); return false\">Share</a></b></td>" +
                                    "<td><b><a href=\"level/index?name="+ results[i].buildingName+"\">View</a></b></td></tr></table></font>");
        map.addOverlay(marker)
        HTML+="<p><b><a style=\"text-decoration:underline; cursor:pointer\" onclick=\"changePoint("+results[i].latitude+","+results[i].longitude+","+i+")\">" + results[i].buildingName + "</a></b>" +
                                    "<br/><b>Type : </b>" + results[i].type +
                                    "<br/><b>Noise Level : </b>" + results[i].noiseLevel+ "</p>";
        markers[i]=marker;
    }

    $('right_container_content_content').innerHTML=HTML;
    $('right_container_content_header').innerHTML="Search results for : \'"+ $('keyword').value+"\'";
    
}

function changePoint(latitude, longitude,i)
{
    map.setCenter(new GLatLng(latitude,longitude), 15);
    GEvent.trigger(markers[i],"click");
}

function prepareShare(name, type, noiseLevel)
{
    $('share_form_header').show()
    $('share_form_content').show()
    $('right_container_content_header').hide()
    $('right_container_content_content').hide()
    $('share_form_buildingName').innerHTML = name
    $('buildingName').value = name
    $('buildingType').value = type
    $('noiseLevel').value = noiseLevel

}

function updateShareResult(response)
{
    $('shareResult').innerHTML = response.responseText
    $('friendName').value = ''
    $('friendEmail').value = ''
    $('readerName').value = ''
    $('readerEmail').value = ''
}

function validateShareForm(thisform)
{
    with (thisform)
    {
        if( !validate_required(friendName, "Your friend's name must be filled out!") )
        {
            friendName.focus()
            return false
        }
        if( !validate_email(friendEmail, "Your friend's email address must be valid!") )
        {
            friendEmail.focus()
            return false
        }
        if( !validate_required(readerName, "Your name must be filled out!") )
        {
            readerName.focus()
            return false
        }
        if( !validate_email(readerEmail, "Your email address must be valid!") )
        {
            readerEmail.focus()
            return false
        }
    }
    $('shareResult').innerHTML = 'Sending...'
    return true
}

function validate_required(field,alerttxt)
{
    with (field)
    {
        if (value==null||value=="")
        {
            alert(alerttxt)
            return false
        }
        else
        {
            return true
        }
    }
}

function validate_email(field,alerttxt)
{
    with (field)
    {

        var emailRegex  = /^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/

        //check for valid email
        if( !emailRegex.test(value) )
        {
            alert(alerttxt)
            return false
        }
        else
        {
            return true
        }

    }
}

function changeIt()
{
    $('map').morph('left_container_shift');
    $('right_container').morph('right_container_shift');
}