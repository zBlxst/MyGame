class Card extends DragableButton (0, 0, 50, 100) {
    var name : String = "Card"
    var needsTarget : Boolean = false
    
    def cast (target : Character) : Unit = {
        println("Casting " + name + " on " + target.name)
    }

    def cast : Unit = {
        println("Casting " + name)
    }

    override def endDrag (dragX : Int, dragY : Int) : Unit = {
        if (0 <= dragX && dragX <= MyFrame.sizeX && dragY <= MyFrame.sizeY - sizeY) {
            if (needsTarget) {
                var targets = Battle.enemies.filter(c => c.posX <= dragX && dragX <= c.posX + c.sizeX && c.posY <= dragY && dragY <= c.posY + c.sizeY)
                if (!targets.isEmpty) {
                    cast(targets(0))
                } else {
                    resetPos
                }
            } else {
                cast
            }
        } else {
            resetPos
        }
    }


    def resetPos : Unit = {
        var indexInHand = Utils.firstIndexOf(Player.hand, this)
        posX = (MyFrame.sizeX - Player.hand.size*sizeX)/2 + indexInHand*sizeX
        posY = MyFrame.sizeY - sizeY
    }
}