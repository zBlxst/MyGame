import java.util.concurrent.TimeUnit
import java.awt.Graphics
import java.awt.Color
class Enemy (imgName_ : String) extends Character (imgName_) {

    var nextAction : String = "Attack 0"
    var allActions : List[String] = List("Attack 0")

    override def initialise : Unit = {
        super.initialise
        chooseNextAction
    }


    override def newTurn : Unit = {
        super.newTurn
        doAction
        chooseNextAction
        TimeUnit.MILLISECONDS.sleep(500)
        super.endTurn
    }

    def doAction : Unit = {
        if (nextAction.startsWith("Attack")) {
            var splited = nextAction.split(" ")
            dealDamage(splited(1).toInt, Player)
        } else if (nextAction.startsWith("Shield")) {
            var splited = nextAction.split(" ")
            gainShield(splited(1).toInt)
        }
    }

    def chooseNextAction : Unit = {
        nextAction = allActions(scala.util.Random.nextInt(allActions.length))
    }

    override def display (g : Graphics) : Unit = {
        super.display(g)
        g.setColor(Color.BLACK)
        
        var metrics = g.getFontMetrics
        var text = "Next action : " + nextAction
        var strX = posX + sizeX/2 - metrics.stringWidth(text)/2
        var strY = posY - 50 - (if (shield > 0) 20 else 0) - metrics.getHeight/2 + metrics.getAscent
        g.drawString(text, strX, strY)
    }

    

    override def die : Unit = {
        super.die
        BattlePanel.battle.enemyDies(this)
    }
}