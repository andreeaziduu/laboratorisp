import java.util.List;

public class Exporter {
    void startExport (IStudentiExport strategyInstance, List<Student> students){
        strategyInstance.doExport(students);
    }
}
