<html>

    <head>

        <title>${listing.resident.name}'s Carpool Listing</title>

        <g:javascript library="scriptaculous" />
        <g:javascript library="prototype" />

        <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAl3XLeSqUNe8Ev9bdkkHWFBTlogEOPz-D7BlWWD22Bqn0kvQxhBQR-
srLJJlcXUmLMTM2KkMsePdU1A"
            type="text/javascript"></script>

        <script type="text/javascript" src="${resource(dir: 'js', file: 'carpoolScripts.js')}" ></script>

    </head>

  <body onload="load(${listing.startLatitude}, ${listing.startLongitude}, ${listing.endLatitude}, ${listing.endLongitude})" onunload="GUnload()">

    <h1>${listing.resident.name}'s Listing</h1>

    <g:if test="${listing.status == 'Fulfilled'}">
      <p style="background-color: #ffc0cb">This user's listing has already been fulfilled.</p>
    </g:if>
      
    <h2>User Information</h2>

    <p>Name: ${listing.resident.name}</p>
    <p>Listing Status: ${listing.status}</p>
    <p>User ID: ${listing.resident.userid} <a href="#" onClick="alert('You will get to send a PM'); return false">Send Private Message</a></p>
    <p>Looking for: ${listing.type}</p>
    <p>Frequency: ${listing.frequency}</p>

    <h2>Journey Information</h2>

    <p>Starting Location: ${listing.startAddress}</p>
    <p>Departure Time: ${listing.departureTime}</p>
    <br/>
    <p>Destination Location: ${listing.endAddress}</p>
    <p>Looking for: ${listing.returnTime}</p>
    <br/>
    <p><a href="#" onClick="toggleControl('map'); return false">Show Journey on a Map</a></p>
    <div id="map" style="width: 40%; height: 350px; border: 1px solid black"></div>
    <p><a href="#" onClick="toggleControl('directionsPane'); return false">Show Driving Directions</a></p>
    <div id="directionsPane" style="width: 40%"></div>

  </body>

</html>
