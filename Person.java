public class Person extends Npc implements Runnable {
    String xname;

    public Person(String xname){
        this.xname = xname;
    }

    @Override
    public void run(){
        System.out.println(this.xname + " says hi from room x");
    }

}
