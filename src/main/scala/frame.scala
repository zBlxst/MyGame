import java.awt.event._
import java.awt._
import javax.swing._

object MyFrame extends JFrame {
    var posX : Int = 100
    var posY : Int = 100

    var sizeX : Int = 1000
    var sizeY : Int = 500

    def initialise : Unit = {
        setSize(sizeX, sizeY)
        setLayout(null)
        setUndecorated(true)
        setLocation(posX, posY)
        setVisible(true)

        TopBar.initialise(sizeX, 50)

        MainPane.initialise
        setContentPane(MainPane)

        Repainter.start
    }

}