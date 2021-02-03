public class Player {

    Inventory alpha;
    int yu;

    public Player(Inventory g, int x) {
        this.alpha = g;
        this.yu = x;
        }



    public void run() {
       alpha.setInt(this.yu);
    }

}

