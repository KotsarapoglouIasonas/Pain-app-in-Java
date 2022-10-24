//Κοτσαράπογλου Ιάσων Ειρηναίος 321/2014092
//Χαμακιώτη Ελένη 321/2017212
public class Line extends MyShape {//klironomei apo thn MyShape



    public Line(int x, int y, int x2, int y2) {//constructor
        super(x,y,x2,y2);//kalw ton contructor ths MyShape
    }

    @Override
    public int getX() {//getters gia oles tis metavlhtes
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getX2() {
        return w;
    }

    public int getY2() {
        return h;
    }
}
