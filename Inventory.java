import java.util.Arrays;
import java.util.stream.IntStream;

public class Inventory {

    private int test = 0;

    private int[] streamtest = {1,2,3,4,4,5};

    public Inventory(int y){

    this.test = y;

    }

    public int getInt() {
        return this.test;
    }

    public void setStream(int z){
        IntStream myStream = Arrays.stream(this.streamtest);
        this.streamtest = myStream
                .map(x -> x*z)
                .toArray();
    }

    public int[] getStream(){
        return this.streamtest;
    }

    public void setInt(int y){
        this.test = this.test + y;
    }
}
