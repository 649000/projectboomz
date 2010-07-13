package app

class Resident {


    String nric
    String name
    String address
    String userid
    String password
    static hasMany = [ barterAds : BarterAd, report: Report ]
    static hasOne = [ carpoolListing : CarpoolListing ]

    static constraints = {
    }

}
