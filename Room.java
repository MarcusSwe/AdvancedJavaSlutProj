import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Room {

    private String[] roomtest = {"","","",""};
    private String roomname;

    public Room(String x){
        this.roomname = x;

    }


    public synchronized void setRoom(String y){
        Stream<String> myStream2 = Stream.of(this.roomtest);

        this.roomtest = myStream2
                .map(x -> {if(x == "" || x == null){
                    return y;
                } else return x;
                })
                .distinct()
                .collect(Collectors.toList()).toArray(new String[4]);

        Stream<String> myStream3 = Stream.of(this.roomtest);

        this.roomtest = myStream3
                .map(x -> {if(x == null){
                    return "";
                } else return x;
                })
                .collect(Collectors.toList()).toArray(new String[4]);

    }

    public synchronized void leaveRoom(String y){
        Stream<String> myStream2 = Stream.of(this.roomtest);
        this.roomtest = myStream2
                .map(x -> {if(x == y){
                    return "";
                } else return x;
                })
                .collect(Collectors.toList()).toArray(new String[4]);

    }

    public synchronized String getRoom() {
        Stream<String> myStream3 = Stream.of(this.roomtest);
        String[] currentRoomMembers = myStream3
                .distinct()
                .collect(Collectors.toList()).toArray(new String[0]);
        String rumNamn = "";
        for(int i = 0; i < currentRoomMembers.length; i++){
            rumNamn = rumNamn +"\n" + currentRoomMembers[i];
        }

        return roomname +": "+ rumNamn;
    }



}
