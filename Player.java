import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Player {

    Inventory alpha;
    int yu;
    ScheduledThreadPoolExecutor pool2;
    Future<?> future;
    String xname;
    int currentrum;

    public Player(Inventory g, int x, String o, int r) {
        this.alpha = g;
        this.yu = x;
        this.xname = o;
        this.currentrum = r;
        }



    public void run() {
       alpha.setInt(this.yu);
       alpha.setStream(2);
    }

    public void run2(){
        this.future = this.pool2.scheduleAtFixedRate(new segHej(), 1, 5, TimeUnit.SECONDS);


    }

    public void run3(){
        this.future.cancel(true);
    }

    public void run4(){
        this.pool2.shutdown();
    }

    public void run5(){
        alpha.setRoom("COHH");
    }


    public void startT1() {
        this.pool2 = new ScheduledThreadPoolExecutor(2);
        pool2.scheduleAtFixedRate(new segHej(), 1, 5, TimeUnit.SECONDS);

    }

    private class segHej implements Runnable{
        @Override
        public void run() {
            System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOORKA");
        }

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

    public int giveRoom(){
        return this.currentrum;
    }

}

