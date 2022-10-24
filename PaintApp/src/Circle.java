//Κοτσαράπογλου Ιάσων Ειρηναίος 321/2014092
//Χαμακιώτη Ελένη 321/2017212
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Circle extends MyShape {//klironomei apo thn MyShape


    public Circle(int x, int y, int w, int h) {//constructor
        super(x,y,w,h);//kalw ton contructor ths MyShape
    }

    @Override
    public int getX() { //getters gia oles tis metavlhtes
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

}
