trait Dragable {
    var posX : Int
    var posY : Int

    var sizeX : Int
    var sizeY : Int
    
    var startX : Int = -1
    var startY : Int = -1

    def startDrag (dragX : Int, dragY : Int) : Unit = {
        startX = dragX - posX
        startY = dragY - posY
    }
    def endDrag (dragX : Int, dragY : Int) : Unit = {
        startX = -1
        startY = -1
    }
    def drag (dragX : Int, dragY : Int) : Unit = {
        posX = dragX - startX
        posY = dragY - startY
    }

    def isItPressed (mouseX : Int, mouseY : Int) : Boolean = {
        mouseX >= posX && mouseX <= posX + sizeX && mouseY >= posY && mouseY <= posY + sizeY 
    }
}