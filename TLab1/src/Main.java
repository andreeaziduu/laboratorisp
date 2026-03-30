import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        /*Student student1 = new Student(300, "Mihaela", "Popescu", "ISM Grupa 21");
        System.out.println(student1);
        Student student2 = new Student(210, "Bianca", "Florescu", "ISM Grupa 21");
        Student student3 = new Student(90, "Maria", "Zidu", "ISM Grupa 21");
        Student student4 = new Student(157, "Ana", "Berea", "ISM Grupa 21");

        List<Student> ListaStudenti = new ArrayList();
        ListaStudenti.add(student1);
        ListaStudenti.add(student2);
        ListaStudenti.add(student3);
        ListaStudenti.add(student4);

        Student studentcautat1 = new Student (90, "Alias", "Popa", "ISM Grupa 21" );
        Set<Student> SetStudenti =  new HashSet<>(ListaStudenti);
        if (SetStudenti.contains(studentcautat1)){
            System.out.println(studentcautat1 + " este prezent");
        }
        else
            System.out.println(studentcautat1 + " nu este prezent");


        for (Student s : ListaStudenti)
            System.out.println(s);*/

        try {
            citire("infile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void citire (String fileName) throws  IOException{
        Path path = Paths.get("studenti_in.txt");
        List <String> linii = Files.readAllLines(path);
        List<Student> ListaStudenti = new ArrayList<>();

        HashMap<Integer, Student> ListaStudentiMap= new HashMap<>();

        for (String linie : linii){
            String [] date = linie.split(",");
            int matricol= Integer.parseInt(date[0]);
            String prenume = date[1];
            String nume = date[2];
            String formatie = date[3];

            Student s = new Student(matricol, prenume, nume, formatie);
            ListaStudenti.add(s);
            ListaStudentiMap.put(matricol, s);

            Path pathNote = Paths.get("TLab1/note_anon.txt");
            List <String> Note = Files.readAllLines(pathNote);

            for (String linien : Note) {
                String[] datenote=  linien.split(",");
                int matricolNr = Integer.parseInt(datenote[0]);
                double valNota = Double.parseDouble(datenote[1]);

                Student st = ListaStudentiMap.get(matricolNr);
                if(st != null){
                    st.setNota(valNota);
                }
            }



        }
        Collections.sort(ListaStudenti);

        List<String> salvat = new ArrayList<>();
        for (Student s: ListaStudenti){
            salvat.add(s.toString());
            System.out.println(s);
        }

        Path path1 = Paths.get("studenti_out_sorted.txt");
        Files.write(path1, salvat);

        











    }

}

