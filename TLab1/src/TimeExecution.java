import java.util.List;

public class TimeExecution implements ITimeExecution {
    protected IStudentiExport exporter;

    public TimeExecution (IStudentiExport exporter){
        this.exporter = exporter;
    }

    public long executionTime (List<Student> studenti){
        return 0;
    }
}
