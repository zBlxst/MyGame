import javax.swing.JPanel
import java.awt.{Graphics, Color}
import java.awt.event.MouseListener
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionListener


object BattlePanel extends MyPanel {

    buttonList = List.concat(buttonList, List(EndTurnButton))
    dragableList = List.concat(dragableList, Player.allCards)

    var battle : Battle = null

    override def initialise : Unit = {
        super.initialise
    }

    override def paintComponent (g : Graphics) : Unit = {
        super.paintComponent(g)
        if (battle != null) {
            battle.display(g)
        }
    }

    def startBattle : Unit = {
        battle = new Battle
        battle.initialise
        battle.start
    }

    
}