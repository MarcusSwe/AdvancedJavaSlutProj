import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game {
    Gui gui;
    static Inventory xTest = new Inventory(10);
    static Player jAg = new Player(xTest, 1000000);

    public Game(){
        this.gui = new Gui();



        Person Jason = new Person("Jason", 10, xTest);
        Person Freddy = new Person("Freddy", 100, xTest);
        Person TureSventon = new Person("Ture Sventon", 400, xTest);

        Update updateGUI = new Update(xTest);



        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(5);
        pool.scheduleAtFixedRate(updateGUI ,1,5, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(Jason ,1,4, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(Freddy ,1,4, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(TureSventon ,1,4, TimeUnit.SECONDS);





    }

     public static void UTEST() {
         jAg.run();
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
