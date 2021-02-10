import java.util.Arrays;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Player {

    Inventory alpha;
    int size;
    ScheduledThreadPoolExecutor pool2;
    Future<?> future;
    String xname;
    int currentrum;
    Inventory playerItems;
    GameObject[] showItems = new GameObject[5];


    public Player(Inventory g, int x, String o, int r) {
        this.alpha = g;
        this.size = x;
        this.xname = o;
        this.currentrum = r;
        this.playerItems = new Inventory(5);
        }



    public void run() {
      // alpha.setInt(this.yu);
       //alpha.setStream(2);
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

    /*public void run5(){
        alpha.setRoom("COHH");
    }*/


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

    public void defaultFillInventory(){
        Arrays.fill(this.playerItems.xObjects, Game.emptyCell);
    }


    public void getRoomItem(){
        switch(this.currentrum){
            case 1:
                if(Game.rum1.passaItem()) {
                    System.out.println("ITEEEEEEEEEEEEEEM ÄR HÄR");
                    this.addRoomItem(Game.rum1.getItemNPC());
                    //this.addRoomItem(Game.xTest.xObjects[0]);
                }
                break;
            case 2:
                if(Game.rum2.passaItem()) {
                    this.addRoomItem(Game.rum2.getItemNPC());
                }
                break;
            case 3:
                if(Game.rum3.passaItem()) {
                    this.addRoomItem(Game.rum3.getItemNPC());
                }
                break;
            case 4:
                if(Game.rum4.passaItem()) {
                    this.addRoomItem(Game.rum4.getItemNPC());
                }
                break;
        }
    }

    public void addRoomItem(GameObject t){
        Stream<GameObject> myStreamX4444 = Stream.of(this.playerItems.xObjects);
        this.playerItems.xObjects = myStreamX4444
                .map(x -> {if(x.getItemName() == Game.emptyCell.getItemName()){
                    return t;
                } else return x;
                })
                .distinct()
                .collect(Collectors.toList()).toArray(new GameObject[2]);

        Stream<GameObject> myStreamX3333 = Stream.of(this.playerItems.xObjects);
        this.playerItems.xObjects = myStreamX3333
                .map(x -> {if(x == null){
                    return Game.emptyCell;
                } else return x;
                })
                .collect(Collectors.toList()).toArray(new GameObject[2]);
        this.showItems();
    }

    public synchronized void showItems(){
        Stream<GameObject> myStreamRX = Stream.of(this.playerItems.xObjects);
        this.showItems = myStreamRX
                .distinct()
                .filter(x -> {if(x == Game.emptyCell){
                    return false;
                } else return true;
                })
                .collect(Collectors.toList()).toArray(new GameObject[0]);
    }

    public String printNPCItems(){
        Stream<GameObject> myStreamRXU = Stream.of(this.playerItems.xObjects);
        this.showItems = myStreamRXU
                .distinct()
                .filter(x -> {if(x == Game.emptyCell){
                    return false;
                } else return true;
                })
                .collect(Collectors.toList()).toArray(new GameObject[0]);
        String npcItems = "";
        for(int i = 0; i < showItems.length; i++){
            npcItems = npcItems +" " + showItems[i].getItemName();
        }
        return this.xname +": " + npcItems +"\n";
    }

}

