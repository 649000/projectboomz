package app

class LevelController {

    def index = { 
        println(params.name)
        render params.name

    }
}
