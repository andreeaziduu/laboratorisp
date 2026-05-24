import java.util.List;

public class TimeExecutionDecorator extends TimeExecution {
    private List<Student> studenti;

    public TimeExecutionDecorator (IStudentiExport exporter, List<Student> studenti){
        super(exporter);
        this.studenti = studenti;
    }
    @Override
    public long executionTime(List<Student> studenti){
        long startTime = System.currentTimeMillis();

        this.exporter.doExport(studenti);
        long endTime = System.currentTimeMillis();
        long duration = endTime- startTime;
        System.out.println("Timp afisat in decorator: "+ duration +" ms");
        return duration;
    }

    public long executionTime(){
        long execTime = this.executionTime(studenti);
        return execTime;
    }


}
