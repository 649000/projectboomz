//google.load("maps", "2");
var map=null;
var markers;
var reportObj = null;
var outdoorReport = null;
var indoorReport = null;
var toGeoCode=null;



function Init(response) {

    reportObj = eval( '(' + response.responseText + ')')
    outdoorReport = reportObj[0];
    indoorReport = reportObj[1];

    var centerPoint = new GLatLng(1.354625, 103.816681);
    var zoom = 11;


    if (GBrowserIsCompatible()==true)
    {

        map = new GMap2(document.getElementById("map"))
        map.enableContinuousZoom();
        map.enableScrollWheelZoom();
        map.setMapType(G_HYBRID_MAP);
        map.setCenter(centerPoint, zoom);
        map.addControl(new GLargeMapControl());
        map.addControl(new GMapTypeControl());

        loadMarkers()
    }

}


function loadMarkers()
{
    //Traverse outdoorReport and print out all the markers

    for (var i=0;i<outdoorReport.size();i++)
    {
        var coordinates = new GLatLng(outdoorReport[i].latitude, outdoorReport[i].longitude);
        var marker = new GMarker(coordinates);
        marker.bindInfoWindowHtml("<b>" + outdoorReport[i].title + "</b></br>" + outdoorReport[i].image + "</br>" + outdoorReport[i].description + "</br> Status: " + outdoorReport[i].status );
        map.addOverlay(marker);
    }

    // println("Postal Code : " + confusingList[i][0] + "Amount of Reports: " + confusingList[i][1]

    var geocode = new GClientGeocoder();
    geocode.setBaseCountryCode("SG");
   
    for (var k=0; k<indoorReport.size(); k++)
    {
         
       //  alert(""+indoorReport[k][0]+" "+indoorReport[k][1]+" "+indoorReport[k][2]+" "+indoorReport[k][3])


          var _coordinates = new GLatLng(indoorReport[k][2], indoorReport[k][3])
          var _marker = new GMarker(_coordinates)

          _marker.bindInfoWindowHtml("<b> Postal Code: " + indoorReport[k][0] + "</b></br> Indoor Reports </br>" +indoorReport[k][1] + " case(s) has been reported. </br> <a href=\"building/index?postalCode="+ indoorReport[k][0] +"\">View</a> ")
           map.addOverlay(_marker)
//        toGeoCode = "Singapore " + indoorReport[k][0];
//        var casesReport = indoorReport[k][1] + " cases has been reported.";
//        alert(casesReport)
//
//        geocode.getLocations(toGeoCode,function loadMarkers2(response)
//        {
//
//            if ((response == null) || (response.Status.code != G_GEO_SUCCESS))
//            {
//                alert("\"" + toGeoCode + "\" not found");
//            }
//            else
//            {
//
//                var place = response.Placemark[0];
//                var point = new GLatLng(place.Point.coordinates[1], place.Point.coordinates[0]);
//                var marker = new GMarker(point, {
//                    title: place.address
//                } );
//                map.addOverlay(marker);
//                marker.bindInfoWindowHtml(casesReport);
//
//            }
//        })
    
    }
}

//function loadMarkers2(response)
//{
//    if ((response == null) || (response.Status.code != G_GEO_SUCCESS))
//    {
//        alert("\"" + toGeoCode + "\" not found");
//    }
//    else
//    {
//        //alert("boo")
//        var place = response.Placemark[0];
//        var point = new GLatLng(place.Point.coordinates[1], place.Point.coordinates[0]);
//        // _map.setCenter(point);
//        var marker = new GMarker(point, {
//            title: place.address
//        } );
//        map.addOverlay(marker);
//        marker.bindInfoWindowHtml(casesReport);
//    }
//}

