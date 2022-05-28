import java.awt.image.BufferedImage
import java.awt.{Graphics, Color}

object Character {
    var allInstances : List[Character] = List()
}

class Character (imgName_ : String) {
    var hpMax : Int = -1
    var hp : Int = -1

    var shield : Int = 0

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
        g.setColor(Color.BLACK)

        var metrics = g.getFontMetrics
        var text = hp.toString + "/" + hpMax.toString
        var strX = posX + sizeX/2 - metrics.stringWidth(text)/2
        var strY = posY - 30 - metrics.getHeight/2 + metrics.getAscent
        g.drawString(text, strX, strY)

        if (shield > 0) {
            g.setColor(Color.BLACK)
            g.drawRect(posX, posX - 60, sizeX, 20)
            g.setColor(Color.LIGHT_GRAY)
            g.fillRect(posX, posY - 60, (sizeX.toDouble * shield.toDouble/hpMax.toDouble).toInt.min(sizeX), 20)
            g.setColor(Color.BLACK)

            var metrics = g.getFontMetrics
            var text = "Shield : " + shield.toString
            var strX = posX + sizeX/2 - metrics.stringWidth(text)/2
            var strY = posY - 50 - metrics.getHeight/2 + metrics.getAscent
            g.drawString(text, strX, strY)
        }



    }

    def dealDamage (amount : Int, target : Character) : Unit = {
        target.takeDamage(amount)
    }

    def takeDamage (amount : Int) : Unit = {
        shield -= amount
        if (shield < 0) {
            hp += shield
            if (hp <= 0) {
                hp = 0
                die
            }
            shield = 0
        }
    }

    def gainShield (amount : Int) : Unit = {
        shield += amount
    }

    def die : Unit = {

    }

    def newTurn : Unit = {
        removeShield
    }

    def removeShield : Unit = {
        shield = 0
    }

    def endTurn : Unit = {

    }

    def resetPos : Unit = {

    }
}