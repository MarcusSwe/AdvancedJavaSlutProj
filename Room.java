import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Room implements Serializable {

    private String[] roomtest = {"","","",""};
    private String roomname;
    Inventory roomItems;
    Inventory iTest;
    GameObject[] showItems;
    String xDoor = "";
    int passaItem = 0; //se förklaring nedan..
    String rumNamn = "";

    public Room(String x, Inventory m){
        this.roomname = x;
        this.iTest = m;
        this.roomItems = new Inventory(20);

    }

   // se dörr till detta rum
    public void giveDoor(){
        this.xDoor = "Dörr";
    }

    // metoden som spelaren och npc använder för tala om dem är i rummet..
    public synchronized void setRoom(String y){
        Stream<String> myStream2 = Stream.of(this.roomtest);

        this.roomtest = myStream2
                .map(x -> {if(x.equals("") || x == null){
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

    //tar bort indivinden som lämnar rummet..
    public synchronized void leaveRoom(String y){
        Stream<String> myStream2 = Stream.of(this.roomtest);
        this.roomtest = myStream2
                .map(x -> {if(x.equals(y)){
                    return "";
                } else return x;
                })
                .collect(Collectors.toList()).toArray(new String[4]);
    }

    //talar om vem som är i rummet..bygger string och returear..
    public synchronized String getRoom() {
        Stream<String> myStream3 = Stream.of(this.roomtest);
        String[] currentRoomMembers = myStream3
                .distinct()
                .collect(Collectors.toList()).toArray(new String[0]);
        rumNamn = "";
        for(int i = 0; i < currentRoomMembers.length; i++){
            if(currentRoomMembers[i].equals("MEEEEE")) {
                rumNamn = rumNamn + "\n" + currentRoomMembers[i]+"("+Game.jAg.printNPCItems()+")";
            }
            if(currentRoomMembers[i].equals("Jason")) {
                rumNamn = rumNamn + "\n" + currentRoomMembers[i]+" says: Hi there! " +"("+Game.Jason.printNPCItems()+")";
            }
            if(currentRoomMembers[i].equals("Ture Sventon")) {
                rumNamn = rumNamn + "\n" + currentRoomMembers[i]+" says: This is my loot! "+"("+Game.TureSventon.printNPCItems()+")";
            }
            if(currentRoomMembers[i].equals("Freddy")) {
                rumNamn = rumNamn + "\n" + currentRoomMembers[i]+" says: Im looking for the key! "+"("+Game.Freddy.printNPCItems()+")";
            }
        }

        return this.rumNamn;
    }


   //metod som tillkallas av individen för att ta emot droppade items..
    public synchronized void addRoomItem(GameObject t){
     Stream<GameObject> myStreamX = Stream.of(this.roomItems.xObjects);
     this.roomItems.xObjects = myStreamX
             .map(x -> {if(x.getItemName().equals(Game.emptyCell.getItemName())){
                 return t;
             } else return x;
             })
             .distinct()
             .collect(Collectors.toList()).toArray(new GameObject[20]);

        Stream<GameObject> myStreamX2 = Stream.of(this.roomItems.xObjects);

        this.roomItems.xObjects = myStreamX2
                .map(x -> {if(x == null){
                    return Game.emptyCell;
                } else return x;
                })
                .collect(Collectors.toList()).toArray(new GameObject[20]);
         passaItem++;
    }

    public synchronized void removeRoomItem(GameObject d){
        Stream<GameObject> myStreamX = Stream.of(this.roomItems.xObjects);
        this.roomItems.xObjects = myStreamX
                .map(x -> {if(x.getItemName().equals(d.getItemName())){
                    return Game.emptyCell;
                } else return x;
                })
                .collect(Collectors.toList()).toArray(new GameObject[20]);
    }

    //printar ut itemsen
    public synchronized String showRoomItem(){
        Stream<GameObject> myStreamR = Stream.of(this.roomItems.xObjects);
        this.showItems = myStreamR
                .distinct()
                .filter(x -> {if(x.getItemName().equals(Game.emptyCell.getItemName())){
                    return false;
                } else return true;
                })
                .collect(Collectors.toList()).toArray(new GameObject[0]);
        String rumItems = "";
        for(int i = 0; i < showItems.length; i++){
            rumItems = rumItems +" " + showItems[i].getItemName();
        }
        return roomname +": " + xDoor + rumItems +"\n";
    }

    public synchronized GameObject getItemNPC(){
        Stream<GameObject> myStreamR = Stream.of(this.roomItems.xObjects);
        this.showItems = myStreamR
                .distinct()
                .filter(x -> {if(x.getItemName().equals(Game.emptyCell.getItemName())){
                    return false;
                } else return true;
                })
                .collect(Collectors.toList()).toArray(new GameObject[0]);
        GameObject npcPickup = showItems[(int) (Math.random()* showItems.length)];
        this.removeRoomItem(npcPickup);
      return npcPickup;
    }

    //S i slutet menar.. spelaren tar upp speciellt item!
    public synchronized GameObject getItemS(String U){
        Stream<GameObject> myStreamR = Stream.of(this.roomItems.xObjects);
        GameObject[] specific = myStreamR
                .distinct()
                .filter(x -> {if(x.getItemName().equals(U)){
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
            this.removeRoomItem(npcPickup);
            return npcPickup;
        } else {
            Game.gui.setShowPersons2("Item finns ej här!");
            return Game.emptyCell;}
    }

    public void defaultFillInventory(){
        Arrays.fill(this.roomItems.xObjects, Game.emptyCell);
    }

    // OKEJ.. denna metod uppkom för det uppstod ett fel när två NPC tog upp ett item samtidigt trotts sync
    //på metoderna.. så denna körs först och kollar om det finns items i rummet..och sedan minskar antalet..
    //så är den 0 så går man inte vidare tilll metoden som tar upp itemsen och skapar problem..
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
                .filter(x -> {if(x.getItemName().equals(Game.emptyCell.getItemName())){
                    return false;
                } else return true;
                })
                .collect(Collectors.toList()).toArray(new GameObject[0]);
    }

    public void pickNpcItem(){

    }

}
