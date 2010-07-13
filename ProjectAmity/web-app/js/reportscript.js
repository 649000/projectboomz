//google.load("maps", "2");
var map=null;
var markers;
var reportObj = null

function Init(response) {

    reportObj = eval( '(' + response.responseText + ')')

    var centerPoint = new GLatLng(1.354625, 103.816681)
    var zoom = 11


    if (GBrowserIsCompatible()==true)
    {

        map = new GMap2(document.getElementById("map"))
        map.enableContinuousZoom();
        map.enableScrollWheelZoom();
        map.setMapType(G_HYBRID_MAP);
        map.setCenter(centerPoint, zoom)
        map.addControl(new GLargeMapControl());
        map.addControl(new GMapTypeControl());

        loadMarkers()
    }

}


function loadMarkers()
{
    //Traverse reportObj and print out all the markers

    for (var i=0;i<reportObj.size();i++)
    {
        var coordinates = new GLatLng(reportObj[i].latitude, reportObj[i].longitude)
        var marker = new GMarker(coordinates)
        // alert(reportObj[i].title)
        marker.bindInfoWindowHtml("<b>" + reportObj[i].title + "</b></br>" + reportObj[i].image + "</br>" + reportObj[i].description + "</br> Status: " + reportObj[i].status )
        map.addOverlay(marker)
    }
    
}