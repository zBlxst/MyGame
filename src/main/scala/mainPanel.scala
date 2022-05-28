import javax.swing.JPanel
import java.awt.{Graphics, Color}
import java.awt.event.MouseListener
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionListener


object MainPane extends MyPanel {

    buttonList = List.concat(buttonList, List(new DragableButton(50, 50, 50, 50)))
    dragableList = List.concat(dragableList, List(buttonList(1).asInstanceOf[Dragable]))

    
}