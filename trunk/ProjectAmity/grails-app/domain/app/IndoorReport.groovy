package app

class IndoorReport {

    static constraints = {
    }
    Date datePosted
    String image
    String title
    String description    
    String status
    String moderationStatus
    
    static belongsTo = [ resident : Resident, building:Building ]

}
