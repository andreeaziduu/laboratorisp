import java.util.ArrayList;
import java.util.List;

public class StudentiDinFisierTxt implements IStudentImport {
    @Override
    public List<Student> doImport(){
        System.out.println("Citesc date din fisier txt");
        return new ArrayList<>();
    }

}
