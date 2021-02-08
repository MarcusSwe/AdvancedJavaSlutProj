import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Inventory {

    private int test = 0;

    private int[] streamtest = {1,2,3,4,4,5};
    private String[] roomtest = {"ost","bobo","","","no"};

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

  public void setRoom(String y){
        Stream<String> myStream2 = Stream.of(this.roomtest);
        this.roomtest = myStream2
                .map(x -> y)
                .collect(Collectors.toList()).toArray(new String[0]);






  }

  public String[] getRoom() {
        return this.roomtest;
    }



}
