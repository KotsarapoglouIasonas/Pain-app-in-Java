//Κοτσαράπογλου Ιάσων Ειρηναίος 321/2014092
//Χαμακιώτη Ελένη 321/2017212
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import static java.util.Locale.filter;
import javax.swing.JOptionPane;

public class FileHandler {

    public static void writecanvas(HashSet<MyShape> set) { //methodos pou dexetai ws orisma ena hashset pou exei mesa ola ta sxhmata
        //gia kathe sxima to grafei sto arxeio
        String filename = JOptionPane.showInputDialog("Give file name to save");//mas dinei o xrhsths to onoma toy arxeioy gia apothikeush
        try (FileOutputStream fos = new FileOutputStream(filename);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {


            oos.writeObject(set);//dimiourgoume to arxeio kai grafoume mesa oti exei to hashset

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //epistrefei to antikeimeno me ta sximata
    public static HashSet<MyShape> readcanvas() {
        //zitaei apto xristi to arxeio
        String filename = JOptionPane.showInputDialog("Give file name to read");

        try (InputStream is = new FileInputStream(filename); 
                ObjectInputStream ois = new ObjectInputStream(is)) {

            return (HashSet<MyShape>) ois.readObject();
        } catch (IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
        }
        //alliws epistrefei mia adeia 
        return new HashSet<>();
    }
}
