package lab5;

public class AppLab5 {
    public static void main (String[] args){
        IntCalculator calculator = new IntCalculator(10);
        int result = calculator.add(5).subtract(3).multiply(2).result();
        System.out.println("a) " + result);

        AdvancedCalculator adv = new AdvancedCalculator(2);
        int resultB = adv.power(3).add(2).root(1).result();
        System.out.println("b) " + resultB);
    }
}
