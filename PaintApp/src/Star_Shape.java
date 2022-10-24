//Κοτσαράπογλου Ιάσων Ειρηναίος 321/2014092
//Χαμακιώτη Ελένη 321/2017212
import java.awt.Shape;
import java.awt.geom.Path2D;

public class Star_Shape extends MyShape {//klironomei apo thn MyShape



    public Star_Shape(int x, int y, int w, int h) {//constructor
        super(x,y,w,h);//kalw ton contructor ths MyShape
    }

    public static Shape createStar(double centerX, double centerY, // dimiourgei asteri me 5 korufes kai epistrfei antikeimeno tupou Shape
            double innerRadius, double outerRadius) {
        Path2D path = new Path2D.Double();
        double deltaAngleRad = Math.PI / 5;
        for (int i = 0; i < 5 * 2; i++) {
            double angleRad = 0 + i * deltaAngleRad;
            double ca = Math.cos(angleRad);
            double sa = Math.sin(angleRad);
            double relX = ca;
            double relY = sa;
            if ((i & 1) == 0) {
                relX *= outerRadius;
                relY *= outerRadius;
            } else {
                relX *= innerRadius;
                relY *= innerRadius;
            }
            if (i == 0) {
                path.moveTo(centerX + relX, centerY + relY);
            } else {
                path.lineTo(centerX + relX, centerY + relY);
            }
        }
        path.closePath();
        return path;
    }
}
