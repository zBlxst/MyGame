object Player extends Character ("Player.png") {
    posX = 200
    posY = 200

    sizeX = 40
    sizeY = 40

    hpMax = 100
    hp = 100

    var hand : List[Card] = List(new Card, new Card, new Card, new Card)

    override def initialise : Unit = {
        hand.foreach(x => x.resetPos)
    }
}