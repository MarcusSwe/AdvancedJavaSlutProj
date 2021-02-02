import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game {
    Gui gui;
    public Game(){
        this.gui = new Gui();


        Person Jason = new Person("Jason");
        Person Freddy = new Person("Freddy");
        Person TureSventon = new Person("Ture Sventon");





        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(5);
        pool.scheduleAtFixedRate(Jason ,1,2, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(Freddy ,1,2, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(TureSventon ,1,2, TimeUnit.SECONDS);




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
