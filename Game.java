import java.util.Arrays;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game {

    static Inventory xTest = new Inventory(100);
    static Player jAg = new Player(xTest, 1000000, "MEEEEE", 1);
    static Room rum1 = new Room("Rum ett", xTest);
    static Room rum2 = new Room("Rum två", xTest);
    static Room rum3 = new Room("Rum tre", xTest);
    static Room rum4 = new Room("Rum fyra", xTest);
    static Person Jason = new Person("Jason", 10, xTest,1);
    static Person Freddy = new Person("Freddy", 100, xTest,2);
    static Person TureSventon = new Person("Ture Sventon", 400, xTest,2);
    static GameObject emptyCell = new GameObject("emptycell", false, false);
    static Gui gui = new Gui();

    public Game(){
        Update updateGUI = new Update(xTest);

        rum1.setRoom("MEEEEE");

        rum1.defaultFillInventory();
        rum2.defaultFillInventory();
        rum3.defaultFillInventory();
        rum4.defaultFillInventory();
        jAg.defaultFillInventory();
        Jason.defaultFillInventory();
        Freddy.defaultFillInventory();
        TureSventon.defaultFillInventory();


        Arrays.fill(xTest.xObjects, Game.emptyCell);

        Container doorToFinish = new Container("Door", true, true);
        Key keyToDoor = new Key("KeyToUnlock", false, false);
        GameObject item1 = new GameObject("Paraply", false, false);
        GameObject item2 = new GameObject("Donut", false, false);
        GameObject item3 = new GameObject("Motorsåg", false, false);
        GameObject item4 = new GameObject("Penna", false, false);
        GameObject item5 = new GameObject("Andriod phone", false, false);
        GameObject item6 = new GameObject("Tröjja", false, false);
        GameObject item7 = new GameObject("Colaburk", false, false);



        xTest.xObjects[0] = keyToDoor;
        xTest.xObjects[1] = doorToFinish;
        xTest.xObjects[2] = item1;
        xTest.xObjects[3] = item2;
        xTest.xObjects[4] = item3;
        xTest.xObjects[5] = item4;
        xTest.xObjects[6] = item5;
        xTest.xObjects[7] = item6;
        xTest.xObjects[8] = item7;







        //* dela ut items till rummet här innan updateroomsItems...

        rum1.updateRoomItems();
        rum2.updateRoomItems();
        rum3.updateRoomItems();
        rum4.updateRoomItems();
        randomItemsToRooms();


        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(5);
        pool.scheduleAtFixedRate(updateGUI ,1,2, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(Jason ,1,8, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(Freddy ,1,8, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(TureSventon ,1,8, TimeUnit.SECONDS);



    }

    public static void randomItemsToRooms(){
        rum1.addRoomItem(xTest.xObjects[0]);
        rum2.addRoomItem(xTest.xObjects[1]);
        rum4.addRoomItem(xTest.xObjects[2]);
        rum3.addRoomItem(xTest.xObjects[3]);
        rum3.addRoomItem(xTest.xObjects[4]);
        rum1.addRoomItem(xTest.xObjects[5]);
        rum2.addRoomItem(xTest.xObjects[6]);
        rum4.addRoomItem(xTest.xObjects[7]);
        rum4.addRoomItem(xTest.xObjects[8]);
    }

    public static void randomItemsToRooms2(){
        jAg.getRoomItem();
    }

    public static void randomItemsToRooms3(){
        jAg.playerDrop();
    }


    public static void printItemTest(){
        System.out.println(rum1.showRoomItem());
    }

    public static void removeItem(){

        rum1.removeRoomItem(xTest.xObjects[0]);
        rum2.removeRoomItem(xTest.xObjects[1]);
        rum3.removeRoomItem(xTest.xObjects[0]);
        rum4.removeRoomItem(xTest.xObjects[0]);
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

    public static void pDoor(){

    }
    public static void pKeyToUnlock(){

    }
    public static void pParaply(){

    }
    public static void pDonut(){

    }
    public static void pMotorsag(){

    }
    public static void pPenna(){

    }
    public static void pAndriod(){

    }
    public static void pTrojja(){

    }
    public static void pColaburk(){

    }

    public static void dDoor(){

    }
    public static void dKeyToUnlock(){

    }
    public static void dParaply(){

    }
    public static void dDonut(){

    }
    public static void dMotorsag(){

    }
    public static void dPenna(){

    }
    public static void dAndriod(){

    }
    public static void dTrojja(){

    }
    public static void dColaburk(){

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
