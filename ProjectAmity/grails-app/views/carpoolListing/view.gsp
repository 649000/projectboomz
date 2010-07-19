<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>


        <title>${listing.resident.name}'s Carpool Listing</title>

        <g:javascript library="scriptaculous" />
        <g:javascript library="prototype" />

		<meta http-equiv="content-type" content="text/html; charset=utf-8" />

                <link rel="stylesheet" href="${resource(dir:'css',file:'layout.css')}" />
                <link rel="stylesheet" href="${resource(dir:'css',file:'style.css')}" />

                        <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAl3XLeSqUNe8Ev9bdkkHWFBTlogEOPz-D7BlWWD22Bqn0kvQxhBQR-
srLJJlcXUmLMTM2KkMsePdU1A"
            type="text/javascript"></script>

        <script type="text/javascript" src="${resource(dir: 'js', file: 'carpoolScripts.js')}" ></script>

	</head>

  <body  class="thrColFixHdr"  onload="loadCarpoolMap(${listing.startLatitude}, ${listing.startLongitude}, ${listing.endLatitude}, ${listing.endLongitude})" onunload="GUnload()">

		<div class="wrapper">

			<div id="container">
            <img src="${resource(dir:'images/amity',file:'logo3.PNG')}" id="logo"/>
            <img src="${resource(dir:'images/amity',file:'header.png')}" id="headerIMG">
            <img src="${resource(dir:'images/amity',file:'bg.jpg')}" id="background"/>
            <img src="${resource(dir:'images/amity',file:'home.png')}" id="home"/>
            <img src="${resource(dir:'images/amity',file:'report.png')}" id="report"/>
            <img src="${resource(dir:'images/amity',file:'carpool.png')}" id="carpool"/>
            <img src="${resource(dir:'images/amity',file:'barter.png')}" id="barter"/>
            <img src="${resource(dir:'images/amity',file:'bcarpool.png')}" id="pageTitle"/>
  <div id="header">
    <h1>test</h1>
  <!-- end #header --></div>
  <div id="banner">&nbsp;</div>
  <div id="navi">&nbsp; You are here: Testing</div>
  <div id="mainContent">

  <!--CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE  -->

    </div>
	<!-- This clearing element should immediately follow the #mainContent div in order to force the #container div to contain all child floats --><br class="clearfloat" />
<!-- end #container --></div>

			<div class="push"></div>

  

    <h1>${listing.resident.name}'s Listing</h1>

    <g:if test="${listing.status == 'Fulfilled'}">
      <p style="background-color: #ffc0cb">This user's listing has already been fulfilled.</p>
    </g:if>
      
    <h2>User Information</h2>

    <p>Name: ${listing.resident.name}</p>
    <p>Listing Status: ${listing.status}</p>
    <p>User ID: ${listing.resident.userid} <g:link controller="message" action="create" params="[receiverUserID: listing.resident.userid, subject: 'Your Carpool Listing']">Send Private Message</g:link></p>
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



<!--CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT
HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE  -->
</div>

		<div class="footer">
			<p>Copyright &copy; 2010 Team Smiley Face</p>
		</div>
	</body>
</html>