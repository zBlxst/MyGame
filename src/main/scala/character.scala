import java.awt.image.BufferedImage
import java.awt.{Graphics, Color}
import java.util.HashMap
import java.awt.Font

import com.sun.tools.javac.util.ListBuffer


object Character {
    var allInstances : List[Character] = List(Enemy1, Enemy2)
}

class Character (imgName_ : String) {
    var hpMax : Int = -1
    var hp : Int = -1
    def hpRate : Double = {hp.toDouble / hpMax.toDouble}

    var shield : Int = 0

    var status : HashMap[Status, Int] = new HashMap
    Status.allInstances.foreach(x => status.put(x, 0)) 

    
    var name : String = "Character"

    var posX : Int = -1
    var posY : Int = -1

    var sizeX : Int = -1
    var sizeY : Int = -1

    var imgName : String = imgName_
    var img : BufferedImage = Utils.loadImage(imgName)

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
        g.setFont(new Font("TimesRoman", Font.PLAIN, 16))
        var metrics = g.getFontMetrics
        var text = hp.toString + "/" + hpMax.toString
        var strX = posX + sizeX/2 - metrics.stringWidth(text)/2
        var strY = posY - 30 - metrics.getHeight/2 + metrics.getAscent
        g.drawString(text, strX, strY)

        if (shield > 0) {
            g.setColor(Color.BLACK)
            g.drawRect(posX, posY - 60, sizeX, 20)
            g.setColor(Color.LIGHT_GRAY)
            g.fillRect(posX, posY - 60, (sizeX.toDouble * shield.toDouble/hpMax.toDouble).toInt.min(sizeX), 20)
            
            g.setColor(Color.BLACK)
            g.setFont(new Font("TimesRoman", Font.PLAIN, 16))
            var metrics = g.getFontMetrics
            var text = "Shield : " + shield.toString
            var strX = posX + sizeX/2 - metrics.stringWidth(text)/2
            var strY = posY - 50 - metrics.getHeight/2 + metrics.getAscent
            g.drawString(text, strX, strY)
        }

        var countStatus = 0
        for (i <- 0 until Status.allInstances.length) {
            var stat = Status.allInstances(i)
            var amount = status.get(stat)
            if (amount > 0) {
                var img = stat.img
                var iconX = posX + stat.iconSize * (countStatus%5)
                var iconY = posY + sizeY + stat.iconSize * (countStatus/5)
                g.drawImage(img, iconX, iconY, null)
                countStatus += 1
            }
        }



    }

    def dealDamage (amount : Int, target : Character) : Unit = {
        target.takeDamage(amount)
    }

    def takeDamage (amount : Int) : Unit = {
        shield -= amount
        if (shield < 0) {
            loseHp(-shield)
            shield = 0
        }
    }
    
    def loseHp (amount : Int) : Unit = {
        hp -= amount
        if (hp <= 0) {
            hp = 0
            die
        }
    }

    def gainShield (amount : Int) : Unit = {
        shield += amount
    }

    def die : Unit = {

    }

    def newTurn : Unit = {
        removeShield
        Status.allInstances.foreach(x => x.onNewTurn(this))
    }

    def removeShield : Unit = {
        shield = 0
    }

    def endTurn : Unit = {
        Status.allInstances.foreach(x => x.onEndTurn(this))
    }

    def resetPos : Unit = {

    }
}