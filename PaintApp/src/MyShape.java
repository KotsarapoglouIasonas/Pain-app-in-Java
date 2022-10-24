//Κοτσαράπογλου Ιάσων Ειρηναίος 321/2014092
//Χαμακιώτη Ελένη 321/2017212
import java.awt.Shape;
import java.io.Serializable;
import javax.swing.JPanel;

public class MyShape extends JPanel implements Serializable {//kanw implement to interface Serializable gia thn apothikeush twvn arxeiwn

    protected int x, y; //suntetagmenes
    protected int w,h;//mikos platos

    public MyShape(int x, int y, int w, int h) {//constuctor
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public int getX() {//getters
        return x;
    }

    public int getY() {
        return y;
    }

 
    
}
