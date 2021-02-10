import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Room<q> {

    private String[] roomtest = {"","","",""};
    private String roomname;
    Inventory roomItems;
    Inventory iTest;
    GameObject[] showItems;
    int passaItem = 0;
    String rumNamn = "";

    public Room(String x, Inventory m){
        this.roomname = x;
        this.iTest = m;
        this.roomItems = new Inventory(20);





        //this.roomItems.xObjects[0] = new GameObject("TEST",false,false);
    }


    public synchronized void setRoom(String y){
        Stream<String> myStream2 = Stream.of(this.roomtest);

        this.roomtest = myStream2
                .map(x -> {if(x == "" || x == null){
                    return y;
                } else return x;
                })
                .distinct()
                .collect(Collectors.toList()).toArray(new String[4]);

        Stream<String> myStream3 = Stream.of(this.roomtest);

        this.roomtest = myStream3
                .map(x -> {if(x == null){
                    return "";
                } else return x;
                })
                .collect(Collectors.toList()).toArray(new String[4]);
    }

    public synchronized void leaveRoom(String y){
        Stream<String> myStream2 = Stream.of(this.roomtest);
        this.roomtest = myStream2
                .map(x -> {if(x == y){
                    return "";
                } else return x;
                })
                .collect(Collectors.toList()).toArray(new String[4]);
    }

    public synchronized String getRoom() {
        Stream<String> myStream3 = Stream.of(this.roomtest);
        String[] currentRoomMembers = myStream3
                .distinct()
                .collect(Collectors.toList()).toArray(new String[0]);
        rumNamn = "";
        for(int i = 0; i < currentRoomMembers.length; i++){
            rumNamn = rumNamn +"\n" + currentRoomMembers[i];
        }

        return this.roomname +": "+ this.rumNamn;
    }



    public synchronized void addRoomItem(GameObject t){
     Stream<GameObject> myStreamX = Stream.of(this.roomItems.xObjects);
     this.roomItems.xObjects = myStreamX
             .map(x -> {if(x.getItemName() == Game.emptyCell.getItemName()){
                 return t;
             } else return x;
             })
             .distinct()
            //  .map(x -> {x.setItemName("XXXXXX"); return x;})
         //    .map(x -> {x.setBoolean(false); return x;})
           //  .map(x -> {x.setStationary(false); return x;})
             .collect(Collectors.toList()).toArray(new GameObject[20]);

        Stream<GameObject> myStreamX2 = Stream.of(this.roomItems.xObjects);

        this.roomItems.xObjects = myStreamX2
                .map(x -> {if(x == null){
                    return Game.emptyCell;
                } else return x;
                })
                .collect(Collectors.toList()).toArray(new GameObject[20]);
         passaItem++;
         System.out.println(passaItem + "           CPPPPPPPPPPPPPPPPPPPPPPPPPPPp");
    }

    public synchronized void removeRoomItem(GameObject d){
        Stream<GameObject> myStreamX = Stream.of(this.roomItems.xObjects);
        this.roomItems.xObjects = myStreamX
                .map(x -> {if(x.getItemName() == d.getItemName()){
                    return Game.emptyCell;
                } else return x;
                })
                .collect(Collectors.toList()).toArray(new GameObject[20]);
    }

    public synchronized String showRoomItem(){
        Stream<GameObject> myStreamR = Stream.of(this.roomItems.xObjects);
        this.showItems = myStreamR
                .distinct()
                .filter(x -> {if(x == Game.emptyCell){
                    return false;
                } else return true;
                })
                .collect(Collectors.toList()).toArray(new GameObject[0]);
        String rumItems = "";
        for(int i = 0; i < showItems.length; i++){
            rumItems = rumItems +" " + showItems[i].getItemName();
        }
        return roomname +": " + rumItems +"\n";
    }

    public synchronized GameObject getItemNPC(){
        Stream<GameObject> myStreamR = Stream.of(this.roomItems.xObjects);
        this.showItems = myStreamR
                .distinct()
                .filter(x -> {if(x == Game.emptyCell){
                    return false;
                } else return true;
                })
                .collect(Collectors.toList()).toArray(new GameObject[0]);
        GameObject npcPickup = showItems[(int) (Math.random()* showItems.length)];
        this.removeRoomItem(npcPickup);
      return npcPickup;
    }

    public void defaultFillInventory(){
        Arrays.fill(this.roomItems.xObjects, Game.emptyCell);
    }

    public synchronized boolean passaItem(){

        if (this.passaItem >0) {
            this.passaItem--;
            System.out.println(passaItem + "          CPPPPPPPPPPPPPPPPPPPPPPPPPPPp");
            return true;
        } else return false;
    }

    public synchronized void updateRoomItems(){
        Stream<GameObject> myStreamRTY = Stream.of(this.roomItems.xObjects);
        this.showItems = myStreamRTY
                .distinct()
                .filter(x -> {if(x == Game.emptyCell){
                    return false;
                } else return true;
                })
                .collect(Collectors.toList()).toArray(new GameObject[0]);
    }

}
