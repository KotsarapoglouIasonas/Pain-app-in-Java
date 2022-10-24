//Κοτσαράπογλου Ιάσων Ειρηναίος 321/2014092
//Χαμακιώτη Ελένη 321/2017212
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class Triangle_Shape extends Path2D.Double {//klironomei apo thn Path2D.Double

    private int x, y, w, h, x2, y2; // dilwnoyme oles tis metavlites 

    public void set_Triangle_Shape(int x, int y, int w, int h, int x2, int y2) { //constuctor
        this.x = x;//x,y metavlites prwtou shmeiou
        this.y = y;
        this.w = w;//platos kai mikos
        this.h = h;
        this.x2=x2;//x2,y2 metavlhtes 2ou shmeiou
        this.y2=y2;
    }

    public Triangle_Shape(Point2D... points) { //constructor
        moveTo(points[0].getX(), points[0].getY());
        lineTo(points[1].getX(), points[1].getY());
        lineTo(points[2].getX(), points[2].getY());
        closePath();
    }

    public int getX() { //getters
        return x;
    }

    public int getY() {
        return y;
    }

}
