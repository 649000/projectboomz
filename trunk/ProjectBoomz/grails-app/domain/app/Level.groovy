package app

class Level {

    static belongsTo = [building : Building]
    String noiseLevel
    String floor

    static constraints =
    {
    }
}
