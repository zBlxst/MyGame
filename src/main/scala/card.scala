import java.awt.Graphics
import java.awt.Font
import java.awt.font.TextAttribute
import collection.JavaConverters._
import java.awt.Color
class Card (imgName_ : String) extends DragableButton (0, 0, 0, 0, imgName_) {
    var name : String = "Card"
    var needsTarget : Boolean = false
    var originalCost : Int = 0
    var cost : Int = 0

    def description : String = ""
    //var angle : Double = 0

    var location : String = "DrawPile"

    img = Utils.resize(img, sizeX, sizeY)

    override def initialise : Unit = {
        cost = originalCost
        sizeX = MyFrame.sizeX/19
        sizeY = MyFrame.sizeY/8
        super.initialise
    }
    
    def cast (target : Character) : Unit = {
        if (cost <= Player.mana) {
            Player.loseMana(cost)
            println("Casting " + name + " on " + target.name)
            whenCasted(target)
            afterCast
        } else {
            resetPos
        }
    }

    def cast : Unit = {
        if (cost <= Player.mana) {
            Player.loseMana(cost)
            println("Casting " + name)
            whenCasted
            afterCast
        } else {
            resetPos
        }
    }

    def whenCasted : Unit = {

    }

    def whenCasted (target : Character) = {

    }

    def afterCast : Unit = {
        location match {
            case "Hand" => {
                Player.hand = Player.hand.filter(x => x != this)
                Player.discardPile = this :: Player.discardPile
            }
            case "DrawPile" => {
                Player.drawPile = Player.drawPile.filter(x => x != this)
                Player.discardPile = this :: Player.discardPile
            }
            case "DiscardPile" => {

            }
        }
        location = "DiscardPile"
        interactable = false
        Player.resetAllCardsPos
    }

    def whenDrawn : Unit = {
        location = "Hand"
        interactable = true
        Player.hand = this :: Player.hand
        Player.drawPile = Player.drawPile.filter(x => x != this)
        Player.resetAllCardsPos
    }

    def whenDiscarded : Unit = {
        location = "DiscardPile"
        interactable = false
        Player.hand = Player.hand.filter(x => x != this)
        Player.discardPile = this :: Player.discardPile
        Player.resetAllCardsPos
    }

    override def endDrag (dragX : Int, dragY : Int) : Unit = {
        if (Player.hisTurn && 0 <= dragX && dragX <= MyFrame.sizeX && dragY <= MyFrame.sizeY - 2*sizeY) {
            if (needsTarget) {
                if (BattlePanel.battle.enemies.length == 1) {
                    cast(BattlePanel.battle.enemies(0))
                } else {
                    var targets = BattlePanel.battle.enemies.filter(c => c.posX <= dragX && dragX <= c.posX + c.sizeX && c.posY <= dragY && dragY <= c.posY + c.sizeY)
                    if (!targets.isEmpty) {
                        cast(targets(0))
                    } else {
                        resetPos
                    }
                }
            } else {
                cast
            }
        } else {
            resetPos
        }
    }


    def resetPos : Unit = {
        location match {
            case "Hand" => {
                var indexInHand = Utils.firstIndexOf(Player.hand, this)
                posX = (MyFrame.sizeX - Player.hand.size*sizeX)/2 + indexInHand*sizeX
                posY = MyFrame.sizeY - (1.2f*sizeY).toInt
            }
            case "DrawPile" => {
                posX = 10
                posY = MyFrame.sizeY - (1.2f*sizeY).toInt
            }

            case "DiscardPile" => {
                posX = MyFrame.sizeX - 2*sizeX
                posY = MyFrame.sizeY - (1.2f*sizeY).toInt
            }
        }

        // img = Utils.rotateImage(img, -angle)
        // angle = 0

        // angle = -(Player.hand.size-1)*5 + indexInHand*10
        // img = Utils.rotateImage(img, angle)

        // posY = 10*(Math.pow(Player.hand.size.toDouble/2f - indexInHand, 2)).toInt + MyFrame.sizeY - 100

    }

    override def display (g : Graphics) : Unit = {
        super.display(g)
        //g.setFont(new Font("TimesRoman", Font.PLAIN, 10))

        var font : Font = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Kreon-Bold.ttf"))
        font = font.deriveFont(Font.BOLD,10)
        val attributes = (collection.Map(TextAttribute.TRACKING -> 0.05)).asJava
        font = font.deriveFont(attributes)
        font = font.deriveFont(font.getStyle() | Font.BOLD)
        g.setFont(font)

        var maxCharPerLine = 10
        g.setColor(Color.WHITE)
        var texts = Utils.cutString(description, maxCharPerLine)
        var metrics = g.getFontMetrics
        for (i <- 0 until texts.length) {
            var text = texts(i)
            var textX = posX + 11*sizeX/21 - metrics.stringWidth(text)/2
            var textY = posY + 7*sizeY/10 + i*metrics.getHeight - metrics.getHeight/2 + metrics.getAscent
            g.drawString(text, textX, textY)

        }

        var text = name
        var textX = posX + 11*sizeX/21 - metrics.stringWidth(text)/2
        var textY = posY + 2*sizeY/15 - metrics.getHeight/2 + metrics.getAscent
        g.drawString(text, textX, textY)

        text = cost.toString
        textX = posX + sizeX/9 - metrics.stringWidth(text)/2
        textY = posY + 2*sizeY/21 - metrics.getHeight/2 + metrics.getAscent
        g.drawString(text, textX, textY)
        

    }
}

class AttackCard extends Card ("attackcard.png")
class SkillCard extends Card ("skillcard.png")
class PowerCard extends Card ("powercard.png")