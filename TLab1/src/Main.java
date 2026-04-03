import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;




public class Main {
    public static void main(String[] args) {

        try {
            citire("infile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<StudentBursier> bursieri = new ArrayList();

        bursieri.add(new StudentBursier(1025, "Andrei", "Popa", "ISM141/2", 8.70, 725.50));
        bursieri.add(new StudentBursier(1024, "Ioan", "Mihalcea", "ISM141/1", 9.80, 801.10));
        bursieri.add(new StudentBursier(1026, "Anamaria", "Prodan", "TI131/1", 8.90, 745.50));
        bursieri.add(new StudentBursier(1029, "Bianca", "Popescu", "TI131/1,", 9.10, 780.80));

        List<String> fisierBurse = new ArrayList();
        for (StudentBursier sb: bursieri){
            fisierBurse.add(sb.toString());
        }
        try {
            salveazaInFisier(fisierBurse, "TLab1/bursieri_out.txt");
            salveazaInFisier(fisierBurse, "TLab1/bursieri_out.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    static void citire(String fileName) throws IOException {
        Path path = Paths.get("studenti_in.txt");
        List<String> linii = Files.readAllLines(path);
        List<Student> ListaStudenti = new ArrayList<>();

        Map<Integer, Student> listaStudentiMap = new HashMap<>();

        for (String linie : linii) {
            String[] date = linie.split(",");
            int matricol = Integer.parseInt(date[0]);
            String prenume = date[1];
            String nume = date[2];
            String formatie = date[3];

            Student s = new Student(matricol, prenume, nume, formatie);
            ListaStudenti.add(s);
            listaStudentiMap.put(matricol, s);

            Path pathNote = Paths.get("TLab1/note_anon.txt");
            List<String> Note = Files.readAllLines(pathNote);

            for (String linien : Note) {
                String[] datenote = linien.split(",");
                int matricolNr = Integer.parseInt(datenote[0]);
                double valNota = Double.parseDouble(datenote[1]);

                Student st = listaStudentiMap.get(matricolNr);
                if (st != null) {
                    st.setNota(valNota);
                }
            }


        }
        Collections.sort(ListaStudenti);

        List<String> salvat = new ArrayList<>();
        for (Student s : ListaStudenti) {
            salvat.add(s.toString());
            System.out.println(s);
        }

        Path path1 = Paths.get("studenti_out_sorted.txt");
        Files.write(path1, salvat);


        float notaM = retNota("Bianca", "Popescu", listaStudentiMap);
        float notaN = retNota("Ioan", "Popa", listaStudentiMap);


        System.out.println("Nota Bianca Popescu: " + notaM);
        System.out.println("Nota Ioan Popa: " + notaN);


    }

    static float retNota(String prenume, String nume, Map<Integer, Student> tineriS) {

        HashMap<String, Student> newmap = new HashMap<>();

        for (Student s : tineriS.values()) {
            String cheie = s.getPrenume() + "-" + s.getNume();
            newmap.put(cheie, s);
        }

        String cheieCautata = prenume + "-" + nume;

        if (newmap.containsKey(cheieCautata)) {
            return (float) newmap.get(cheieCautata).getNota();
        }

        return 0.0f;

    }

    static void salveazaInFisier(List<String> lines, String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Files.write(path, lines);
    }
}

