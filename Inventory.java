import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Inventory implements Serializable {


    private int size = 0;
    GameObject[] xObjects;

    public Inventory(int y){
    this.size = y;
    this.xObjects = new GameObject[size];
    }

    //SPARAR objekten för alla rum och spelare och npcer

}
