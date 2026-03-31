package lab5;

public class IntCalculator {
    protected int state;

    public IntCalculator (int initialstate){
        this.state=initialstate;
    }
    public IntCalculator add(int val){
        this.state+=val;
        return this;
    }
    public IntCalculator subtract(int val){
        this.state -= val;
        return this;
    }
    public IntCalculator multiply(int val){
        this.state *= val;
        return this;
    }
    public int result(){
        return state;
    }
    public IntCalculator clear(){
        this.state=0;
        return this;
    }

}
