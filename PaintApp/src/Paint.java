//Κοτσαράπογλου Ιάσων Ειρηναίος 321/2014092
//Χαμακιώτη Ελένη 321/2017212
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Paint extends JPanel implements MouseListener, ActionListener, MouseMotionListener {//klironomei apo thn JPanel gia thn dimiourgia tou frame kathws kai twn koubiwn kai genika twn grafikwn
// kanoume implement ta sugekrimena interface etsi wste na xeroume pote o xrhsths ekane klik kai pou kai se poio shmeio vrisketai to pontiki kathe fora kai genika gia tis leitourgies pou ektelounte me to pontiki
    private Color background = Color.WHITE;//to backgrount tou frame
    private int x1, x2, y1, y2, choice;//x1 kai y1 suntetagmenes otan patame klik , x2,y2 otan to afinoume , choice gia thn epilogh twn sxhmatwn
    private boolean myflag = false, myflag2 = false; //2 flag pou energopoioynte me thn epilogh ths katallhlhs leitourgias
    private ArrayList<MyShape> FillShapes = new ArrayList(); // mia lista me ola ta sxhmata pou boroun na gemistoun me xrwma
    private ArrayList<Polygon> polys = new ArrayList();//mia lista gia ta trigwna
    private ArrayList<MyShape> BorderShapes = new ArrayList();//mia lista gia ta sxhmata pou boroun na allaxoun xrwma perigrammatos
    private HashSet<MyShape> shapeSet = new HashSet<>();//ena hashset pou apothikevontai ola ta sxhmata kai tha mas xreiastei gia to save

    public Paint() {
        JFrame frame = new JFrame("Drawing");//ftiaxnw to frame me onoma drawing
        frame.setSize(1200, 800);

        frame.setBackground(background);
        frame.getContentPane().add(this);

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        JMenu file = new JMenu("File");//ftiaxnw ena menu bar kai vazw to file kai to tools
        menubar.add(file);
        JMenuItem save = new JMenuItem("Save");//2 menuitems gia to file to save kai to load sta opoia vazw kai listeners
        file.add(save);
        save.addActionListener(this);
        JMenuItem load = new JMenuItem("Load");
        file.add(load);
        load.addActionListener(this);
        JMenu tools = new JMenu("Tools"); //ena menu tools 
        menubar.add(tools);
        JMenuItem copy = new JMenuItem("Copy"); // vazw ta antistoixa menu items (leitourgies ) kai gia ola thetw listeners
        tools.add(copy);
        copy.addActionListener(this);
        JMenuItem move = new JMenuItem("Move");
        tools.add(move);
        move.addActionListener(this);
        JMenuItem delete = new JMenuItem("Delete");
        tools.add(delete);
        delete.addActionListener(this);
        JMenuItem undo = new JMenuItem("Undo");
        tools.add(undo);
        undo.addActionListener(this);
        JMenuItem resize = new JMenuItem("Resize");
        tools.add(resize);
        resize.addActionListener(this);
        JMenuItem paint_border = new JMenuItem("Paint Border");
        tools.add(paint_border);
        paint_border.addActionListener(this);
        JMenuItem fill = new JMenuItem("Fill");
        tools.add(fill);
        fill.addActionListener(this);

        JButton rectangle = new JButton("Rectangle"); // prosthetw koubia gia kathe sxhma kai vazw listeners se ola
        rectangle.addActionListener(this);
        JButton circle = new JButton("Circle");
        circle.addActionListener(this);
        JButton oval = new JButton("Oval");
        oval.addActionListener(this);
        JButton line = new JButton("Line");
        line.addActionListener(this);
        JButton star = new JButton("Star");
        star.addActionListener(this);
        JButton square = new JButton("Square");
        square.addActionListener(this);
        JButton triangle = new JButton("Triangle");
        triangle.addActionListener(this);

        this.add(rectangle); //prosthetw ta koubia sto frame
        this.add(circle);
        this.add(oval);
        this.add(line);
        this.add(star);
        this.add(square);
        this.add(triangle);
        addMouseListener(this);
        frame.setVisible(true); //thetw na einai emfanes to parathuro kai close operation otan kleinoume to parathuro
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override

    public void paintComponent(Graphics g) { // methodos gia thn dimiourgia twn sxhmatwn (grafika )
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (grid == null) {
            int w = this.getWidth(); 						
            int h = this.getHeight(); 						
            grid = (BufferedImage) (this.createImage(w, h));
            gc = grid.createGraphics();
            gc.setColor(Color.BLACK); //thetw default xrwma sxediashs to mavro
        }

        g2.drawImage(grid, null, 0, 0);
        check();
    }
    BufferedImage grid;
    Graphics2D gc;

    public void draw() { //methodos poy ulopoiw twn kwdika sxediashs twn sxhmatwn analoga me thn epilogh
        Graphics2D g = (Graphics2D) getGraphics();
        int w = x2 - x1; //frontizw to mhkos kai to platos na einai thetika
        if (w < 0) {
            w = w * (-1);
        }
        int h = y2 - y1;
        if (h < 0) {
            h = h * (-1);
        }
        if (choice == 1) { // dimiourgw antikeimeno gia kathe sxhma kai an borw na tou allaxw xrwma perigrammatos h gemismatos to prosthetw sthn antistoixh lista
            check();
            MyRectangle rec = new MyRectangle(x1, y1, w, h);
            FillShapes.add(rec);
            BorderShapes.add(rec);
            shapeSet.add(rec); // to prosthetw sthn hashset
            gc.drawRect(x1, y1, w, h); //to sxediazw
            repaint();
        } else if (choice == 2) {
            check();
            Circle circle = new Circle(x1, y1, w, w); //omoiws me prin
            FillShapes.add(circle);
            shapeSet.add(circle);
            gc.drawOval(x1, y1, w, w);
            repaint();
        } else if (choice == 3) {
            check();
            Oval oval = new Oval(x1, y1, w, h);//omoiws me prin
            shapeSet.add(oval);
            gc.drawOval(x1, y1, w, h);
            repaint();
        } else if (choice == 4) {
            check();
            Line line = new Line(x1, y1, x2, y2);//omoiws me prin
            BorderShapes.add(line);
            shapeSet.add(line);
            gc.drawLine(x1, y1, x2, y2);
            repaint();
        } else if (choice == 5) {
            check();
            Star_Shape star = new Star_Shape(x1, y1, w, h);//omoiws me prin
            shapeSet.add(star);
            gc.draw(Star_Shape.createStar(x1, y1, w, h));
            repaint();
        } else if (choice == 6) {//omoiws me prin
            check();
            Square square = new Square(x1, y1, w, w);
            FillShapes.add(square);
            BorderShapes.add(square);
            shapeSet.add(square);
            gc.drawRect(x1, y1, w, w);
            repaint();
        } else {
            check();//omoiws me prin
            Triangle_Shape triangle = new Triangle_Shape(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double((x1 + x2) / 2, x2 - x1));
            Polygon poly = new Polygon();
            poly.addPoint(x1, y1);
            poly.addPoint(x2, y2);
            poly.addPoint((x1 + x2) / 2, x2 - x1);
            polys.add(poly);
            triangle.set_Triangle_Shape(x1, y1, x1 + x2 / 2, h + 1, x2, y2);
            gc.draw((Shape) triangle);
            repaint();
        }

    }

    public void check() { //frontixw panta to x1 , y1 na einai megalutera apo x2 y2 etsi wste otan ta aferw gia tis topothesies twn sxhmatwn na mhn vgainoyn arnitika 
        if (x1 > x2) {
            int z = 0;
            z = x1;
            x1 = x2;
            x2 = z;
        }
        if (y1 > y2) {
            int z = 0;
            z = y1;
            y1 = y2;
            y2 = z;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (myflag == true) { // an to flag einai true (dld pathse thn leitourgia fill
            for (MyShape item : FillShapes) {//trexw olh thn sxetikh lista me ta sxhmata gia fill
                if (item instanceof MyRectangle) { //elegxw ti einai  kai to sxediazw gemismeno
                    if (x1 >= item.getX() && x1 <= item.getX() + ((MyRectangle) item).getW() && y1 >= item.getY() && y1 < item.getY() + ((MyRectangle) item).getH()) {
                        gc.fillRect(item.getX(), item.getY(), ((MyRectangle) item).getW(), ((MyRectangle) item).getH());
                        break;
                    }
                }
                if (item instanceof Circle) {//omoiws me prin
                    if (Math.sqrt(((x1 - item.getX()) * (x1 - item.getX())) + ((y1 - item.getY()) * (y1 - item.getY()))) < ((Circle) item).getW()) {
                        gc.fillOval(item.getX(), item.getY(), ((Circle) item).getW(), ((Circle) item).getW());
                        break;
                    }
                }
                if (item instanceof Square) {//omoiws me prin
                    if (x1 >= item.getX() && x1 <= item.getX() + ((Square) item).getW() && y1 >= item.getY() && y1 < item.getY() + ((Square) item).getH()) {
                        gc.fillRect(item.getX(), item.getY(), ((Square) item).getW(), ((Square) item).getH());
                        break;
                    }
                }
            }
            for (Polygon item : polys) {//omoiws me prin
                if (item.contains(e.getX(), e.getY())) {
                    gc.fillPolygon(item.xpoints, item.ypoints, 3);
                }
            }
            repaint();
            myflag = false;//kanw to flag false etsi wste na trexei mono gia ena sxhma
            gc.setColor(Color.BLACK);//ksanathetw to xrwma mavro
        }
        if (myflag2 == true) {//an to flag einai true (dld pathse thn leitourgia paint border
            for (MyShape item : BorderShapes) {//trexw olh thn sxetikh lista me ta sxhmata gia border
                if (item instanceof MyRectangle) {//elegxw ti einai  kai to sxediazw gemismeno
                    if (x1 >= item.getX() && x1 <= item.getX() + ((MyRectangle) item).getW() && y1 >= item.getY() && y1 < item.getY() + ((MyRectangle) item).getH()) {
                        gc.drawRect(item.getX(), item.getY(), ((MyRectangle) item).getW(), ((MyRectangle) item).getH());
                        break;
                    }
                }
                if (item instanceof Square) {//omoiws me prin
                    if (x1 >= item.getX() && x1 <= item.getX() + ((Square) item).getW() && y1 >= item.getY() && y1 < item.getY() + ((Square) item).getH()) {
                        gc.drawRect(item.getX(), item.getY(), ((Square) item).getW(), ((Square) item).getW());
                        break;
                    }
                }
                if (item instanceof Line) {//omoiws me prin
                    gc.drawLine(item.getX(), item.getY(), ((Line) item).getX2(), ((Line) item).getY2());
                }

            }
            repaint();
            myflag2 = false;//kanw to flag false etsi wste na trexei mono gia ena sxhma
            gc.setColor(Color.BLACK);//ksanathetw to xrwma mavro
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {//pairnw tis suntetagmenes opou ekane o xrhsths klik
        x1 = e.getX();
        y1 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {//pairnw tis suntetagmenes opou  o xrhsths afhse to klik
        x2 = e.getX();
        y2 = e.getY();
        draw();

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) { //elegxw an pathse kapou pou exw listener
        super.removeMouseMotionListener(this);
        int w = x2 - x1;
        if (w < 0) {
            w = w * (-1);
        }
        int h = y2 - y1;
        if (h < 0) {
            h = h * (-1);
        }
        if (e.getActionCommand().equals("Copy")) // action when user choose Empt Rect
        {
            //copy
            // copyPoint = new Point2D(event.getX(), event.getY());

        }
        if (e.getActionCommand().equals("Move")) // action when user choose Empt Rect
        {
            //move
        }
        if (e.getActionCommand().equals("Delete")) // action when user choose Empt Rect
        {
            //delete
        }
        if (e.getActionCommand().equals("Undo")) // action when user choose Empt Rect
        {
            //undo
        }
        if (e.getActionCommand().equals("Resize")) // action when user choose Empt Rect
        {
            check();
            Rectangle newRect = new Rectangle(x1, y1, w, h);
            newRect.add(x1, y1);

        }
        if (e.getActionCommand().equals("Paint Border")) // an pathsei tou vgazw ena parathuro gia na dialeksei xrwma
        {
            Color bgColor = JColorChooser.showDialog(this, "Choose Border Color", getBackground());
            if (bgColor != null) {
                gc.setColor(bgColor);
            }
            myflag2 = true;
        }
        if (e.getActionCommand().equals("Fill")) // an pathsei tou vgazw ena parathuro gia na dialeksei xrwma
        {
            Color bgColor = JColorChooser.showDialog(this, "Choose Fill Color", getBackground());
            if (bgColor != null) {
                gc.setColor(bgColor);
            }
            myflag = true;
        }
        if (e.getActionCommand().equals("Save")) { // an pathsei save kalw thn writecanvas kai tou dinw to hashset
            FileHandler.writecanvas(shapeSet);
        }
        if (e.getActionCommand().equals("Load")) {//an patisei load
            this.removeAll();
            //anakta to set sximatwn
            shapeSet = FileHandler.readcanvas();//afou gemizw to hashset apo to arxeio
            for (MyShape myshape : shapeSet) {//vazw kathe sxima sto frame
                this.add(myshape);
            }
            revalidate();
            repaint();

        }
        if (e.getActionCommand().equals("Rectangle")) // an pathsei se kapoio sxhma allazw analoga to choise
        {
            choice = 1;
        }

        if (e.getActionCommand().equals("Circle")) // omoiws me prin
        {
            choice = 2;
        }

        if (e.getActionCommand().equals("Oval")) // omoiws me prin
        {
            choice = 3;
        }
        if (e.getActionCommand().equals("Line")) // omoiws me prin
        {
            choice = 4;
        }
        if (e.getActionCommand().equals("Star")) // omoiws me prin
        {
            choice = 5;
        }
        if (e.getActionCommand().equals("Square")) // omoiws me prin
        {
            choice = 6;
        }
        if (e.getActionCommand().equals("Triangle")) // omoiws me prin
        {
            choice = 7;
        }
    }

    @Override
    public void mouseDragged(MouseEvent re) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
