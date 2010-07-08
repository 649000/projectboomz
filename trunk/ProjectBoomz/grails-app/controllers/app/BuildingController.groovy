package app

import grails.converters.JSON

class BuildingController
{

    def LibraryService
    def CinemaService
    def PlacesOfInterestService
    def counter=0

    def index = {
        println("Start of Index Action")

        redirect(action:"loadData")
    }

    def loadData =
    {
        println("Start of loadData Action")

        if(counter==0)
        {
            //Loading for the first time.
            //Removal of previous data in DB is ommited for now *Note*


            for(Building b: LibraryService.getLibraries())
            {
               
                b.save(insert:true)
                 println(b.buildingName)

            }

            //Upon successfully loading of data, counter++;
        }
                
        
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
