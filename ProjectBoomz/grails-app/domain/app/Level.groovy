package app

class Level {

    static belongsTo = [building : Building]
    String noiseLevel

    static constraints =
    {
    }
}
