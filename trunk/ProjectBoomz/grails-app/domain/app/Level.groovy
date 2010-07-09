package app

class Level {

    static belongsTo = [building : Building]
    String noiseLevel
    String floor
    String xcoordinate
    String ycoordinate
    String zcoordinate

    static constraints =
    {
    }
}
