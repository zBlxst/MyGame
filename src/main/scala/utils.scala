import java.util.concurrent.TimeUnit
import java.awt.image.BufferedImage
import java.awt.Graphics

object Utils {
    def loadImage (name : String) : BufferedImage = {
        try {
            javax.imageio.ImageIO.read(getClass.getResource(name))
        } catch {
            case _ : Throwable => {
                javax.imageio.ImageIO.read(getClass.getResource("Empty.png"))
            }
        }
    }
    
    
    def firstSatisfiedList[T] (li : List[T], f : T => Boolean) : Int = {
        for (i <- 0 until li.size) {
            if (f(li(i))) {
                return i
            }
        }
        return -1
    }

    def firstIndexOf[T] (li : List[T], el : T) : Int = {
        for (i <- 0 until li.size) {
            if (li(i) == el) {
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