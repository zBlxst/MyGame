class Card (imgName_ : String) extends DragableButton (0, 0, 100, 125, imgName_) {
    var name : String = "Card"
    var needsTarget : Boolean = false
    var cost : Int = 0

    //var angle : Double = 0

    var location : String = "DrawPile"

    img = Utils.resize(img, sizeX, sizeY)
    
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
                posY = MyFrame.sizeY - 2*sizeY
            }
            case "DrawPile" => {
                posX = 10
                posY = MyFrame.sizeY - 2*sizeY
            }

            case "DiscardPile" => {
                posX = MyFrame.sizeX - 2*sizeX
                posY = MyFrame.sizeY - 2*sizeY
            }
        }

        // img = Utils.rotateImage(img, -angle)
        // angle = 0

        // angle = -(Player.hand.size-1)*5 + indexInHand*10
        // img = Utils.rotateImage(img, angle)

        // posY = 10*(Math.pow(Player.hand.size.toDouble/2f - indexInHand, 2)).toInt + MyFrame.sizeY - 100

    }
}

class AttackCard extends Card ("attackcard.png")
class SkillCard extends Card ("skillcard.png")
class PowerCard extends Card ("powercard.png")