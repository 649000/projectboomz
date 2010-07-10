package app

import grails.converters.JSON

class LevelController {

    

    def index = { 
        println("Params: " + params.name)
        session.temp = params.name
    }

    def loadBuilding =
    {
        //Retrieve building info based on name
       // println(session.temp)
        def _building = Building.findByBuildingName(session.temp)
        println(_building.type)

        def level = new ArrayList()
        level = Level.findAllByBuilding(_building)
        println(level.floor)

         
        //Display building info and germanium 3D model of the building
        //render as JSON, the appropriate model based on the model
  
        def list =[_building, level]
        render list as JSON
      
        
    }
}
