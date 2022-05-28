object Status {
    var allInstances : List[Status] = List()
}

class Status {
    var name : String = ""

    Status.allInstances = this :: Status.allInstances
    def onNewTurn (c : Character) : Unit = {}
    def onEndTurn (c : Character) : Unit = {}
}


object StrenghtStatus extends Status {
    name = "Strenght"
}

object PoisonStatus extends Status {
    name = "Poison"
    override def onNewTurn (c : Character) : Unit = {
        c.loseHp(c.status.get(this))
    }
}