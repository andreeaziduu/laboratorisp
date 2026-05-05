import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

//ex 9.3.1
        //a)
        List<Integer> lista = new ArrayList<>();
        lista.add(5);  lista.add(10);  lista.add(12);  lista.add(13);  lista.add(7);
        lista.add(22);  lista.add(14);  lista.add(21);  lista.add(16);  lista.add(23);

        System.out.println("lista: "+ lista);

        int suma = lista.stream()
                .mapToInt(e->e.intValue())
                .sum();
        System.out.println("Suma = " + suma);

        //b)
        int maxim = lista.stream()
                .max(Comparator.naturalOrder())
                .get();

        System.out.println("Maxim = "+ maxim);

        int minim = lista.stream()
                .min(Comparator.naturalOrder())
                .get();

        System.out.println("Minim = "+ minim);

        //c)
        List<Integer> listaModificata = lista.stream()
                .filter (n -> n>=10 && n<= 20)
                .collect(Collectors.toList());

        System.out.println("Lista modificata: " + listaModificata);

        //d)
        List <Double> listaDouble = lista.stream()
                .map(n->Double.valueOf(n))
                .collect(Collectors.toList());

        System.out.println("Lista Double: " + listaDouble);

        //e)
        boolean gasit = lista.stream()
                .anyMatch(n-> n==12);

        System.out.println("Exista valoarea 12 in lista? " + gasit );

    }
}
