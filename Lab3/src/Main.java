
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main (String [] args) {
        try {
            functie("in.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void functie (String FileName) throws IOException{
        Path path = Paths.get("in.txt");
        List<String> allLinesInMemory = Files.readAllLines(path);
        List <String> fisierOut = new ArrayList<>();

        List<String> listaCuvinte = new ArrayList<>();
        for (String line : allLinesInMemory) {
            String [] words = line.split(" ");

            for (String w : words){
                if (!w.isEmpty()){
                    listaCuvinte.add(w);
                }
            }
        }
        System.out.println("lista de cuvinte: " + listaCuvinte);

        fisierOut.add("Punctul a");
        for (String line : allLinesInMemory){
            fisierOut.add(line + "\n\n");
        }

        fisierOut.add("Punctul b");
        for (String line : allLinesInMemory){
            String linieModificata = line.replace(".", "\n");
            fisierOut.add(linieModificata);
        }

        Path path1 = Paths.get("out.txt");
        Files.write(path1, fisierOut);




    }


}


