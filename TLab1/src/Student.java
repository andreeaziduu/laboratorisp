public class Student implements Comparable <Student>{
    protected final int numarMatricol;
    protected final String prenume;
    protected final String nume;
    protected final String formatieDeStudiu;
    protected final double nota;


    public String getPrenume() {
        return prenume;
    }

    public String getNume() {
        return nume;
    }

    public double getNota() {
        return nota;
    }

    public int getNumarMatricol() {return numarMatricol;}

    public String getFormatieDeStudiu() {return formatieDeStudiu;}

    public Student (int numarMatricol, String prenume, String nume, String formatieDeStudiu, double nota) {
        this.numarMatricol=numarMatricol;
        this.prenume=prenume;
        this.nume=nume;
        this.formatieDeStudiu=formatieDeStudiu;
        this.nota = nota;
    }
    public String toString(){
        return "Student: " + numarMatricol+ ", "+  prenume +", "+ nume + ", " + formatieDeStudiu+ ','+ nota;
    }
    @Override
    public boolean equals (Object obj){
        Student nou = (Student) obj; //Transform obiectul primit intr un Student
        return this.numarMatricol == nou.numarMatricol &&
                this.prenume.equals(nou.prenume) &&
                this.nume.equals(nou.nume)&&
                this.formatieDeStudiu.equals(nou.formatieDeStudiu) &&
                this.nota==nou.nota;

    }
    @Override
    public int hashCode(){
        return (prenume + nume + formatieDeStudiu + nota ).hashCode();
    }


    public int compareTo(Student st2){
       int rezFormatie = this.formatieDeStudiu.compareTo(st2.formatieDeStudiu);

       if (rezFormatie !=0 ){
           return rezFormatie;
       }
       return this.nume.compareTo(st2.nume);
    }

}

