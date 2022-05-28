import javax.swing.text.Position
class DamageCard extends AttackCard {
    name = "Damage"
    needsTarget = true
    cost = 1

    def power : Int = (5 + Player.status.get(StrenghtStatus))
    

    override def whenCasted (target : Character) : Unit = {
        Player.dealDamage(power, target)
    }


}

class DrawCard extends SkillCard {
    name = "Draw Card"
    needsTarget = false
    cost = 0

    override def whenCasted : Unit = {
        Player.drawCards(2)
    }
}

class ShieldCard extends SkillCard {
    name = "Shield Up"
    needsTarget = false
    cost = 0

    var power = 150

    override def whenCasted : Unit = {
        Player.gainShield(power)
    }
}

class PoisonCard extends SkillCard {
    name = "Poison"
    needsTarget = true
    cost = 1

    override def whenCasted (target : Character) : Unit = {
        target.status.put(PoisonStatus, target.status.get(PoisonStatus) + 5)
    }
}