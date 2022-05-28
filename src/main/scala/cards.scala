class Damage extends AttackCard {
    name = "Damage"
    needsTarget = true

    var power = 5

    override def cast (target : Character) : Unit = {
        Player.dealDamage(power, target)
        afterCast
    }


}

class Draw extends SkillCard {
    name = "Draw Card"
    needsTarget = false

    override def cast : Unit = {
        Player.drawCards(2)
        super.cast
    }
}