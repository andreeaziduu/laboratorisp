package lab5.advanced;

public class Lab5Adv {
    public void main (String[] args){
        NewIntCalculator icalc = new NewIntCalculator();
        icalc.init(10);
        DoubleCalculator dcalc = new DoubleCalculator();
        dcalc.init(10.0);

        int result1 = (Integer) icalc.add(5).subtract(3).multiply(2).result();
        System.out.println("rez1 = " + result1);
        double result2 = (Double) dcalc.add(5).subtract(3.3).multiply(2.2).result();
        System.out.println("rez2 = " + result2);

    }
}
