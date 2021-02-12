import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Person extends Npc implements Runnable, Serializable {
    String xname;
    int size;
    Inventory iTest;
    int currentrum = 1;
    Inventory npcItems;
    GameObject[] showItems = new GameObject[2]; /*Denna array används för printa ut itemsen..Så streamar över
                                                  riktiga inven och filtrerar bort emptycells*/

    public Person(String xname, int y, Inventory o, int x){
        this.xname = xname;
        this.size = y;
        this.iTest = o;
        this.currentrum = x;
        this.npcItems = new Inventory(2);
    }

    @Override
    public void run(){
        int pickUpItemOrNot = (int)((Math.random() *3)+1);
        this.leaveRoom(); //guess what
        this.goToRoom();
        this.showItems();

        // droppar itemsen på random och om man har något på sig
        if(this.showItems.length > 0 && pickUpItemOrNot == 1){
            if(this.showItems.length == 1) {
                dropNPCItem(this.showItems[0]);
            } else {
                dropNPCItem(this.showItems[(int) ((Math.random()*2))]);
            }
        }

      // tar upp itemsen random och NPC inte har fler än 2 på sig..
      if(pickUpItemOrNot == 2 && this.showItems.length < 2) {
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
       // System.out.println(this.xname + " says hi from room "+ z);
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


    //Addar room itemsen till sin array..
    public void addRoomItem(GameObject t){
        Stream<GameObject> myStreamX4444 = Stream.of(this.npcItems.xObjects);
        this.npcItems.xObjects = myStreamX4444
                .map(x -> {if(x.getItemName().equals(Game.emptyCell.getItemName())){
                    return t;
                } else return x;
                })
                .distinct()
                .collect(Collectors.toList()).toArray(new GameObject[2]);

        Stream<GameObject> myStreamX3333 = Stream.of(this.npcItems.xObjects);
        this.npcItems.xObjects = myStreamX3333
                .map(x -> {if(x == null){
                    return Game.emptyCell;
                } else return x;
                })
                .collect(Collectors.toList()).toArray(new GameObject[2]);
        this.showItems();
    }


    //Fyller arraysen med emptycells, start sak..
    public void defaultFillInventory(){
        Arrays.fill(this.npcItems.xObjects, Game.emptyCell);
        Arrays.fill(this.showItems, Game.emptyCell);
    }

    //tar upp itemsen från rummet där man är i.. skickar vidare till additemsen metoden..
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

    //droppar itemsen och replacar med emptycell..kontrollerar vilket rum man är i och droppar där..
    public synchronized void dropNPCItem(GameObject d){
        Stream<GameObject> myStreamX23 = Stream.of(this.npcItems.xObjects);
        this.npcItems.xObjects = myStreamX23
                .map(x -> {if(x.getItemName().equals(d.getItemName())){
                    return Game.emptyCell;
                } else return x;
                })
                .collect(Collectors.toList()).toArray(new GameObject[0]);

        switch(this.currentrum){
            case 1:
                System.out.println("111111111111");
                    Game.rum1.addRoomItem(d);
                break;
            case 2:
                System.out.println("2222222222222");
                    Game.rum2.addRoomItem(d);
                break;
            case 3:
                System.out.println("3333333333333");
                    Game.rum3.addRoomItem(d);
                break;
            case 4:
                System.out.println("444444444444");
                    Game.rum4.addRoomItem(d);
                break;
        }
       this.showItems();
    }

    //uppdaterar show arrayen.. tar vanliga inven arrayen och streamar bort emptycells..
    public synchronized void showItems(){
        Stream<GameObject> myStreamRX = Stream.of(this.npcItems.xObjects);
        this.showItems = myStreamRX
                .distinct()
                .filter(x -> {if(x.getItemName().equals(Game.emptyCell.getItemName())){
                    return false;
                } else return true;
                })
                .collect(Collectors.toList()).toArray(new GameObject[0]);
    }

    //printar ut NPC itemsen till GUI genom skicka vidare strängen som byggs upp..
    public String printNPCItems(){
        Stream<GameObject> myStreamRXU = Stream.of(this.npcItems.xObjects);
        this.showItems = myStreamRXU
                .distinct()
                .filter(x -> {if(x.getItemName().equals(Game.emptyCell.getItemName())){
                    return false;
                } else return true;
                })
                .collect(Collectors.toList()).toArray(new GameObject[0]);
        String npcItems = "";
        for(int i = 0; i < showItems.length; i++){
            npcItems = npcItems +" " + showItems[i].getItemName();
        }
        return npcItems;
    }

    // denna metod används när spelaren snor itemsen från NPC..
    public synchronized GameObject dropNPCItemS(String P){
        Stream<GameObject> myStreamR = Stream.of(this.npcItems.xObjects);
        GameObject[] specific = myStreamR
                .distinct()
                .filter(x -> {if(x.getItemName().equals(P)){
                    return true;
                } else return false;
                })
                .filter(x -> {if(x.getItemName().equals(Game.emptyCell.getItemName())){
                    return false;
                } else return true;
                })
                .collect(Collectors.toList()).toArray(new GameObject[0]);
        if (specific.length > 0) {
            GameObject npcPickup = specific[0];
            this.dropNPCItemToPlayer(npcPickup);
            return npcPickup;
        } else {
            Game.gui.setShowPersons2("Item finns ej här!");
            return Game.emptyCell;}
    }

    //se ovan..
    public synchronized void dropNPCItemToPlayer(GameObject d){
        Stream<GameObject> myStreamX23 = Stream.of(this.npcItems.xObjects);
        this.npcItems.xObjects = myStreamX23
                .map(x -> {if(x.getItemName().equals(d.getItemName())){
                    return Game.emptyCell;
                } else return x;
                })
                .collect(Collectors.toList()).toArray(new GameObject[0]);

        this.showItems();
    }


}
