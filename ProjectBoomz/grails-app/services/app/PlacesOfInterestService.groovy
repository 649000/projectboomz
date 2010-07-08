package app

class PlacesOfInterestService {

    static transactional = true

    def serviceMethod() {

    }
    def ArrayList getPlaces()
    {
        def retrievedContent;

        def buildingList = new ArrayList<Building>()

        try {
            URL _url = new URL("http://api.projectnimbus.org/stbodataservice.svc/PlaceSet");
            URLConnection _urlConn = _url.openConnection();

            _urlConn.setRequestProperty("accept", "*/*");

            _urlConn.addRequestProperty("AccountKey", "Xu584Q7QbYo+/bBO43SWmvhqdf0=");
            _urlConn.addRequestProperty("UniqueUserID", "98765432145269875154125015201452");

            retrievedContent = _urlConn.content.text

            def xml = new XmlSlurper().parseText(retrievedContent);

            xml.entry.each {
                def buildingz = new Building()
                buildingz.buildingName = it.content.properties.Name.text()
                buildingz.longitude = it.content.properties.Longitude.text()
                buildingz.latitude = it.content.properties.Latitude.text()
                buildingz.type = "Places of Interest"
                buildingList.add(buildingz)
            }

        } catch (Exception e) {
            println(e);
        }


       // println(retrievedContent);
        return buildingList

    }
}
