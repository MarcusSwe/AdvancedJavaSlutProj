import java.io.Serializable;
import java.util.Arrays;

public class Update implements Runnable, Serializable {

    Inventory boom;

    public Update(Inventory z){
        this.boom = z;
    }

    @Override

    public synchronized void run(){
       // System.out.println(this.boom.getInt());
       // System.out.println(Arrays.toString(this.boom.getRoom()));
        //System.out.println(Arrays.toString(this.boom.getStream()));

        System.out.println(Game.rum1.getRoom());
        System.out.println(Game.rum2.getRoom());
        System.out.println(Game.rum3.getRoom());
        System.out.println(Game.rum4.getRoom());


        switch(Game.jAg.giveRoom()){
            case 1:
                Game.gui.setShowRoom(Game.rum1.showRoomItem() + Game.rum1.getRoom());
                break;
            case 2:
                Game.gui.setShowRoom(Game.rum2.showRoomItem() + Game.rum2.getRoom());
                break;
            case 3:
                Game.gui.setShowRoom(Game.rum3.showRoomItem() + Game.rum3.getRoom());
                break;
            case 4:
                Game.gui.setShowRoom(Game.rum4.showRoomItem() + Game.rum4.getRoom());
                break;
        }

        String showRooms = Game.rum1.showRoomItem() +" " + Game.rum2.showRoomItem() + " "+
                           Game.rum3.showRoomItem() + " " + Game.rum4.showRoomItem();


        String showNpcItems = "Freddy: "+Game.Freddy.printNPCItems() +"\n"+ "Ture Sventon: "+Game.TureSventon.printNPCItems() +"\n" +
                "Jason says: "+Game.Jason.printNPCItems() +"\n" + "MEEEEE: "+Game.jAg.printNPCItems();

        Game.gui.setShowInventory(showRooms + showNpcItems);
        Game.gui.setShowPersons("Mitt inventory: "+Game.jAg.printNPCItems());






    }

}
