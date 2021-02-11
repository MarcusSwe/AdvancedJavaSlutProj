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
        private JTextArea status;
        private JTextField input;
        //private JTextField input2;
        private JTextArea inventory;
        private JTextArea inventory2;
        private String command;
        private boolean gotCommand;
        private JButton button;
        private JButton start;
        private JButton load;
        private JButton save;
        public Gui(){
            this.gotCommand = false;
            this.command = "";
            this.setTitle("Game");
            this.setSize(1200, 600);
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
        public void setShowPersons2(String person){
            this.status.setText(person);
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
            //this.panel.add(inventory2);
            this.panel.add(status);
            this.panel.add(start);
            this.panel.add(load);
            this.panel.add(save);
            this.panel.add(inventory);
        }
        private void setUpElements(){
            this.panel = new JPanel(new GridLayout(4,3));
            this.showRoom = new JTextArea("Rum ett: ");
            this.showPersons = new JTextArea();
            this.inventory = new JTextArea("");
            this.inventory2 = new JTextArea("");
            this.status = new JTextArea("Status: Find key and find door to win! Pickup item with 'P itemname' \n or drop with 'D itemname' \n or 'NPC P itemname' do pickup NPC item");
            this.input = new JTextField("Give command");
            //this.input2 = new JTextField("Type item to drop or pickup");
            this.showPersons.setEditable(false);
            this.showRoom.setEditable(false);
            this.inventory.setEditable(false);
            this.inventory2.setEditable(false);
            this.status.setEditable(false);

            ActionListener inputListener = e -> {
                this.setShowPersons2("Status: Find key and find door to win! Pickup item with 'P itemname' \n or drop with 'D itemname' \n or 'NPC P itemname' do pickup NPC item");
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
                    case "P Door":
                        Game.pDoor();
                        break;
                    case "P KeyToUnlock":
                        Game.pKeyToUnlock();
                        break;
                    case "P Paraply":
                        Game.pParaply();
                        break;
                    case "P Donut":
                        Game.pDonut();
                        break;
                    case "P Motorsåg":
                        Game.pMotorsag();
                        break;
                    case "P Penna":
                        Game.pPenna();
                        break;
                    case "P Andriod phone":
                        Game.pAndriod();
                        break;
                    case "P Tröjja":
                        Game.pTrojja();
                        break;
                    case "P Colaburk":
                        Game.pColaburk();
                        break;
                    case "D Door":
                        Game.dDoor();
                        break;
                    case "D KeyToUnlock":
                        Game.dKeyToUnlock();
                        break;
                    case "D Paraply":
                        Game.dParaply();
                        break;
                    case "D Donut":
                        Game.dDonut();
                        break;
                    case "D Motorsåg":
                        Game.dMotorsag();
                        break;
                    case "D Penna":
                        Game.dPenna();
                        break;
                    case "D Andriod phone":
                        Game.dAndriod();
                        break;
                    case "D Tröjja":
                        Game.dTrojja();
                        break;
                    case "D Colaburk":
                        Game.dColaburk();
                        break;
                    case "NPC P Door":
                        Game.npcPDoor();
                        break;
                    case "NPC P KeyToUnlock":
                        Game.npcPKeyToUnlock();
                        break;
                    case "NPC P Paraply":
                        Game.npcPParaply();
                        break;
                    case "NPC P Donut":
                        Game.npcPDonut();
                        break;
                    case "NPC P Motorsåg":
                        Game.npcPMotorsag();
                        break;
                    case "NPC P Penna":
                        Game.npcPPenna();
                        break;
                    case "NPC P Andriod phone":
                        Game.npcPAndriod();
                        break;
                    case "NPC P Tröjja":
                        Game.npcPTrojja();
                        break;
                    case "NPC P Colaburk":
                        Game.npcPColaburk();
                        break;
                    default:
                        this.setShowPersons2("Fel Kommando!");
                }
            };

            input.addActionListener(inputListener);

            this.button = new JButton("commit");
            this.button.addActionListener(inputListener);
            this.start = new JButton("Start");
            //this.button.addActionListener(inputListener);
            this.load = new JButton("Load");
            this.save = new JButton("Save");
            //this.button.addActionListener(inputListener);

        }


    }









