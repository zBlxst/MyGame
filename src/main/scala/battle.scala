import java.awt.Graphics
class Battle extends Thread {
    var enemies : List[Character] = List()

    def initialise : Unit = {
        Player.initialise
        enemies = List(Enemy1, Enemy2)
        enemies.foreach(x => x.initialise)
    }


    def display (g : Graphics) : Unit = {
        enemies.foreach(x => x.display(g))
        Player.display(g)
    }

    def enemyDies (e : Enemy) : Unit = {
        enemies = enemies.filter(x => x != e)
        enemies.foreach(x => x.resetPos)
        if (enemies.isEmpty) {
            Player.playing = false
            Player.win
        }
    }

    override def run : Unit = {
        while (Player.playing) {
            Player.newTurn
            enemies.foreach(x => x.newTurn)
        }
    }
}