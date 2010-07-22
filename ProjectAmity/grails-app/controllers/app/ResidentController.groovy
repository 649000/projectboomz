package app

import java.util.*
import java.text.*


class ResidentController {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss")

    def index = { }

    def checkPassword = {
        def toReturn="";
        def resident = Resident.findByNric(params.nric)

        if(resident !=null)
        {
            if(resident.password == params.password)
            {
                session.user = resident
                println("Login Success")               
                toReturn="Success"
            }
            else
            {
                println("Wrong Password")
                toReturn = "Invalid NRIC / Password Combination"
            }
        } else
        {
            println("Login Invalid")
            toReturn = "Invalid NRIC / Password Combination"
        }

        render toReturn
    }

    def mLogin =
    {

        def resident = Resident.findByNric(params.nric)

        if(resident != null)
        {
            if(resident.password == params.password)
            {
                render "T|" + sdf.format( new Date() )+ "|" + resident.userid
            }
            else {
                render "F"
            }
        }
        else
        {
            render "F"
        }
    }

    def mPostalCode = {
        def resident = Resident.findByUserid(params.userid)
        
        //return all the building level and stairwell.
        def level = "";
        def stairwell ="";
        def _building = Building.findAllByPostalCode(resident.postalCode)

        for(Building b: _building)
        {
            level+="|"+b.level
            stairwell+="|"+ b.stairwell
        }
        def toReturn = resident.postalCode+"~"+level+ "~" + stairwell

        render toReturn

        
    }
}
