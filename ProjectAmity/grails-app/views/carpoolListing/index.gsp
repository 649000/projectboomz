<html>

    <head>

        <title>Define Your Carpool Listing</title>

        <g:javascript library="scriptaculous" />
        <g:javascript library="prototype" />

        <script type="text/javascript" src="${resource(dir: 'js', file: 'carpoolScripts.js')}" ></script>

    </head>

    <body onload="initialisePage(); load()" onunload="GUnload()">
      <resource:autoComplete skin="default" />
      <h1>Welcome, ${session.user.name}</h1>

      <div id="saving"><img src="${resource(dir:'images', file:'spinner.gif')}" alt="Loading" /> Saving...</div>
      <div id="defineListingStatus"></div>

      <p>Define your carpool listing here so that people can search for you.</p>

      <g:form>
        <h2>Departure Information</h2>
        <p>Starting Location: ${listing.startAddress}</p>
        <p>Departure Time: <g:select name="departureTimeHour" from="${['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23']}" value="${params.departureTimeHour}"
          noSelection="['':'HH']"/> : <g:select name="departureTimeMinute" from="${['00', '10', '20', '30', '40', '50']}" value="${params.departureTimeMinute}"
          noSelection="['':'MM']"/></p>
        <h2>Return Information</h2>
        <p>Destination Location:  ${listing.endAddress}</p>
        <p><a href="#" onClick="toggleControl('destinationAutoComplete'); return false">Change</a></p>
        <span id="destinationAutoComplete">New Destination Location: <richui:autoComplete name="endAddress" action="${createLinkTo('dir': 'carpoolListing/searchAJAX')}" forceSelection="true"  value="${listing.endAddress}" /></span>
        <p>Return Time: <g:select name="returnTimeHour" from="${['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23']}" value="${params.returnTimeHour}"
          noSelection="['':'HH']"/> : <g:select name="returnTimeMinute" from="${['00', '10', '20', '30', '40', '50']}" value="${params.returnTimeMinute}"
          noSelection="['':'MM']"/></p>
        <h2>Miscellaneous Details</h2>
        <p>Frequency: <g:select name="frequency" from="${['Weekdays', 'Weekends']}" value="${listing.frequency}"
          noSelection="['':'Select a Frequency']"/></p>
        <p>Looking for: <g:select name="type" from="${['Driver', 'Passenger', 'Cab Pool']}" value="${listing.type}"
          noSelection="['':'Who Are You Seeking?']"/></p>
        <g:submitToRemote value="Update Listing"
                          url="[controller: 'carpoolListing', action:'save']"
                          onSuccess="updateSaveStatus(e)"
                          onLoading="prepareSave(); toggleControl('saving')"
                          onComplete="toggleControl('saving')" />
      </g:form>

    </body>

</html>
