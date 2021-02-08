import java.util.Arrays;

public class Update implements Runnable{

    Inventory boom;

    public Update(Inventory z){
        this.boom = z;
    }

    @Override

    public void run(){
        System.out.println(this.boom.getInt());
       // System.out.println(Arrays.toString(this.boom.getRoom()));
        System.out.println(Arrays.toString(this.boom.getStream()));

        System.out.println(Game.rum1.getRoom());
        System.out.println(Game.rum2.getRoom());
        System.out.println(Game.rum3.getRoom());
        System.out.println(Game.rum4.getRoom());
    }

}
