package app

class Resident {

    static constraints = {
    }
    String nric
    String name
    String address
    String userid
    String password
    static hasMany = [ passengers : Resident, cabSharers : Resident, barterAds : BarterAd  ]
    static belongsTo = [ driver : Resident ]

}
