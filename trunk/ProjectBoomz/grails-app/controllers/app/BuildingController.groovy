package app

import grails.converters.JSON
import java.text.SimpleDateFormat

class BuildingController
{

    def LibraryService
    def CinemaService
    def PlacesOfInterestService
    def CounterService

    def shareByEmailService

    def index = {
        println("Start of Index Action")

        redirect(action:"loadData")
    }

    def loadData =
    {
        println("Start of loadData Action")
        //println("CounterService: " + CounterService._Counter())

        if(CounterService._Counter()==1)
        {
           println("Start CounterService: " + CounterService.CounterValue())
            Building.executeUpdate("delete Building c where c.type != :_type", [_type:"NULL"])
            //Loading for the first time.
            
            for(Building b: LibraryService.getLibraries()){
                b.save()
            }

            for(Building b: CinemaService.getCinemas()){
                b.save()
            }

            for(Building b: PlacesOfInterestService.getPlaces()){
                b.save()
            }           
        }
        println(" End CounterService: " + CounterService.CounterValue())
                
        
    }

    def search =
    {

        System.out.println("You are here!")

        // YJ'S PART
        // Get search criteria from params
	def keyword = params.keyword

	// Create criteria and execute query
        // Search by name, building type, noise level
	def buildings = Building.createCriteria().list(params)
	{
            or
            {
		like("buildingName", "%" +  keyword + "%")
		like("noiseLevel", "%" +  keyword + "%")
		like("type", "%" +  keyword + "%")
            }
	}

        println( buildings.size() )

	// Manipulate list of places. Perform filtering.

	// Render results as JSON
        render buildings as JSON

    }

    def autoComplete={
        def buildings=Building.findAllByBuildingNameLike("%${params.query}%")

        //Create XML response
        render(contentType: "text/xml") {
	    results() {
	        buildings.each { building ->
		    result(){
		        name(building.buildingName)
                        //Optional id which will be available in onItemSelect
                        id(building.id)
		    }
		}
            }
        }
    }

    def share =
    {
        String[] recipients = new String[1]
        recipients[0] = params.friendEmail

        def content =
        "Dear " + params.friendName + ",\n\n" +
        "Your friend " + params.readerName + " recently visited Project Boomz for the latest updates on " +
        "noise pollution levels in Singapore and has highly recommended that you note the following information:\n\n" +
        "The venue " + params.buildingName + ", which is a " + params.buildingType + ", had a noise level of " +
        params.noiseLevel + " dB as of " + getDateTime() + ".\n\n\n\n\n" +
        "PROJECT BOOMZ\nPeace to the ears"

        println(content)

        boolean successfulSend = shareByEmailService.postMail(recipients, "Updates from Project Boomz", content, params.readerName.toString(), params.readerEmail.toString() );
        if (successfulSend)
        {
            render 'We have successfully sent the information you wanted to share!'
        }
        else
        {
            render 'The sending failed! You might want to try again.'
        }

    }

    def String getDateTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy hh:mm aaa");
        Date date = new Date();
        return sdf.format(date);
    }
    
}
