package app

class BarterRequest {

    static constraints = {
        requestDatePosted(nullable:false)
        requestMessage(nullable:true)
        requestState(nullable:true)
    }

    Date requestDatePosted
    String requestMessage
    String requestState

    static hasMany = [ barterAds : BarterAd]

}
