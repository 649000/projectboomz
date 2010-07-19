package app

class ResidentController {

    def index = { }

    def mLogin =
    {

        def resident = Resident.findByNric(params.nric)

        if(resident != null)
        {
            if(resident.password == params.password)
            {
                render "T"
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
