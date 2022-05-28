import javax.swing.JPanel
import java.awt.{Graphics, Color}
import java.awt.event.MouseListener
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionListener


object MainPanel extends MyPanel {

    buttonList = List.concat(buttonList)
    dragableList = List.concat(dragableList, List(buttonList(1).asInstanceOf[Dragable]))

    
}