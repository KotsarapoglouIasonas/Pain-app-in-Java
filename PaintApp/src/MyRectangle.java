//Κοτσαράπογλου Ιάσων Ειρηναίος 321/2014092
//Χαμακιώτη Ελένη 321/2017212
public class MyRectangle extends MyShape {//klironomei apo thn MyShape


    public MyRectangle(int x, int y, int w, int h) {//constructor
        super(x,y,w,h);//kalw ton contructor ths MyShape
    }

    @Override
    public int getX() {//getters gia oles tis metavlhtes
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
