package app

class BarterAd {

    static constraints = {
    }

    String datePosted
    String itemName
    String photo
    String description
static belongsTo = [ resident : Resident ]

}
