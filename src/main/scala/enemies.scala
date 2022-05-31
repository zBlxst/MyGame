object Enemy1 extends Enemy ("ennemy.png") {
    posX = 600
    posY = 200

    sizeX = 200
    sizeY = 200

    hpMax = 100
    hp = 100

    allActions = List(List("Attack", "10"), List("Attack", "5"), List("Shield", "5"))
}

object Enemy2 extends Enemy ("ennemy.png") {
    posX = 900
    posY = 200

    sizeX = 200
    sizeY = 200

    hpMax = 20
    hp = 20

    allActions = List(List("Debuff", "Weakness", "3"), List("Attack", "5"), List("Shield", "5"))
}