package app

class Building
{

    String buildingName
    String latitude
    String longitude
    String type
    String noiseLevel

    static hasMany = [levels : Level]

    static constraints =
    {
    }
}
