import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Person extends Npc implements Runnable {
    String xname;
    int size;
    Inventory iTest;
    int currentrum;
    Inventory npcItems;
    GameObject[] showItems;

    public Person(String xname, int y, Inventory o, int x){
        this.xname = xname;
        this.size = y;
        this.iTest = o;
        this.currentrum = x;
        this.npcItems = new Inventory(2);
    }

    @Override
    public void run(){
        boolean pickUpItemOrNot = (Math.random() < 0.5);


        leaveRoom();
        goToRoom();

        showItems();
        if(pickUpItemOrNot && this.showItems.length < 2) {
        getRoomItem();
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



    public void addRoomItem(GameObject t){
        Stream<GameObject> myStreamX = Stream.of(this.npcItems.xObjects);
        this.npcItems.xObjects = myStreamX
                .map(x -> {if(x.getItemName() == Game.emptyCell.getItemName()){
                    return t;
                } else return x;
                })
                .distinct()
                .collect(Collectors.toList()).toArray(new GameObject[2]);

        this.npcItems.xObjects = myStreamX
                .map(x -> {if(x == null){
                    return Game.emptyCell;
                } else return x;
                })
                .collect(Collectors.toList()).toArray(new GameObject[2]);
    }



    public void defaultFillInventory(){
        Arrays.fill(this.npcItems.xObjects, Game.emptyCell);
    }

    public void getRoomItem(){
        switch(this.currentrum){
            case 1:
                this.addRoomItem(Game.rum1.getItemNPC());
                break;
            case 2:
                this.addRoomItem(Game.rum2.getItemNPC());
                break;
            case 3:
                this.addRoomItem(Game.rum3.getItemNPC());
                break;
            case 4:
                this.addRoomItem(Game.rum4.getItemNPC());
                break;
        }
    }

    public synchronized void dropNPCItem(GameObject d){
        Stream<GameObject> myStreamX23 = Stream.of(this.npcItems.xObjects);
        this.npcItems.xObjects = myStreamX23
                .map(x -> {if(x.getItemName() == d.getItemName()){
                    return Game.emptyCell;
                } else return x;
                })
                .collect(Collectors.toList()).toArray(new GameObject[0]);
    }

    public int showItems(){
        Stream<GameObject> myStreamRX = Stream.of(this.npcItems.xObjects);
        this.showItems = myStreamRX
                .distinct()
                .filter(x -> {if(x == Game.emptyCell){
                    return false;
                } else return true;
                })
                .collect(Collectors.toList()).toArray(new GameObject[0]);
    }

}
