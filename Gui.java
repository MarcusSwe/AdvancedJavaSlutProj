import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

/*Extremt enkelt Gui för att kunna komma igång.
Snygga gärna till/gör ett eget. Men tänk på att gör GUI:s INTE är ett kursmoment - så fastna inte här!
 */


    public class Gui extends JFrame {

        private JPanel panel;
        private JTextArea showRoom;
        private JTextArea showPersons;
        private JTextField input;
        private JTextArea inventory;
        private String command;
        private boolean gotCommand;
        private JButton button;
        public Gui(){
            this.gotCommand = false;
            this.command = "";
            this.setTitle("Game");
            this.setSize(800, 600);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setUpElements();
            setUpPanel();
            this.add(panel);
            this.setVisible(true);
            this.setResizable(false);



        }

        //Returnera det senaste commitade kommandot
        /*public String getCommand(){
            if (this.gotCommand){
                System.out.println(this.command);
                return this.command;
            }
            return null;
        }*/

        //Här kan man updatera respektive fält:

        public void setShowPersons(Person person){
            this.showPersons.setText(person.toString());
        }
        public void setShowInventory(String i){
            this.inventory.setText(i);
        }
        public void setShowRoom(String roomDescription){
            this.showRoom.setText(roomDescription);
        }
        //Add person to room
        public void setPerson(Person p){
            this.showPersons.setText(p.toString());
        }

//Nedantåenda spaghetti är inte vacker...

        /*
        public void gotCommand(){
            this.gotCommand = false;
        }*/

        private void setUpPanel(){
            this.panel.add(showPersons);
            this.panel.add(showRoom);
            this.panel.add(input);
            this.panel.add(inventory);
            this.panel.add(button);

        }
        private void setUpElements(){
            this.panel = new JPanel(new GridLayout(4,3));
            this.showRoom = new JTextArea("Rum ett: ");
            this.showPersons = new JTextArea("Persons");
            this.inventory = new JTextArea("");
            this.input = new JTextField("Give command");
            this.showPersons.setEditable(false);
            this.showRoom.setEditable(false);
            this.inventory.setEditable(false);

            ActionListener inputListener = e -> {
                this.command = input.getText();
                switch (this.command){
                    case "test":
                        Game.UTEST();
                        break;
                    case "test2":
                        Game.YTEST();
                        break;
                    case "test3":
                        Game.XTEST();
                        break;
                    case "test4":
                        Game.X2TEST();
                        break;
                    case "test5":
                        Game.X3TEST();
                        break;
                    case "MOVEROOM":
                        Game.MOVEROOM();
                        break;
                    case "ADDITEMS":
                        Game.randomItemsToRooms();
                        break;
                    case "PRINTITEMS":
                        Game.printItemTest();
                        break;
                    case "REMOVEITEMS":
                        Game.removeItem();
                        break;
                    default:
                        System.out.println("fel kommando!");
                }
                //gör till switch sats för dem olika kommandon och köra metoder..
               /* if(Objects.equals("test", this.command)){
               // this.gotCommand = true;
                    Game.UTEST();
                System.out.println("adderat");
                } else System.out.println("skriv något");*/




            };

            input.addActionListener(inputListener);

            this.button = new JButton("commit");
            this.button.addActionListener(inputListener);

        }


    }









