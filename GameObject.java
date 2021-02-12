import java.io.Serializable;

public class GameObject implements Serializable {

    private boolean lockedOrNot;
    private boolean stationary;
    private String item;


    public GameObject(String y, Boolean x, Boolean z){
        this.lockedOrNot = x;
        this.item = y;
        this.stationary = z;

    }
   //looked or not och stationary ej använda pga brist på tid pga börja alldeles för sent. Ligger fint här dock för framtida use..

    public boolean getBoolean(){
        return this.lockedOrNot;
     }

    public String getItemName(){
        return this.item;
     }

    public void setBoolean(boolean b){
        this.lockedOrNot = b;
    }

    public void setItemName(String n){
        this.item = n;
    }

    public boolean isStationary() {
        return stationary;
    }

    public void setStationary(boolean stationary) {
        this.stationary = stationary;
    }

}
