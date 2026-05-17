import java.util.List;

public class StudentiInFisierXlsx implements IStudentiExport {
    private String fileName;

    public StudentiInFisierXlsx (String fileName){
        this.fileName = fileName;
    }

    @Override
    public void doExport (List<Student> studenti){
        System.out.println("Export in fisier Excel: "+ fileName );
    }
}
