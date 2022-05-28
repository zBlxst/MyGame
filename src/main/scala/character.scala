import java.awt.image.BufferedImage
import java.awt.Graphics

object Character {
    var allInstances : List[Character] = List()
}

class Character (imgName_ : String) {
    var hpMax : Int = -1
    var hp : Int = -1
    
    var name : String = "Character"

    var posX : Int = -1
    var posY : Int = -1

    var sizeX : Int = -1
    var sizeY : Int = -1

    var imgName : String = imgName_
    var img : BufferedImage = Utils.loadImage(imgName)


    Character.allInstances = this :: Character.allInstances

    def initialise : Unit = {
        
    }

    def display (g : Graphics) : Unit = {
        g.drawImage(img, posX, posY, sizeX, sizeY, null)
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
}