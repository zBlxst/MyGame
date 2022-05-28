import java.awt.{Graphics, Toolkit}


abstract class MyButton (posX_ : Int, posY_ : Int, sizeX_ : Int, sizeY_ : Int) {
    var posX : Int = posX_
    var posY : Int = posY_

    var sizeX : Int = sizeX_
    var sizeY : Int = sizeY_

    def isItClicked (mouseX : Int, mouseY : Int) : Boolean = {
        mouseX >= posX && mouseX <= posX + sizeX && mouseY >= posY && mouseY <= posY + sizeY 
    }

    def clickAction : Unit = {
        println("Click !")
    }

    def display (g : Graphics) {
        g.fillRect(posX, posY, sizeX, sizeY)
    }
}

class DragableButton (posX_ : Int, posY_ : Int, sizeX_ : Int, sizeY_ : Int) extends MyButton (posX_, posY_, sizeX_, sizeY_) with Dragable {
    
}

object TopBar extends DragableButton (0, 0, 0, 0) {

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

