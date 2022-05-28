import java.util.concurrent.TimeUnit
import java.awt.image.BufferedImage
import java.awt.{Graphics, Image, Graphics2D, Color, RenderingHints}
import java.awt.geom.AffineTransform

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

    def resize (img : BufferedImage, newW : Int, newH : Int) : BufferedImage = { 
        var tmp : Image = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH)
        var dimg : BufferedImage = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB)

        var g2d : Graphics2D = dimg.createGraphics()
        g2d.drawImage(tmp, 0, 0, null)
        g2d.dispose()

        return dimg
    }

    def rotate(bimg : BufferedImage , angle : Double) : BufferedImage = {

        var w : Int = bimg.getWidth();    
        var h : Int = bimg.getHeight();

        var rotated : BufferedImage = new BufferedImage(w, h, bimg.getType());  
        var graphic : Graphics2D = rotated.createGraphics();
        graphic.rotate(Math.toRadians(angle), w/2, h/2);
        graphic.drawImage(bimg, null, 0, 0);
        graphic.dispose();
        return rotated;
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

    def shuffled[T] (li_ : List[T]) : List[T] = {
        var li = li_
        var res : List[T] = List()
        while (!li.isEmpty) {
            var r = scala.util.Random.nextInt(li.length)
            res = li(r) :: res
            li = li.filter(x => x != li(r))
        }
        res
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
    override def run : Unit = {
        while (true) {
            Repaintable.allInstances.foreach(x => x.repaint)
            TimeUnit.MILLISECONDS.sleep(10)
        }
    }
}