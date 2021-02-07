import java.util.Arrays;

public class Update implements Runnable{

    Inventory boom;

    public Update(Inventory z){
        this.boom = z;
    }

    @Override

    public void run(){
        System.out.println(this.boom.getInt());
        System.out.println(Arrays.toString(this.boom.getStream()));
    }

}
