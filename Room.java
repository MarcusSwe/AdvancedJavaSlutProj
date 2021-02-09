import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Room<q> {

    private String[] roomtest = {"","","",""};
    private String roomname;
    Inventory roomItems;
    Inventory iTest;

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
        String rumNamn = "";
        for(int i = 0; i < currentRoomMembers.length; i++){
            rumNamn = rumNamn +"\n" + currentRoomMembers[i];
        }

        return roomname +": "+ rumNamn;
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
        String q = this.roomItems.xObjects[0].getItemName();
        return q;
    }

    public void defaultFillInventory(){
        Arrays.fill(this.roomItems.xObjects, Game.emptyCell);
    }


}
