import javax.swing.JPanel
import java.awt.{Graphics, Color}
import java.awt.event.MouseListener
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionListener


object BattlePanel extends MyPanel {

    buttonList = List.concat(buttonList, Player.hand)
    dragableList = List.concat(dragableList, Player.hand)

    override def initialise : Unit = {
        super.initialise
        Battle.initialise
    }

    override def paintComponent (g : Graphics) : Unit = {
        super.paintComponent(g)
        Battle.display(g)
    }

    
}