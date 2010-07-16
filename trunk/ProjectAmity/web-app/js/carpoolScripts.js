function initialisePage()
{
    $('destinationAutoComplete').hide()
    $('saving').hide()
}

function initialiseSearchPage()
{
    $('searchLoading').hide()
}

function toggleControl(element)
{
    $(element).toggle();
}

// Maps API JS
function load(startLat, startLong, endLat, endLong)
{
    if (GBrowserIsCompatible())
    {
        // GMap2 of the route map.
        var map = new GMap2(document.getElementById("map"))
        map.setCenter(new GLatLng(1.360117, 103.829041), 11)
        map.addControl(new GLargeMapControl3D())
        map.addControl(new GMapTypeControl())

        var start = new GLatLng(startLat,startLong)
        var end = new GLatLng(endLat, endLong)
        var directionsPanel = document.getElementById("directionsPane")
        var directions = new GDirections(map, directionsPanel)
        directions.load("from: " + start + " to: " + end )
    }
}

// AJAX Save
function prepareSave()
{
    $('defineListingStatus').hide()
}

function updateSaveStatus(response)
{
    $('defineListingStatus').show()
    $('defineListingStatus').innerHTML = response.responseText
}