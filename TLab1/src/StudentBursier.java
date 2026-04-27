public class StudentBursier extends Student {
    private double cuantumBursa;

    public StudentBursier (int numarMatricol, String prenume, String nume, String formatieDeStudiu, double nota, double cuantumBursa){
        super(numarMatricol, prenume, nume, formatieDeStudiu, nota);
        this.cuantumBursa=cuantumBursa;
    }
    public double getCuantumBursa(){
        return cuantumBursa;
    }

    @Override
    public int hashCode(){return (prenume + nume + formatieDeStudiu + nota + cuantumBursa ).hashCode();}

    @Override
    public boolean equals (Object obj){
        StudentBursier nou = (StudentBursier) obj; //Transform obiectul primit intr un Student
        return super.equals(obj) &&this.cuantumBursa == nou.cuantumBursa;
    }

    @Override
    public String toString(){
        return super.toString()+ ", Bursa: "+ cuantumBursa;
    }




}
