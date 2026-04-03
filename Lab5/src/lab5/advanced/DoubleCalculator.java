package lab5.advanced;

public class DoubleCalculator extends ACalculator {
    @Override
    public void init (Object initialval){
        this.state= (Double) initialval;
    }

    public DoubleCalculator add (double val){
        this.state = (Double) this.state + val;   //trebuie sa fac conversia la integer de fiecare data deoarece eu am declarat state ca Object in clasa de baza
        return this;
    }

    public DoubleCalculator subtract(double val){
        this.state = (Double) this.state - val;
        return this;
    }

    public DoubleCalculator multiply (double val){
        this.state = (Double) this.state * val;
        return this;
    }
}
