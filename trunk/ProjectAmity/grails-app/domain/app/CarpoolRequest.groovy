package app

class CarpoolRequest {

    static constraints = {
    }

    static belongsTo = [ passenger : Resident, driver : Resident, cabSharer1 : Resident, cabSharer2 : Resident ]

}
