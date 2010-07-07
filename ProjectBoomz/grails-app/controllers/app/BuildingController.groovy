package app

 import grails.converters.JSON;

class BuildingController
{

    def index =
    {
        
        // NAZRI'S PART

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
		like("buildingName", "%" +  keyword + "%")
		like("noiseLevel", "%" +  keyword + "%")
		like("type", "%" +  keyword + "%")
	}

        println( buildings.size() )

	// Manipulate list of places. Perform filtering.

	// Render results as JSON
        render buildings as JSON

    }
    
}
