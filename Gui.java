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
        //private JTextField input2;
        private JTextArea inventory;
        private JTextArea inventory2;
        private String command;
        private boolean gotCommand;
        private JButton button;
        public Gui(){
            this.gotCommand = false;
            this.command = "";
            this.setTitle("Game");
            this.setSize(1000, 600);
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

        public void setShowPersons(String person){
            this.showPersons.setText(person);
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
            //this.panel.add(input2);
            this.panel.add(button);
            this.panel.add(inventory);
            this.panel.add(inventory2);

        }
        private void setUpElements(){
            this.panel = new JPanel(new GridLayout(4,3));
            this.showRoom = new JTextArea("Rum ett: ");
            this.showPersons = new JTextArea();
            this.inventory = new JTextArea("");
            this.inventory2 = new JTextArea("");
            this.input = new JTextField("Give command");
            //this.input2 = new JTextField("Type item to drop or pickup");
            this.showPersons.setEditable(false);
            this.showRoom.setEditable(false);
            this.inventory.setEditable(false);
            this.inventory2.setEditable(false);

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
                    case "PLAYERPICKUP":
                        Game.randomItemsToRooms2();
                        break;
                    case "PLAYERDROP":
                        Game.randomItemsToRooms3();
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
            };

            input.addActionListener(inputListener);

            this.button = new JButton("commit");
            this.button.addActionListener(inputListener);

        }


    }









