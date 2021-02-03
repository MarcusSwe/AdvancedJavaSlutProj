public class Inventory {

    private int test = 0;


    public Inventory(int y){

    this.test = y;

    }

    public int getInt() {
        return this.test;
    }

    public void setInt(int y){
        this.test = this.test + y;
    }
}
