import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Inventory implements Serializable {


    private int size = 0;
    GameObject[] xObjects;

    //private int[] streamtest = {1,2,3,4,4,5};
    //private String[] roomtest = {"ost","bobo","",""};

    public Inventory(int y){
    this.size = y;
    this.xObjects = new GameObject[size];
    }


    /*public synchronized int getInt() {
        return this.test;
    }

    public synchronized void setStream(int z){
        IntStream myStream = Arrays.stream(this.streamtest);
        this.streamtest = myStream
                .map(x -> x*z)
                .toArray();
    }

    public synchronized int[] getStream(){
        return this.streamtest;
    }

    public synchronized void setInt(int y){
        this.test = this.test + y;
    }

  public synchronized void setRoom(String y){
        Stream<String> myStream2 = Stream.of(this.roomtest);
        this.roomtest = myStream2
                .map(x -> {if(x == ""){
                    return y;
                } else return x;
                 })
                .collect(Collectors.toList()).toArray(new String[0]);

  }

  public synchronized String[] getRoom() {
        return this.roomtest;
    }

*/

}
