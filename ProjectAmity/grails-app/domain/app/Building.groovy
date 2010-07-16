package app

class Building {

    static constraints = {
    }

    String postalCode
    double latitude
    double longitude
    double altitude
    String level
    String stairwell
     static hasMany = [ indoorReport : IndoorReport]
}
