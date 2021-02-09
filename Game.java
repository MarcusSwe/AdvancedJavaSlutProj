import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game {

    static Inventory xTest = new Inventory(10);
    static Player jAg = new Player(xTest, 1000000, "MEEEEE", 1);
    static Room rum1 = new Room("Rum ett");
    static Room rum2 = new Room("Rum två");
    static Room rum3 = new Room("Rum tre");
    static Room rum4 = new Room("Rum fyra");
    static Person Jason = new Person("Jason", 10, xTest,1);
    static Person Freddy = new Person("Freddy", 100, xTest,2);
    static Person TureSventon = new Person("Ture Sventon", 400, xTest,2);
    static Gui gui = new Gui();

    public Game(){
        Update updateGUI = new Update(xTest);

        rum1.setRoom("MEEEEE");



        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(5);
        pool.scheduleAtFixedRate(updateGUI ,6,6, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(Jason ,1,8, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(Freddy ,1,8, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(TureSventon ,1,8, TimeUnit.SECONDS);

    }

    public static void randomItemsToRooms(){
        Container doorToFinish = new Container("Door", true, true);
        Key keyToDoor = new Key("KeyToUnlock", false, false);
        rum1.addRoomItem(keyToDoor);
    }

    public static void printItemTest(){
        System.out.println(rum1.showRoomItem());
    }


    public static void UTEST() {
         jAg.run();
    }

    public static void YTEST() {
        jAg.startT1();
    }

    public static void XTEST() {
        jAg.run2();
    }
    public static void X2TEST() {
        jAg.run3();
    }
    public static void X3TEST() {
        jAg.run4();
    }

    /*public static void X4TEST() {
        jAg.run5();
    }*/

    public static void MOVEROOM(){
        jAg.leaveRoom();
        jAg.goToRoom();
    }



  /* flytta över denna till person klassen.. Man kanske har runnable här utan använder sig av getters och setters där man skickar info och flyttar dem till olika rum eftersom han inte har runnable..
    class xNPC extends Npc implements Runnable {
        String xname;

        public xNPC(String xname){
            this.xname = xname;
        }

        @Override
        public void run(){
            System.out.println(this.xname + " says hi from room x");
        }
    }*/




}
