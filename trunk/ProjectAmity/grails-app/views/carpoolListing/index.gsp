<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>

		
        <title>Define Your Carpool Listing</title>

        <g:javascript library="scriptaculous" />
        <g:javascript library="prototype" />

        <script type="text/javascript" src="${resource(dir: 'js', file: 'carpoolScripts.js')}" ></script>

		<meta http-equiv="content-type" content="text/html; charset=utf-8" />

                <link rel="stylesheet" href="${resource(dir:'css',file:'layout.css')}" />
                <link rel="stylesheet" href="${resource(dir:'css',file:'style.css')}" />

	</head>
<body class="thrColFixHdr"  onload="initialiseCarpoolPage()">

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

  <!--CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE CONTENT HERE  -->
  <resource:autoComplete skin="default" />
      <h1>Welcome, ${session.user.name}</h1>
      
      <div id="saving"><img src="${resource(dir:'images', file:'spinner.gif')}" alt="Loading" /> Saving...</div>
      <div id="defineListingStatus"></div>
      <br/>
      <p>Define your carpool listing here so that people can search for you.</p>

      <g:form>
        <br/>
        <h2>General Listing Information</h2>
        <br/>
        <p>Have you found your driver, someone to share a cab with or had enough passengers?
          <br/>If you do, you might want to change your listing status to Fulfilled.
          <br/>This will make your listing invisible to our listing search engine, which means that other users will not be able to search for your listing.</p>
        <br/>
        <p>Listing Status: <g:select name="status" from="${['Pending', 'Fulfilled']}" value="${listing.status}"
          noSelection="['':'Select a Status']"/></p>
        <br/>
        <h2>Departure Information</h2>
        <br/>
        <p>Starting Location: ${listing.startAddress}</p>
        <br/>
        <p>Departure Time: <g:select name="departureTimeHour" from="${['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23']}" value="${params.departureTimeHour}"
          noSelection="['':'HH']"/> : <g:select name="departureTimeMinute" from="${['00', '10', '20', '30', '40', '50']}" value="${params.departureTimeMinute}"
          noSelection="['':'MM']"/></p>
        <br/>
        <h2>Return Information</h2>
        <br/>
        <p>Destination Location:  ${listing.endAddress}</p>
        <br/>
        <p><a href="#" onClick="toggleControl('destinationAutoComplete'); return false">Change</a></p>
        <span id="destinationAutoComplete">New Destination Location: <richui:autoComplete name="endAddress" action="${createLinkTo('dir': 'carpoolListing/searchAJAX')}" forceSelection="true"  value="${listing.endAddress}" /></span>
        <br/>
        <p>Return Time: <g:select name="returnTimeHour" from="${['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23']}" value="${params.returnTimeHour}"
          noSelection="['':'HH']"/> : <g:select name="returnTimeMinute" from="${['00', '10', '20', '30', '40', '50']}" value="${params.returnTimeMinute}"
          noSelection="['':'MM']"/></p>
        <br/>
        <h2>Miscellaneous Details</h2>
        <br/>
        <p>Frequency: <g:select name="frequency" from="${['Weekdays', 'Weekends']}" value="${listing.frequency}"
          noSelection="['':'Select a Frequency']"/></p>
        <br/>
        <p>Looking for: <g:select name="type" from="${['Driver', 'Passenger', 'Cab Pool']}" value="${listing.type}"
          noSelection="['':'Who Are You Seeking?']"/></p>
        <br/>
        <g:submitToRemote value="Update Listing"
                          url="[controller: 'carpoolListing', action:'save']"
                          onSuccess="updateListingSaveStatus(e)"
                          onLoading="prepareSaveListing(); toggleControl('saving')"
                          onComplete="toggleControl('saving')" />
      </g:form>
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