package app

import java.util.*
import java.text.*

class ResidentController {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss")

    def index = { }

    def mLogin =
    {

        def resident = Resident.findByNric(params.nric)

        if(resident != null)
        {
            if(resident.password == params.password)
            {
                render "T|" + sdf.format( new Date() )
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

        render resident.postalCode
        
    }
}
