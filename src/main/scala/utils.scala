import java.util.concurrent.TimeUnit
import java.sql.Time

object Utils {
    def firstSatisfiedList[T] (li : List[T], f : T => Boolean) : Int = {
        for (i <- 0 until li.size) {
            if (f(li(i))) {
                return i
            }
        }
        return -1
    }
}


object Repaintable {
    var allInstances : List[Repaintable] = List()
}

trait Repaintable {
    Repaintable.allInstances = this :: Repaintable.allInstances
    def repaint : Unit
}

object Repainter extends Thread {
    override def start : Unit = {
        while (true) {
            Repaintable.allInstances.foreach(x => x.repaint)
            TimeUnit.MILLISECONDS.sleep(10)
        }
    }
}