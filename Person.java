public class Person extends Npc implements Runnable {
    String xname;
    int value = 10;
    Inventory iTest;

    public Person(String xname, int y, Inventory o){
        this.xname = xname;
        this.value = y;
        this.iTest = o;
    }

    @Override
    public void run(){
        iTest.setInt(this.value);
        System.out.println(this.xname + " says hi from room x");

    }

}
