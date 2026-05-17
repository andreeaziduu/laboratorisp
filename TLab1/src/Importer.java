import java.util.List;

public class Importer {
    public List<Student> startImport (IStudentImport strategyInstance) {
        return strategyInstance.doImport();
    }
}