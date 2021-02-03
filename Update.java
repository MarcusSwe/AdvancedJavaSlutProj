public class Update implements Runnable{

    Inventory boom;

    public Update(Inventory z){
        this.boom = z;
    }

    @Override

    public void run(){
        System.out.println(this.boom.getInt());
    }

}
