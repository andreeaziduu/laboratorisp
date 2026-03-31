package lab5;

public class AdvancedCalculator extends IntCalculator {
    public AdvancedCalculator (int initialState){
        super(initialState);
    }
    public AdvancedCalculator divide(int val){
        if(val != 0){
            this.state /= val;
        }
        else {
            System.out.println("eroare");
        }
        return this;
    }
    public AdvancedCalculator power (int exponent){
        this.state = (int) Math.pow(this.state, exponent);
        return this;
    }
    public AdvancedCalculator root (int n){
        if (n>0){
            this.state = (int) Math.pow(this.state, 1.0/n);
        }
        return this;
    }
    @Override
    public AdvancedCalculator add(int val) {
        super.add(val);
        return this;
    }

    @Override
    public AdvancedCalculator subtract(int val) {
        super.subtract(val);
        return this;
    }

    @Override
    public AdvancedCalculator multiply(int val) {
        super.multiply(val);
        return this;
    }

}
