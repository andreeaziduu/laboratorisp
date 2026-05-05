import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

        try {
            citire("infile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Student> ListaStudenti = new ArrayList<>();
        try {

            ListaStudenti = citire("studenti_in.txt");
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
        String xlsFileName = "laborator8_students.xlsx";
        WriteToXls(new HashSet<>(ListaStudenti), xlsFileName);

        List<Student> studentsFromXls = readFromXls(xlsFileName); //Citeste studentii in ArraList
        System.out.println("\n Studenti cititi din xlsx:");
        for(Student st: studentsFromXls) {
            System.out.println(st);
        }


        System.out.println();
        System.out.println("------ LAB 9 ------");
        //lab9
        List <Student> studentiCuNote = Arrays.asList(
                new Student(1025,"Andrei","Popa","ISM141/2", 8.70),
                new Student(1024,"Ioan","Mihalcea","ISM141/1", 10),
                new Student(1026,"Anamaria","Prodan","TI131/1", 8.90),
                new Student(1029,"Bianca","Popescu","TI131/1,",  10),
                new Student(1029,"Maria","Pana","TI131/2,",  4.10),
                new Student(1029,"Gabriela","Mohanu","TI131/2,",  7.33),
                new Student(1029,"Marius","Nasta","TI131/2,",  3.20),
                new Student(1029,"Marius","Nasta","TI131/1,",  5.12),
                new Student(1029,"Andrei","Dobrescu","TI131/2,",  2.22)
        );

        //a)
        System.out.println("Studenti cu nota 10: ");
        studentiCuNote.stream()
                .filter(s->s.getNota() == 10)
                .forEach(s-> System.out.println(s.getNume()+ " "+ s.getPrenume()+ " "+ s.getNota()));

        //b)
        System.out.println("Studenti cu nota sub 5: ");
        studentiCuNote.stream()
                .filter(s->s.getNota()<5)
                .forEach(s-> System.out.println(s.getNume()+ " "+ s.getPrenume()+ " "+ s.getNota()));

        //c)
        List <Student> listaModificata = studentiCuNote.stream()
                .map(s -> {
                    if(s.getNota()<4){
                        return new Student (s.getNumarMatricol(), s.getNume(), s.getPrenume(), s.getFormatieDeStudiu(), 4.0);
                    }
                    else return s;
        })
                .collect(Collectors.toList());
        System.out.println("Lista Modificata: " + listaModificata);

        //d)
        double sumaNote = studentiCuNote.stream()
                .map(s->s.getNota())
                .reduce(0.0, (a,b)-> a+b);
        System.out.println("Suma notelor: "+ sumaNote);

        //e)
        double medie = sumaNote/studentiCuNote.size();
        System.out.println("Media notelor: " + medie);

    }

    static List<Student> citire(String fileName) throws IOException {
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

            Student s = new Student(matricol, prenume, nume, formatie, 0.0);
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
                    Student studentActualizat = new Student(st.getNumarMatricol(), st.getPrenume(), st.getNume(), st.getFormatieDeStudiu(), valNota);
                    listaStudentiMap.put(matricolNr, studentActualizat);
                }
            }


        }
        ListaStudenti = new ArrayList<>(listaStudentiMap.values());
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

        MutaStudent(ListaStudenti);

        return ListaStudenti;
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

    public static void MutaStudent(List<Student> listaInitiala){
        int n = listaInitiala.size();
        int mijloc = (n+1)/2;

        List<Student> f1 = new ArrayList();
        List<Student> f2 = new ArrayList();

        for (int i=0; i<n; i++){
            Student s= listaInitiala.get(i);

            if(i<mijloc){
                f1.add(new Student(s.getNumarMatricol(), s.getPrenume(), s.getNume(), s.getFormatieDeStudiu(), s.getNota()));
            }
            else {
                f2.add(new Student(s.getNumarMatricol(), s.getPrenume(), s.getNume(), s.getFormatieDeStudiu(), s.getNota()));
            }
        }

        System.out.println("----Rezultate impartite----");
        System.out.println("Formatia 1:");
        f1.forEach(System.out::println);

        System.out.println("Formatia 2:");
        f2.forEach(System.out::println);
    }

    public static void WriteToXls(Set<Student>studenti, String numeFisier){
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             FileOutputStream out = new FileOutputStream(new File(numeFisier))) {

            Sheet sheet = workbook.createSheet("Studenti");
            int rowNum = 0;


            Row headerRow = sheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Matricol");
            headerRow.createCell(1).setCellValue("Prenume");
            headerRow.createCell(2).setCellValue("Nume");
            headerRow.createCell(3).setCellValue("Formatie");
            headerRow.createCell(4).setCellValue("Nota");

            for (Student s : studenti) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(s.getNumarMatricol());
                row.createCell(1).setCellValue(s.getPrenume());
                row.createCell(2).setCellValue(s.getNume());
                row.createCell(3).setCellValue(s.getFormatieDeStudiu());
                row.createCell(4).setCellValue(s.getNota());
            }

            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static List<Student> readFromXls(String numeFisier) {
        List<Student> listaRecuperata = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(numeFisier));
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                int matricol = (int) row.getCell(0).getNumericCellValue();
                String prenume = row.getCell(1).getStringCellValue();
                String nume = row.getCell(2).getStringCellValue();
                String formatie = row.getCell(3).getStringCellValue();
                double nota = row.getCell(4).getNumericCellValue();

                listaRecuperata.add(new Student(matricol, prenume, nume, formatie, nota));
            }

        } catch (IOException e) {
            System.out.println("Eroare la citirea din Excel: " + e.getMessage());
        }

        return listaRecuperata;
    }
}


