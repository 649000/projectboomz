package app

class LibraryService {

    static transactional = true

    def serviceMethod() {

    }
    
    def ArrayList getLibraries()
    {
        def retrievedContent;
      
        def buildingList = new ArrayList<Building>()

        try {
            URL _url = new URL("https://nlb.projectnimbus.org/nlbodataservice.svc/LibrarySet");
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
                buildingz.type = "Library"
                buildingz.noiseLevel = 0
                buildingList.add(buildingz)
            }

        } catch (Exception e) {
            println(e);
        }


       // println(retrievedContent);
        return buildingList

    }
}
