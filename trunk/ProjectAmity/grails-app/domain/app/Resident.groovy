package app

class Resident {


    String nric
    String name
    String address
    String userid
    String password
    static hasMany = [ passengers : Resident, cabSharers : Resident, barterAds : BarterAd, report: Report ]
    static hasOne = [ driver : Resident ]

    static constraints = {
    }

}
