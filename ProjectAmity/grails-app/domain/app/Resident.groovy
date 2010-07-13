package app

class Resident {


    String nric
    String name
    String address
    String userid
    String password
    static hasMany = [ passengers : Resident, cabSharers : Resident, barterAds : BarterAd  ]
    static hasOne = [ driver : Resident ]

    static constraints = {
    }

}
