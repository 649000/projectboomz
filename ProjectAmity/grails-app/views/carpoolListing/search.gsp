<html>

    <head>

        <title>Carpool Listing Search</title>

        <g:javascript library="scriptaculous" />
        <g:javascript library="prototype" />

        <script type="text/javascript" src="${resource(dir: 'js', file: 'carpoolScripts.js')}" ></script>

    </head>

    <body>
      <resource:autoComplete skin="default" />
      <h1>Carpool Listing Search</h1>

      <div id="searchPane" style="width: 30%; border: 1px black solid; float: left">
        <p>Enter only those attributes you wish to search by.</p>

        <g:form>
          <p>Search for only those who live near you?</p>
          <g:radioGroup name="neighboursOnly" labels="['Yes', 'No']" values="['true','false']" value="${params.neighboursOnly}" >
            ${it.radio} ${it.label}
          </g:radioGroup>
          <p>Destination Location:</p>
          <richui:autoComplete name="endAddress" action="${createLinkTo('dir': 'carpoolListing/searchAJAX')}" forceSelection="true"  value="${params.endAddress}" />
          <p>Departure Time:</p>
          <p>Between <g:select name="departureTimeFrom" from="${['0000', '0100', '0200', '0300', '0400', '0500', '0600', '0700', '0800', '0900', '1000', '1100', '1200', '1300', '1400', '1500', '1600', '1700', '1800', '1900', '2000', '2100', '2200', '2300']}" value="${params.departureTimeFrom}"
            noSelection="['':'HHMM']"/> and <g:select name="departureTimeTo" from="${['0000', '0100', '0200', '0300', '0400', '0500', '0600', '0700', '0800', '0900', '1000', '1100', '1200', '1300', '1400', '1500', '1600', '1700', '1800', '1900', '2000', '2100', '2200', '2300']}" value="${params.departureTimeTo}"
            noSelection="['':'HHMM']"/></p>
          <p>Return Time:</p>
          <p>Between <g:select name="returnTimeFrom" from="${['0000', '0100', '0200', '0300', '0400', '0500', '0600', '0700', '0800', '0900', '1000', '1100', '1200', '1300', '1400', '1500', '1600', '1700', '1800', '1900', '2000', '2100', '2200', '2300']}" value="${params.returnTimeFrom}"
            noSelection="['':'HHMM']"/> and <g:select name="returnTimeTo" from="${['0000', '0100', '0200', '0300', '0400', '0500', '0600', '0700', '0800', '0900', '1000', '1100', '1200', '1300', '1400', '1500', '1600', '1700', '1800', '1900', '2000', '2100', '2200', '2300']}" value="${params.returnTimeTo}"
            noSelection="['':'HHMM']"/></p>
          <p>Frequency: <g:select name="frequency" from="${['Weekdays', 'Weekends']}" value="${params.frequency}"
            noSelection="['':'Select a Frequency']"/></p>
          <p>Looking for: <g:select name="type" from="${['Driver', 'Passenger', 'Cab Pool']}" value="${params.type}"
            noSelection="['':'Who Are You Seeking?']"/></p>
          <g:actionSubmit value="Search" action="search" />
        </g:form>

      </div>

      <div id="searchResults" style="width: 60%; border: 1px black solid; float: right">
        <g:if test="${listings}">
            <g:each in="${listings}" var="l">
              <table>
                <tr>
                  <td colspan="2">
                    <h3><g:link controller="carpoolListing" action="view" params="[id: l?.resident.id]">${l?.resident.name}</g:link></h3>
                  </td>
                </tr>
                <tr>
                  <td width="70%">
                    <p>Starting Location: ${l?.startAddress}<br/>
                    Leaving Starting Location at: ${l?.departureTime}<br/>
                    Destination Location: ${l?.endAddress}<br/>
                    Returning to Starting Location at: ${l?.returnTime}</p>
                    <p>Frequency: ${l.frequency}<br/>
                    Looking for: ${l.type}</p>
                  </td>
                  <td width="30%">
                    <img src="http://maps.google.com/maps/api/staticmap?size=200x200&markers=color:green|label:A|${l.startLatitude},${l.startLongitude}&markers=color:red|label:B|${l.endLatitude},${l.endLongitude}&path=${l.startLatitude},${l.startLongitude}|${l.endLatitude},${l.endLongitude}&sensor=false" alt="Journey Map" title="Journey Map" />
                  </td>
                </tr>
              </table>
            </g:each>

          <g:paginate total="${params.totalResults}" controller="carpoolListing" action="search" params="[ neighboursOnly : params.neighboursOnly , endAddress: params.endAddress, departureTimeFrom : params.departureTimeFrom, departureTimeTo : params.departureTimeTo, returnTimeFrom : params.returnTimeFrom, returnTimeTo : params.returnTimeTo, frequency : params.frequency, type : params.type ]" />
          <hr/>
          <p>${params.totalResults} listing(s) found.</p>
        </g:if>
        <g:if test="${params.totalResults == 0}">
          <p>No results found!</p>
        </g:if>
      </div>

    </body>

</html>