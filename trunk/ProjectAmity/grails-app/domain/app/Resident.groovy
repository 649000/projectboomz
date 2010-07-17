package app

class Resident {


    String nric
    String name
    String address
    String postalCode
    String userid
    String password
    static hasMany = [ barterAds : BarterAd, report: Report, indoorReport: IndoorReport, sentMessages : Message, receivedMessages : Message ]
    static hasOne = [ carpoolListing : CarpoolListing ]
    static mappedBy = [ sentMessages : 'sender', receivedMessages : 'receiver' ]

    static constraints = {
    }

}
