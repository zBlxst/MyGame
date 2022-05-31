import javax.swing.text.Position
class DamageCard extends AttackCard {
    name = "Damage"
    override def description : String = "Deal " + power + " damage" + (if (power > 1) "s" else "") + "." 
    needsTarget = true
    originalCost = 1

    def power : Int = ((5 + Player.status.get(StrengthStatus)) * (if (Player.status.get(WeaknessStatus) > 0) 0.5f else 1f)).toInt
    

    override def whenCasted (target : Character) : Unit = {
        Player.dealDamage(power, target)
    }


}

class DrawCard extends SkillCard {
    name = "Draw Card"
    override def description : String = "Draw " + power + " cards."
    needsTarget = false
    originalCost = 0

    def power : Int = 2 + Player.status.get(LucidityStatus)

    override def whenCasted : Unit = {
        Player.drawCards(2)
    }
}

class ShieldCard extends SkillCard {
    name = "Shield Up"
    override def description : String = "Gives " + power + " shield." 
    needsTarget = false
    originalCost = 0

    def power : Int = 150 + Player.status.get(TenacityStatus)

    override def whenCasted : Unit = {
        Player.gainShield(power)
    }
}

class PoisonCard extends SkillCard {
    name = "Poison"
    override def description: String = "Apply " + power + " poison." 
    needsTarget = true
    originalCost = 1

    var power = 5

    override def whenCasted (target : Character) : Unit = {
        target.status.put(PoisonStatus, target.status.get(PoisonStatus) + 5)
    }
}