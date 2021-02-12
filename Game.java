import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game implements Serializable{

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
    static Update updateGUI = new Update(xTest);
    static String filename = "loadFile";

    // Initierar arrayer osv Men startar ej spelet väntar på användaren tryck start.
    public Game(){

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

        Container doorToFinish = new Container("Tandborste", true, true);
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

        rum1.updateRoomItems();
        rum2.updateRoomItems();
        rum3.updateRoomItems();
        rum4.updateRoomItems();
        randomItemsToRooms();


        //Dörr till random rum
        int door = (int)((Math.random()*4)+1);
        switch(door){
            case 1:
                Game.rum1.giveDoor();
            break;
            case 2:
                Game.rum2.giveDoor();
            break;
            case 3:
                Game.rum3.giveDoor();
            break;
            case 4:
                Game.rum4.giveDoor();
            break;
        }

    }

    //Laddar alla sparfiler på en gång
    public static Player loadFile(){
        JFileChooser LF = new JFileChooser("/myfilepath");
        LF.showOpenDialog(null);
        File file = LF.getSelectedFile();
        filename = file.getName();

        loadRum1();
        loadRum2();
        loadRum3();
        loadRum4();
        loadFreddy();
        loadJason();
        loadTureSventon();

        FileInputStream fis = null;

        try{

            fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            jAg = (Player) ois.readObject();
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
        return jAg;

    }

    public static Room loadRum1(){
        FileInputStream fis = null;

        try{

            fis = new FileInputStream(filename+"rum1");
            ObjectInputStream ois = new ObjectInputStream(fis);
            rum1 = (Room) ois.readObject();
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
        return rum1;
    }

    public static Room loadRum2(){
        FileInputStream fis = null;

        try{

            fis = new FileInputStream(filename+"rum2");
            ObjectInputStream ois = new ObjectInputStream(fis);
            rum2 = (Room) ois.readObject();
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
        return rum2;
    }

    public static Room loadRum3(){
        FileInputStream fis = null;

        try{

            fis = new FileInputStream(filename+"rum3");
            ObjectInputStream ois = new ObjectInputStream(fis);
            rum3 = (Room) ois.readObject();
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
        return rum3;
    }

    public static Room loadRum4(){
        FileInputStream fis = null;

        try{

            fis = new FileInputStream(filename+"rum4");
            ObjectInputStream ois = new ObjectInputStream(fis);
            rum4 = (Room) ois.readObject();
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
        return rum4;
    }

    public static Person loadFreddy(){
        FileInputStream fis = null;

        try{

            fis = new FileInputStream(filename+"freddy");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Freddy = (Person) ois.readObject();
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
        return Freddy;
    }

    public static Person loadJason(){
        FileInputStream fis = null;

        try{

            fis = new FileInputStream(filename+"jason");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Jason = (Person) ois.readObject();
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
        return Jason;
    }

    public static Person loadTureSventon(){
        FileInputStream fis = null;

        try{

            fis = new FileInputStream(filename+"turesventon");
            ObjectInputStream ois = new ObjectInputStream(fis);
            TureSventon = (Person) ois.readObject();
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
        return TureSventon;
    }

    //Sparar allt! Men filechooser buggar. Sparar ej till rätt katalog, utan rooten på programmet!
    public static void saveFile(){
        JFileChooser SF = new JFileChooser("/myfilepath");
        SF.showSaveDialog(null);
        File file = SF.getSelectedFile();
        filename = file.getName();

        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(jAg);
            oos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = new FileOutputStream(filename+"rum1");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(rum1);
            oos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = new FileOutputStream(filename+"rum2");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(rum2);
            oos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = new FileOutputStream(filename+"rum3");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(rum3);
            oos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = new FileOutputStream(filename+"rum4");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(rum4);
            oos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = new FileOutputStream(filename+"jason");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Jason);
            oos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = new FileOutputStream(filename+"freddy");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Freddy);
            oos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = new FileOutputStream(filename+"turesventon");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(TureSventon);
            oos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    //Startar spelet efter kommando av anvädnaren, sätter igång threads..
    public static void startGame() {
        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(5);
        pool.scheduleAtFixedRate(updateGUI, 1, 1200, TimeUnit.MILLISECONDS);
        pool.scheduleAtFixedRate(Jason, 1, 8, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(Freddy, 1, 8, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(TureSventon, 1, 8, TimeUnit.SECONDS);
    }

    //Skickar ut itemsen till rummen och nyckeln till random rum
    public static void randomItemsToRooms(){
        int alpha = (int)((Math.random()*4)+1);
        switch(alpha){
            case 1:
                rum1.addRoomItem(xTest.xObjects[0]);
                break;
            case 2:
                rum2.addRoomItem(xTest.xObjects[0]);
                break;
            case 3:
                rum3.addRoomItem(xTest.xObjects[0]);
                break;
            case 4:
                rum4.addRoomItem(xTest.xObjects[0]);
                break;
        }


        rum2.addRoomItem(xTest.xObjects[1]);
        rum4.addRoomItem(xTest.xObjects[2]);
        rum3.addRoomItem(xTest.xObjects[3]);
        rum3.addRoomItem(xTest.xObjects[4]);
        rum1.addRoomItem(xTest.xObjects[5]);
        rum2.addRoomItem(xTest.xObjects[6]);
        rum4.addRoomItem(xTest.xObjects[7]);
        rum4.addRoomItem(xTest.xObjects[8]);
    }


    //------ TEST METODER, IGNORERA, vill ha kvar dem för eget bruk
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
    //----- slut test metoeder

    // Flytta spelaren till random rum , roligare med random eftersom det blir svårare..
    public static void MOVEROOM(){
        jAg.leaveRoom();
        jAg.goToRoom();
    }

    //Welcome to the spam ;)
    public static void pDoor(){
        jAg.getRoomItemS("Tandborste");
    }
    public static void pKeyToUnlock(){
        jAg.getRoomItemS("KeyToUnlock");
    }
    public static void pParaply(){
        jAg.getRoomItemS("Paraply");
    }
    public static void pDonut(){
        jAg.getRoomItemS("Donut");
    }
    public static void pMotorsag(){
        jAg.getRoomItemS("Motorsåg");
    }
    public static void pPenna(){
        jAg.getRoomItemS("Penna");
    }
    public static void pAndriod(){
        jAg.getRoomItemS("Andriod phone");
    }
    public static void pTrojja(){
        jAg.getRoomItemS("Tröjja");
    }
    public static void pColaburk(){
        jAg.getRoomItemS("Colaburk");
    }

    public static void dDoor(){
        jAg.playerDropS("Tandborste");
    }
    public static void dKeyToUnlock(){
        jAg.playerDropS("KeyToUnlock");
    }
    public static void dParaply(){
        jAg.playerDropS("Paraply");
    }
    public static void dDonut(){
        jAg.playerDropS("Donut");
    }
    public static void dMotorsag(){
        jAg.playerDropS("Motorsåg");
    }
    public static void dPenna(){
        jAg.playerDropS("Penna");
    }
    public static void dAndriod(){
        jAg.playerDropS("Andriod phone");
    }
    public static void dTrojja(){
        jAg.playerDropS("Tröjja");
    }
    public static void dColaburk(){
        jAg.playerDropS("Colaburk");
    }

    public static void npcPDoor(){
        jAg.getNpcitem("Tandborste");
    }
    public static void npcPKeyToUnlock(){
        jAg.getNpcitem("KeyToUnlock");
    }
    public static void npcPParaply(){
        jAg.getNpcitem("Paraply");
    }
    public static void npcPDonut(){
        jAg.getNpcitem("Donut");
    }
    public static void npcPMotorsag(){
        jAg.getNpcitem("Motorsåg");
    }
    public static void npcPPenna(){
        jAg.getNpcitem("Penna");
    }
    public static void npcPAndriod(){
        jAg.getNpcitem("Andriod phone");
    }
    public static void npcPTrojja(){
        jAg.getNpcitem("Tröjja");
    }
    public static void npcPColaburk(){
        jAg.getNpcitem("Colaburk");
    }

    public static void exitDoor(){
       jAg.checkForKey();
    }





}
