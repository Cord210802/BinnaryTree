import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AlturaArbol {

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        System.out.println(bs.promedioArbol(6,40));
        try{
            File file = new File("altura.txt");
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i<8000; i++){
                pw.println(bs.promedioArbol(i, 40) + ", " + i);
            }
            pw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
