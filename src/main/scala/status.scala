import java.awt.image.BufferedImage
object Status {
    var allInstances : List[Status] = List(StrengthStatus, TenacityStatus, LucidityStatus, PoisonStatus, WeaknessStatus)
    def fromName (name : String) : Status = {
        allInstances.filter(x => x.name == name)(0)
    }
}

class Status {
    var name : String = ""
    var countable : Boolean = true
    def imgName : String = "Icon_" + name + ".png"
    var iconSize : Int = 30
    var img : BufferedImage = null

    def initialise : Unit = {
        img = Utils.loadImage(imgName)
        img = Utils.resize(img, iconSize, iconSize)
    }

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

object WeaknessStatus extends Status {
    name = "Weakness"
    override def onEndTurn(c : Character) : Unit = {
        if (c.status.get(this) > 0) {
            c.status.put(this, c.status.get(this) - 1)
        }
    }
}