import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game {
    Gui gui;
    public Game(){
        this.gui = new Gui();


        xNPC Jason = new xNPC("Jason");
        xNPC Freddy = new xNPC("Freddy");
        xNPC TureSventon = new xNPC("Ture Sventon");





        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(5);
        pool.scheduleAtFixedRate(Jason ,1,2, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(Freddy ,1,2, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(TureSventon ,1,2, TimeUnit.SECONDS);




    }


  // flytta över denna till person klassen.. Man kanske har runnable här utan använder sig av getters och setters där man skickar info och flyttar dem till olika rum eftersom han inte har runnable.. 
    class xNPC extends Npc implements Runnable {
        String xname;

        public xNPC(String xname){
            this.xname = xname;
        }

        @Override
        public void run(){
            System.out.println(this.xname + " says hi from room x");
        }
    }




}
