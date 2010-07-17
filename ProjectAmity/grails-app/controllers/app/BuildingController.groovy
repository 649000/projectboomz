package app

import grails.converters.JSON

class BuildingController {

        def index = {

        //User will be redirected to this index closure

        //This controller will retrieve the building's info based on the postal code
        println("Params Received (Postal COde): " + params.postalCode)
        session.postalCode = params.postalCode
    }

    def loadBuilding =
    {

        //Retrieve building info based on postal code
       // println(session.postalCode)
        def _building = Building.findByPostalCode(session.postalCode)

        //Store all the indoor reports 
        def reportList = new ArrayList()

        for(Building b: _building)
        {
            def r = IndoorReport.findAllByBuilding(b)
            reportList.add(r)
        }


       // def list =[_building, room]
        render reportList as JSON


    }

}
