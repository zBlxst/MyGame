import java.util.concurrent.TimeUnit
class Enemy (imgName_ : String) extends Character (imgName_) {
    override def newTurn : Unit = {
        super.newTurn
        dealDamage(10, Player)
        TimeUnit.MILLISECONDS.sleep(2000)
    }

    override def die : Unit = {
        super.die
        BattlePanel.battle.enemyDies(this)
    }
}