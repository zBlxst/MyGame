import java.awt.Graphics
import java.util.concurrent.TimeUnit
object Player extends Character ("Player.png") {
    posX = 200
    posY = 200

    sizeX = 200
    sizeY = 200

    hpMax = 100
    hp = 100

    var playing : Boolean = false
    var hisTurn : Boolean = false

    var drawPile : List[Card] = List(new Damage, new Damage, new Damage, new Damage, new Damage, new Damage, new Damage, new Draw)
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
        hand.foreach(x => x.display(g))
        discardPile.foreach(x => x.display(g))
        drawPile.foreach(x => x.display(g))
    }

    override def newTurn : Unit = {
        hisTurn = true
        hand.foreach(x => x.whenDiscarded)
        drawCards
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