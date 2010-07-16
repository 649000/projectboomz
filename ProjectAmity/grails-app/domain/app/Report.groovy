package app

class Report {

    static constraints = {
    }
    
    Date datePosted
    String image
    String title
    String description
    String category
    String status
    double latitude
    double longitude
    double altitude
    String moderationStatus
    
    static belongsTo = [ resident : Resident ]
}
