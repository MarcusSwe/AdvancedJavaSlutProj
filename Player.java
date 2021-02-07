import jdk.nashorn.api.tree.FunctionCallTree;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Player {

    Inventory alpha;
    int yu;
    ScheduledThreadPoolExecutor pool2;
    Future<?> future;

    public Player(Inventory g, int x) {
        this.alpha = g;
        this.yu = x;
        }



    public void run() {
       alpha.setInt(this.yu);
       alpha.setStream(2);
    }

    public void run2(){
        this.future = this.pool2.scheduleAtFixedRate(new segHej(), 1, 5, TimeUnit.SECONDS);


    }

    public void run3(){
        this.future.cancel(true);
    }

    public void run4(){
        this.pool2.shutdown();
    }

    public void startT1() {
        this.pool2 = new ScheduledThreadPoolExecutor(2);
        pool2.scheduleAtFixedRate(new segHej(), 1, 5, TimeUnit.SECONDS);

    }

    private class segHej implements Runnable{
        @Override
        public void run() {
            System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOORKA");
        }

    }



}

