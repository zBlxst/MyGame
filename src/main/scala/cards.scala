class Damage extends AttackCard {
    name = "Damage"
    needsTarget = true
    cost = 1

    var power = 5
    

    override def whenCasted (target : Character) : Unit = {
        Player.dealDamage(power, target)
    }


}

class Draw extends SkillCard {
    name = "Draw Card"
    needsTarget = false
    cost = 0

    override def whenCasted : Unit = {
        Player.drawCards(2)
    }
}