import java.awt.Graphics
object Battle {
    var enemies : List[Character] = List()

    def initialise : Unit = {
        Player.initialise
        enemies.foreach(x => x.initialise)
    }


    def display (g : Graphics) {
        Player.display(g)
        enemies.foreach(x => x.display(g))
    }
}