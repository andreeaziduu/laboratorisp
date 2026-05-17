import java.util.Arrays;
import java.util.List;

public class AplicatieCuStrategy {
    public static void main(String[] args) {
        List<Student> studenti = Arrays.asList(
                new Student(1025,"Andrei","Popa","ISM141/2", 8.70),
                new Student(1024,"Ioan","Mihalcea","ISM141/1", 10),
                new Student(1026,"Anamaria","Prodan","TI131/1", 8.90),
                new Student(1029,"Bianca","Popescu","TI131/1,", 10),
                new Student(1029,"Maria","Pana","TI131/2,", 4.10),
                new Student(1029,"Gabriela","Mohanu","TI131/2,", 7.33),
                new Student(1029,"Marius","Nasta","TI131/2,", 3.20),
                new Student(1029,"Marius","Nasta","TI131/1,", 5.12),
                new Student(1029,"Andrei","Dobrescu","TI131/2,", 2.22)
        );

        //a)
        IStudentiExport strategyConsole = new StudentiInConsola();
        Exporter exporter = new Exporter();
        exporter.startExport(strategyConsole, studenti);

        //b)
        String fileNameTxt = "studentiStrategyText.txt";
        StudentiInFisierText strategyFisierText = new StudentiInFisierText(fileNameTxt);
        exporter.startExport(strategyFisierText, studenti);

        //c)
        String fileNameXlsx = "studentiStrategyExcel.xlsx";
        StudentiInFisierXlsx strategyFisierExcel = new StudentiInFisierXlsx(fileNameXlsx);
        exporter.startExport(strategyFisierExcel, studenti);

        //d)
        Importer importer = new Importer();
        IStudentImport importText = new StudentiDinFisierTxt();
        List<Student> studentiDinTxt = importer.startImport(importText);
        for (Student s : studentiDinTxt) {
            System.out.println(s);
        }

        //e)
        IStudentImport importExcel = new StudentiDinFisierXlsx();
        List<Student> studentiDinExcel = importer.startImport(importExcel);

    }
}

