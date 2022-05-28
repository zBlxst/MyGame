import java.awt.Graphics
import java.util.concurrent.TimeUnit
import java.awt.Color
object Player extends Character ("Player.png") {
    posX = 200
    posY = 200

    sizeX = 200
    sizeY = 200

    hpMax = 100
    hp = 100

    var playing : Boolean = false
    var hisTurn : Boolean = false

    var manaMax : Int = 3
    var mana : Int = 0

    def manaRate : Double = {mana.toDouble/manaMax.toDouble}

    var drawPile : List[Card] = List(new DamageCard, new DamageCard, new DamageCard, new DamageCard, new DamageCard, 
                                     new DamageCard, new DamageCard, new DrawCard, new ShieldCard)
    var discardPile : List[Card] = List()
    var hand : List[Card] = List()
    hand.foreach(x => x.location = "Hand")

    def allCards : List[Card] = List.concat(drawPile, discardPile, hand)
    def resetAllCardsPos : Unit = allCards.foreach(x => x.resetPos)

    override def initialise : Unit = {
        hand.foreach(x => x.resetPos)
        discardPile.foreach(x => x.resetPos)
        drawPile.foreach(x => x.resetPos)

        discardPile.foreach(x => x.interactable = false)
        drawPile.foreach(x => x.interactable = false)

        playing = true
    }

    override def display (g : Graphics) : Unit = {
        super.display(g)
        g.setColor(Color.BLACK)
        g.drawRect(posX, posY + sizeY + 20, sizeX, 20)
        g.setColor(Color.CYAN)
        g.fillRect(posX, posY + sizeY + 20, (sizeX.toDouble*manaRate).toInt, 20)


        g.setColor(Color.BLACK)
        var metrics = g.getFontMetrics
        var text = mana.toString + "/" + manaMax.toString
        var strX = posX + sizeX/2 - metrics.stringWidth(text)/2
        var strY = posY +sizeY + 30 - metrics.getHeight/2 + metrics.getAscent
        g.drawString(text, strX, strY)

        hand.foreach(x => x.display(g))
        discardPile.foreach(x => x.display(g))
        drawPile.foreach(x => x.display(g))



    }

    def loseMana (amount : Int) : Unit = {
        mana -= amount
    }

    override def newTurn : Unit = {
        super.newTurn
        hisTurn = true
        hand.foreach(x => x.whenDiscarded)
        drawCards
        mana = manaMax
        while (hisTurn) {
            TimeUnit.MILLISECONDS.sleep(100)
        }
    }
    
    override def endTurn : Unit = {
        hisTurn = false
    }

    def win : Unit = {
        println("jfpeoz")
    }

    def lose : Unit = {
        playing = false
    }

    override def die : Unit = lose

    def drawCards (amount : Int) : Unit = {
        for (i <- 0 until amount) {
            drawCard
        }
    }

    def drawCards : Unit = drawCards(3)
    
    def drawCard : Unit = {
        if (drawPile.isEmpty) {
            if (!discardPile.isEmpty) {
                drawPile = discardPile
                drawPile.foreach(x => x.location = "DrawPile")
                drawPile = Utils.shuffled(drawPile)
                discardPile = List()
                resetAllCardsPos
            } else {
                return 
                }
            }
        drawPile(scala.util.Random.nextInt(drawPile.length)).whenDrawn
        TimeUnit.MILLISECONDS.sleep(300)

    }
}