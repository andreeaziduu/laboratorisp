package lab5.advanced;

public abstract class ACalculator {
    protected Object state;

    public Object result(){
        return state;
    }

    public void clear(){
        this.state=0;
    }

    public abstract void init (Object initialval);



}
