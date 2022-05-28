import java.awt.image.BufferedImage
import java.awt.{Graphics, Color}

object Character {
    var allInstances : List[Character] = List()
}

class Character (imgName_ : String) {
    var hpMax : Int = -1
    var hp : Int = -1

    def hpRate : Double = {hp.toDouble / hpMax.toDouble}
    
    var name : String = "Character"

    var posX : Int = -1
    var posY : Int = -1

    var sizeX : Int = -1
    var sizeY : Int = -1

    var imgName : String = imgName_
    var img : BufferedImage = Utils.loadImage(imgName)


    Character.allInstances = this :: Character.allInstances

    def initialise : Unit = {
        img = Utils.resize(img, sizeX, sizeY)
    }

    def display (g : Graphics) : Unit = {
        g.drawImage(img, posX, posY, sizeX, sizeY, null)
        g.setColor(Color.BLACK)
        g.drawRect(posX, posY - 40, sizeX, 20)
        g.setColor(Color.GREEN)
        g.fillRect(posX, posY - 40, (sizeX.toDouble * hpRate).toInt, 20)
    }

    def dealDamage (amount : Int, target : Character) : Unit = {
        target.takeDamage(amount)
    }

    def takeDamage (amount : Int) : Unit = {
        hp -= amount
        if (hp <= 0) {
            hp = 0
            die
        }
    }

    def die : Unit = {

    }

    def newTurn : Unit = {

    }

    def endTurn : Unit = {

    }

    def resetPos : Unit = {

    }
}