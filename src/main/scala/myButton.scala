import java.awt.{Graphics, Toolkit}
import java.awt.image.BufferedImage


abstract class MyButton (posX_ : Int, posY_ : Int, sizeX_ : Int, sizeY_ : Int, imgName_ : String) extends Object with Interactable {
    var posX : Int = posX_
    var posY : Int = posY_

    var sizeX : Int = sizeX_
    var sizeY : Int = sizeY_

    var imgName : String = imgName_
    var img : BufferedImage = Utils.loadImage(imgName)

    def initialise : Unit = {

    }

    def isItClicked (mouseX : Int, mouseY : Int) : Boolean = {
        mouseX >= posX && mouseX <= posX + sizeX && mouseY >= posY && mouseY <= posY + sizeY && interactable
    }

    def clickAction : Unit = {
        println("Click !")
    }

    def display (g : Graphics) {
        if (imgName == "") {
            g.fillRect(posX, posY, sizeX, sizeY)
        } else {
            g.drawImage(img, posX, posY, null)
        }
    }
}

class DragableButton (posX_ : Int, posY_ : Int, sizeX_ : Int, sizeY_ : Int, imgName_ : String) extends MyButton (posX_, posY_, sizeX_, sizeY_, imgName_) 
    with Dragable {
    
}

object TopBar extends DragableButton (0, 0, 0, 0, "") {

    var frameOriginalX : Int = -1
    var frameOriginalY : Int = -1

    def initialise (sizeX_ : Int, sizeY_ : Int) : Unit = {
        sizeX = sizeX_
        sizeY = sizeY_
    }

    override def startDrag (dragX : Int, dragY : Int) : Unit = {
        frameOriginalX = MyFrame.posX 
        frameOriginalY = MyFrame.posY

        startX = dragX
        startY = dragY

    }
    override def drag (dragX : Int, dragY : Int) : Unit = {
        var movX = startX - dragX
        var movY = startY - dragY
        MyFrame.posX = (MyFrame.posX - movX).max(0).min(Toolkit.getDefaultToolkit.getScreenSize.getWidth.toInt-MyFrame.sizeX)
        MyFrame.posY = (MyFrame.posY - movY).max(0).min(Toolkit.getDefaultToolkit.getScreenSize.getHeight.toInt-MyFrame.sizeY)

        MyFrame.setLocation(MyFrame.posX, MyFrame.posY)

    }
}

object EndTurnButton extends MyButton (0, 0, 200, 50, "") {
    
    override def initialise : Unit = {
        posX = (MyFrame.sizeX - sizeX)/2
        posY = MyFrame.sizeY - 3*(new Card("")).sizeY - sizeY
    }

    override def clickAction : Unit = {
        Player.endTurn
    }
}
