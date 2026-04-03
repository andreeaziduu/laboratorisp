package lab5.advanced;

public class NewIntCalculator extends ACalculator {
    @Override
    public void init (Object initialval){
        this.state=(Integer) initialval;
    }

    public NewIntCalculator add (Integer val){
        this.state = (Integer) this.state + val;   //trebuie sa fac conversia la integer de fiecare data deoarece eu am declarat state ca Object in clasa de baza
        return this;
    }

    public NewIntCalculator subtract(Integer val){
        this.state = (Integer) this.state - val;
        return this;
    }

    public NewIntCalculator multiply (Integer val){
        this.state = (Integer) this.state * val;
        return this;
    }

}
