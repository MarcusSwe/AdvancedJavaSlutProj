public class Person extends Npc implements Runnable {
    String xname;
    int value = 10;
    Inventory iTest;
    int currentrum;

    public Person(String xname, int y, Inventory o, int x){
        this.xname = xname;
        this.value = y;
        this.iTest = o;
        this.currentrum = x;
    }

    @Override
    public void run(){
        iTest.setInt(this.value);
        leaveRoom();
        goToRoom();

    }

    public void goToRoom(){
        int z = (int) ((Math.random()*4)+1);
        this.currentrum = z;
        switch(z){
            case 1:
                Game.rum1.setRoom(this.xname);
                break;
            case 2:
                Game.rum2.setRoom(this.xname);
                break;
            case 3:
                Game.rum3.setRoom(this.xname);
                break;
            case 4:
                Game.rum4.setRoom(this.xname);
                break;
        }


        System.out.println(this.xname + " says hi from room "+ z);
    }

    public void leaveRoom(){
        switch(this.currentrum){
            case 1:
                Game.rum1.leaveRoom(this.xname);
                break;
            case 2:
                Game.rum2.leaveRoom(this.xname);
                break;
            case 3:
                Game.rum3.leaveRoom(this.xname);
                break;
            case 4:
                Game.rum4.leaveRoom(this.xname);
                break;
        }
    }

}
