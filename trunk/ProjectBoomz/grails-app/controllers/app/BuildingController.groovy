package app

import grails.converters.JSON

class BuildingController
{

    def LibraryService
    def CinemaService
    def PlacesOfInterestService
    def CounterService
 

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
    
}
