<html>

    <head>

        <title>Carpool Listing Search</title>

        <g:javascript library="scriptaculous" />
        <g:javascript library="prototype" />

        <script type="text/javascript" src="${resource(dir: 'js', file: 'carpoolScripts.js')}" ></script>

    </head>

    <body onload="initialiseSearchPage(); load()" onunload="GUnload()">
      <resource:autoComplete skin="default" />
      <h1>Carpool Listing Search</h1>

      <div id="searchPane" style="width: 30%; border: 1px black solid; float: left">
        <p>Enter only those attributes you wish to search by.</p>

        <g:form>
          <p>Search for only those who live near you?</p>
          <g:radioGroup name="neighboursOnly" labels="['Yes', 'No']" values="['true','false']" >
            ${it.radio} ${it.label}
          </g:radioGroup>
          <p>Destination Location:</p>
          <richui:autoComplete name="endAddress" action="${createLinkTo('dir': 'carpoolListing/searchAJAX')}" forceSelection="true"  value="${endAddress}" />
          <p>Departure Time:</p>
          <p>Between <g:select name="departureTimeFrom" from="${['0000', '0100', '0200', '0300', '0400', '0500', '0600', '0700', '0800', '0900', '1000', '1100', '1200', '1300', '1400', '1500', '1600', '1700', '1800', '1900', '2000', '2100', '2200', '2300']}" value="${params.departureTimeHour}"
            noSelection="['':'HHMM']"/> and <g:select name="departureTimeTo" from="${['0000', '0100', '0200', '0300', '0400', '0500', '0600', '0700', '0800', '0900', '1000', '1100', '1200', '1300', '1400', '1500', '1600', '1700', '1800', '1900', '2000', '2100', '2200', '2300']}" value="${params.departureTimeHour}"
            noSelection="['':'HHMM']"/></p>
          <p>Return Time:</p>
          <p>Between <g:select name="returnTimeFrom" from="${['0000', '0100', '0200', '0300', '0400', '0500', '0600', '0700', '0800', '0900', '1000', '1100', '1200', '1300', '1400', '1500', '1600', '1700', '1800', '1900', '2000', '2100', '2200', '2300']}" value="${params.departureTimeHour}"
            noSelection="['':'HHMM']"/> and <g:select name="returnTimeTo" from="${['0000', '0100', '0200', '0300', '0400', '0500', '0600', '0700', '0800', '0900', '1000', '1100', '1200', '1300', '1400', '1500', '1600', '1700', '1800', '1900', '2000', '2100', '2200', '2300']}" value="${params.departureTimeHour}"
            noSelection="['':'HHMM']"/></p>
          <p>Frequency: <g:select name="frequency" from="${['Weekdays', 'Weekends']}" value="${frequency}"
            noSelection="['':'Select a Frequency']"/></p>
          <p>Looking for: <g:select name="type" from="${['Driver', 'Passenger', 'Cab Pool']}" value="${type}"
            noSelection="['':'Who Are You Seeking?']"/></p>
          <g:submitToRemote value="Search"
                            url="[controller: 'carpoolListing', action:'executeSearch']"
                            onSuccess="updateSearchResults(e)"
                            onLoading="toggleControl('searchLoading')"
                            onComplete="toggleControl('searchLoading')" />
        </g:form>

      </div>

      <div id="searchLoading" style="width: 60%; border: 1px black solid; float: right"><img src="${resource(dir:'images', file:'spinner.gif')}" alt="Loading" /> Searching...</div>
      <div id="searchResults" style="width: 60%; border: 1px black solid; float: right"></div>

    </body>

</html>