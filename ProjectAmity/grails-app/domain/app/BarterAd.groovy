package app

class BarterAd {

    static constraints = {
        resident(nullable:false)
        barterRequest(nullable:true)
        resident(nullable:false)
        itemDatePosted(nullable:false)
        itemName(nullable:false)
        itemPhoto(nullable:true)
        itemDescription(nullable:true)
        itemCategory(nullable:true)
    }

    Date itemDatePosted
    String itemName
    String itemPhoto
    String itemDescription
    String itemCategory
static belongsTo = [ resident : Resident, barterRequest : BarterRequest ]

}
