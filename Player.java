import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Player implements Serializable {

    Inventory alpha;
    int size;
    ScheduledThreadPoolExecutor pool2;
    Future<?> future;
    String xname;
    int currentrum;
    Inventory playerItems;
    GameObject[] showItems; // samma som NPC.. samma sak som inventory fast utan emptycells.. för att printa
    GameObject emptyCellX;

    public Player(Inventory g, int x, String o, int r) {
        this.alpha = g;
        this.size = x;
        this.xname = o;
        this.currentrum = r;
        this.playerItems = new Inventory(5);
        this.emptyCellX = new GameObject("emptycell", false, false);
        }


    //TEST metoder nedan.. typ starta en stream och sedan deaktiverar den från GUI..bara lite roligt test jag gjorde..
    public void run() {
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

    // Guess what..
    public synchronized void goToRoom(){
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

    public synchronized void leaveRoom(){
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

    //Ger rum där spelaren är i ..
    public int giveRoom(){
        return this.currentrum;
    }

    //Fyller arrayer med emptycells..
    public void defaultFillInventory(){

        Arrays.fill(this.playerItems.xObjects, this.emptyCellX);
    }

    //Tar upp itemsen..kollar först med Passa metoden i rum..
    public synchronized void getRoomItem(){
        this.showItems();
        if(this.showItems.length < 5) {
            switch (this.currentrum) {
                case 1:
                    if (Game.rum1.passaItem()) {
                        System.out.println("ITEEEEEEEEEEEEEEM ÄR HÄR");
                        this.addRoomItem(Game.rum1.getItemNPC());
                    } else System.out.println("NO ITEMS TO PICKUP REEEEEEEETAAAAAAAAARD");
                    break;
                case 2:
                    if (Game.rum2.passaItem()) {
                        this.addRoomItem(Game.rum2.getItemNPC());
                    } else System.out.println("NO ITEMS TO PICKUP REEEEEEEETAAAAAAAAARD");
                    break;
                case 3:
                    if (Game.rum3.passaItem()) {
                        this.addRoomItem(Game.rum3.getItemNPC());
                    } else System.out.println("NO ITEMS TO PICKUP REEEEEEEETAAAAAAAAARD");
                    break;
                case 4:
                    if (Game.rum4.passaItem()) {
                        this.addRoomItem(Game.rum4.getItemNPC());
                    } else System.out.println("NO ITEMS TO PICKUP REEEEEEEETAAAAAAAAARD");
                    break;
            }
        } else Game.gui.setShowPersons2("Kan ej ta upp fler items, max 5!");
    }

    //metoden getroom tillkallar efter fått klartecken från Passa från room klassen..
    //addar till emptycells, bygger arrayen vidare genom replaya null med emptycells..
    public synchronized void addRoomItem(GameObject t){
        Stream<GameObject> myStreamX4444 = Stream.of(this.playerItems.xObjects);
        this.playerItems.xObjects = myStreamX4444
                .map(x -> {if(x.getItemName().equals(this.emptyCellX.getItemName())){
                    return t;
                } else return x;
                })
                .distinct()
                .collect(Collectors.toList()).toArray(new GameObject[5]);

        Stream<GameObject> myStreamX3333 = Stream.of(this.playerItems.xObjects);
        this.playerItems.xObjects = myStreamX3333
                .map(x -> {if(x == null){
                    return this.emptyCellX;
                } else return x;
                })
                .collect(Collectors.toList()).toArray(new GameObject[5]);
        this.showItems();
    }

    //visar arrayen.. samma som inv men utan emptycells.. för clean print..
    public synchronized void showItems(){
        Stream<GameObject> myStreamRX = Stream.of(this.playerItems.xObjects);
        this.showItems = myStreamRX
                .distinct()
                .filter(x -> {if(x.getItemName().equals(this.emptyCellX.getItemName())){
                    return false;
                } else return true;
                })
                .filter(x -> {if(x.getItemName().equals(this.emptyCellX.getItemName())){
                    return false;
                } else return true;
                })
                .collect(Collectors.toList()).toArray(new GameObject[0]);
    }

    public synchronized String printNPCItems(){
        Stream<GameObject> myStreamRXU = Stream.of(this.playerItems.xObjects);
        this.showItems = myStreamRXU
                .distinct()
                .filter(x -> {if(x.getItemName().equals(this.emptyCellX.getItemName())){
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

    //droppar itemsen och kollar först vilket rum man befinner sig i ..
    public synchronized void dropNPCItem(GameObject d){
        Stream<GameObject> myStreamX23 = Stream.of(this.playerItems.xObjects);
        this.playerItems.xObjects = myStreamX23
                .map(x -> {if(x.getItemName().equals(d.getItemName())){
                    return this.emptyCellX;
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

    public synchronized void playerDrop(){
        if(this.showItems.length > 0) {
            this.dropNPCItem(this.showItems[0]);
        } else System.out.println("NO ITEMS TO DROP RETAAAAAAAAAAAAARD");
    }

    //droppar specifikt itemsen.. skickar vidare matchat item till dropNPCItem metoden..
    public synchronized void playerDropS(String G){
        if(this.showItems.length > 0) {

            Stream<GameObject> myStreamR = Stream.of(this.playerItems.xObjects);
            GameObject[] specificP = myStreamR
                    .distinct()
                    .filter(x -> {if(Objects.equals(x.getItemName(),G)){
                        return true;
                    } else return false;
                    })
                    .filter(x -> {if(x.getItemName().equals(this.emptyCellX.getItemName())){
                        return false;
                    } else return true;
                    })
                    .collect(Collectors.toList()).toArray(new GameObject[0]);
            if (specificP.length > 0) {
                GameObject npcPickup = specificP[0];
                this.dropNPCItem(npcPickup);
            } else Game.gui.setShowPersons2("Kan ej droppa item för du har det ej på dig!");
        }
    }

    //tar upp itemsen.. Om dem heter NPC är för det copy paste med lätt modifering från NPC! Praktiskt!
    public synchronized void getRoomItemS(String Y){
        this.showItems();
        if(this.showItems.length < 5) {
            switch (this.currentrum) {
                case 1:
                    if (Game.rum1.passaItem()) {
                        System.out.println("ITEEEEEEEEEEEEEEM ÄR HÄR");
                        this.addRoomItem(Game.rum1.getItemS(Y));
                        //this.addRoomItem(Game.xTest.xObjects[0]);
                    } else System.out.println("NO ITEMS TO PICKUP REEEEEEEETAAAAAAAAARD");
                    break;
                case 2:
                    if (Game.rum2.passaItem()) {
                        this.addRoomItem(Game.rum2.getItemS(Y));
                    } else System.out.println("NO ITEMS TO PICKUP REEEEEEEETAAAAAAAAARD");
                    break;
                case 3:
                    if (Game.rum3.passaItem()) {
                        this.addRoomItem(Game.rum3.getItemS(Y));
                    } else System.out.println("NO ITEMS TO PICKUP REEEEEEEETAAAAAAAAARD");
                    break;
                case 4:
                    if (Game.rum4.passaItem()) {
                        this.addRoomItem(Game.rum4.getItemS(Y));
                    } else System.out.println("NO ITEMS TO PICKUP REEEEEEEETAAAAAAAAARD");
                    break;
            }
        } else Game.gui.setShowPersons2("Kan ej ta upp fler items, max 5!");
    }

    //snor itemsen från NPC..
    public synchronized void getNpcitem(String L){
        switch(this.currentrum){
            case 1:
                if(Game.Freddy.currentrum == 1){
                    this.addRoomItem(Game.Freddy.dropNPCItemS(L));
                }
                if(Game.TureSventon.currentrum == 1){
                    this.addRoomItem(Game.TureSventon.dropNPCItemS(L));
                }
                if(Game.Jason.currentrum == 1){
                    this.addRoomItem(Game.Jason.dropNPCItemS(L));
                }
                break;
            case 2:
                if(Game.Freddy.currentrum == 2){
                    this.addRoomItem(Game.Freddy.dropNPCItemS(L));
                }
                if(Game.TureSventon.currentrum == 2){
                    this.addRoomItem(Game.TureSventon.dropNPCItemS(L));
                }
                if(Game.Jason.currentrum == 2){
                    this.addRoomItem(Game.Jason.dropNPCItemS(L));
                }
                break;
            case 3:
                if(Game.Freddy.currentrum == 3){
                    this.addRoomItem(Game.Freddy.dropNPCItemS(L));
                }
                if(Game.TureSventon.currentrum == 3){
                    this.addRoomItem(Game.TureSventon.dropNPCItemS(L));
                }
                if(Game.Jason.currentrum == 3){
                    this.addRoomItem(Game.Jason.dropNPCItemS(L));
                }
                break;
            case 4:
                if(Game.Freddy.currentrum == 4){
                    this.addRoomItem(Game.Freddy.dropNPCItemS(L));
                }
                if(Game.TureSventon.currentrum == 4){
                    this.addRoomItem(Game.TureSventon.dropNPCItemS(L));
                }
                if(Game.Jason.currentrum == 4){
                    this.addRoomItem(Game.Jason.dropNPCItemS(L));
                }
                break;
        }
    }

    //Victory CHECK!
    public synchronized void checkForKey() {
        Stream<GameObject> myStreamR = Stream.of(this.playerItems.xObjects);
        GameObject[] specific = myStreamR
                .distinct()
                .filter(x -> {
                    if (x.getItemName().equals("KeyToUnlock")) {
                        return true;
                    } else return false;
                })
                .filter(x -> {
                    if (x.getItemName().equals(this.emptyCellX.getItemName())) {
                        return false;
                    } else return true;
                })
                .collect(Collectors.toList()).toArray(new GameObject[0]);
        if (specific.length > 0) {
            Game.gui.setShowPersons2("Du hitta ut! Grattis!");
        } else {
            Game.gui.setShowPersons2("Fel rum eller ingen nyckel!");
        }
    }



}

