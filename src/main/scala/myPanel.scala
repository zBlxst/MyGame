import javax.swing._
import java.awt.{Graphics}
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener

abstract class MyPanel extends JPanel with MouseListener with MouseMotionListener with Repaintable {
    var buttonList : List[MyButton] = List(TopBar)

    var dragableList : List[Dragable] = List(TopBar)
    var draged : Dragable = null

    var clickX : Int = -1
    var clickY : Int = -1

    override def paintComponent (g: Graphics): Unit = {
        super.paintComponent(g)
        buttonList.foreach(x => x.display(g))
    }



    def initialise : Unit = {
        addMouseListener(this)
        addMouseMotionListener(this)

        buttonList.foreach(x => x.initialise)

        setVisible(true)
    }


    def mouseClicked(e: MouseEvent): Unit = {
        var clickCaught : Boolean = false
        def findClick (b: MyButton) {
            if (!clickCaught && b.isItClicked(e.getX, e.getY)) {
                clickCaught = true
                b.clickAction
            }
        }
        buttonList.foreach(findClick)
    }

    def mouseEntered(e: MouseEvent): Unit = {}
    def mouseExited(e: MouseEvent): Unit = {}
    def mousePressed(e: MouseEvent): Unit = {
        var pressCaught : Boolean = false
        def findPress (d: Dragable): Unit = {
            if (!pressCaught && d.isItPressed(e.getX, e.getY)) {
                pressCaught = true
                draged = d
                d.startDrag(e.getX, e.getY)
            }
        }
        dragableList.foreach(findPress)
    }
    def mouseReleased(e: MouseEvent): Unit = {
        if (draged != null) {
            draged.endDrag(e.getX, e.getY)
            draged = null
        }
    }

    def mouseDragged(e: MouseEvent): Unit = {
        if (draged != null) {
            draged.drag(e.getX, e.getY)
        }
    }
    def mouseMoved(e: MouseEvent): Unit = {}
}