// Maps API JS
function load(response)
{
  if (GBrowserIsCompatible())
  {
    var map = new GMap2(document.getElementById("map"));
    map.setCenter(new GLatLng(1.300931,103.838503), 16);
    map.addControl(new GLargeMapControl3D());
    map.addControl(new GMapTypeControl());

    addPins(map, response)
  }
}

function addPins(map, response)
{
    var results = eval( '(' + response.responseText + ')')

    for (var i = 0; i < results.length; i++)
    {
        var marker = new GMarker(   new GLatLng( results[i].latitude, results[i].longitude )   )
        marker.bindInfoWindowHtml( "<font face=\"Arial\" size=\"2em\"><b>" + results[i].buildingName + "</b><br/>" +  +
                                    "<br/><br/><b>Type:</b><br/>" + results[i].type +
                                    "<br/><br/><b>Noise Level:</b><br/>" + results[i].noiseLevel + "</font>")
        map.addOverlay(marker)
    }
    
}