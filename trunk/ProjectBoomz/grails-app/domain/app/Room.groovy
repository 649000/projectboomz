package app

class Room {

    static belongsTo = [building : Building]
    String noiseLevel   
    String xcoordinate
    String ycoordinate
    String zcoordinate

    static constraints =
    {
    }
}
