import java.awt.event._
import java.awt._
import javax.swing._

object MyFrame extends JFrame {
    var posX : Int = 100
    var posY : Int = 100

    var sizeX : Int = 1300
    var sizeY : Int = 900

    def initialise : Unit = {
        setSize(sizeX, sizeY)
        setLayout(null)
        setUndecorated(true)
        setLocation(posX, posY)
        
        TopBar.initialise(sizeX, 50)
        
        BattlePanel.initialise
        setContentPane(BattlePanel)
        BattlePanel.startBattle
        
        Repainter.start
        setVisible(true)

        
    }

}