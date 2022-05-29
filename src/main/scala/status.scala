object Status {
    var allInstances : List[Status] = List()
}

class Status {
    var name : String = ""

    Status.allInstances = this :: Status.allInstances
    def onNewTurn (c : Character) : Unit = {}
    def onEndTurn (c : Character) : Unit = {}
}


object StrengthStatus extends Status {
    name = "Strength"
}

object TenacityStatus extends Status {
    name = "Tenacity"
}

object LucidityStatus extends Status {
    name = "Lucidity"
}

object PoisonStatus extends Status {
    name = "Poison"
    override def onNewTurn (c : Character) : Unit = {
        if (c.status.get(this) > 0) {
            c.loseHp(c.status.get(this))
            c.status.put(this, c.status.get(this) - 1)
        }
    }
}