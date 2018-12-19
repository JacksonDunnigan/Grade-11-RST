//import activity4.BallClicker.BallClickHandler;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

//import activity4.Pong.UserKeyInput;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


 
public class enigma extends Application {
	//declares variables
	static final int SCREEN_WIDTH = 1280;
	static final int SCREEN_HEIGHT = 720;
	static final int LETTER_AMOUNT = 26;
	static final int CIRCLE_SIZE = 30;
	
    //creates a canvas
	static Group canvas = new Group();
	
	//initializes the font
    static Font medium_font = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 35);
	static Font small_font = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 24);
	
	//makes lists that hold objects
	static Circle[] button_list = new Circle[LETTER_AMOUNT];
	static Text[] text_list = new Text[LETTER_AMOUNT];
	static Text[] rotor_list = new Text[3];
	static ComboBox<String>[] box_list = new ComboBox[3];

	//defining the alphabet
	static String[] alphabet =  new String[]{"Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M"};
	static String[] alphabet_sorted =  new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	//makes lists of rotors
	static String[] rotor_I = new String[] {"E","K","M","F","L","G","D","Q","V","Z","N","T","O","W","Y","H","X","U","S","P","A","I","B","R","C","J"};
	static String[] rotor_II = new String[] {"A","J","D","K","S","I","R","U","X","B","L","H","W","T","M","C","Q","G","Z","N","P","Y","F","V","O","E"};
	static String[] rotor_III = new String[] {"B","D","F","H","J","L","C","P","R","T","X","V","Z","N","Y","E","I","W","G","A","K","M","U","S","Q","O"};
	static String[] rotor_IV = new String[] {"E","S","O","V","P","Z","J","A","Y","Q","U","I","R","H","X","L","N","F","T","G","K","D","C","M","W","B"};
	static String[] rotor_V = new String[] {"V","Z","B","R","G","I","T","Y","U","P","S","D","N","H","L","X","A","W","M","J","Q","O","F","E","C","K"};
	static String[] rotor_VI = new String[] {"J","P","G","V","O","U","M","F","Y","Q","B","E","N","H","Z","R","D","K","A","S","X","L","I","C","T","W"};
	static String[] rotor_VII = new String[] {"N","Z","J","H","G","R","C","X","M","Y","S","W","B","O","U","F","A","I","V","L","P","E","K","Q","D","T"};
	static String[] rotor_VIII = new String[] {"F","K","Q","H","T","L","X","O","C","B","J","S","P","D","Z","R","A","M","E","W","N","I","U","Y","G","V"};

	//turns the rotor arrays into array lists
	static ArrayList<String> rotor_1 = new ArrayList<String>(Arrays.asList(rotor_I));	
	static ArrayList<String> rotor_2 = new ArrayList<String>(Arrays.asList(rotor_II));	
	static ArrayList<String> rotor_3 = new ArrayList<String>(Arrays.asList(rotor_III)); 
	static ArrayList<ArrayList<String>> current_rotors = new ArrayList<ArrayList<String>>();
	
	//makes counters
	static int rotor_counter_1 = 0;
	static int rotor_counter_2 = 0;
	static int rotor_counter_3 = 0;

	//defining custom colors
	static final Color DARK_GREY = Color.rgb(64,64,64);
	static final Color LIGHT_GREY = Color.rgb(161,161,161);
	static final Color LIGHT_YELLOW = Color.rgb(208,189,59);
	
   	//declares the shadows
	DropShadow black_shadow;
	DropShadow yellow_shadow;
	
	//initializes everything
    @Override
    public void start(Stage primaryStage) throws Exception {
    	
        //black shadow
        black_shadow=new DropShadow();
        black_shadow.setRadius(CIRCLE_SIZE);
        black_shadow.setColor(Color.BLACK);  
        
        //yellow shadow
        yellow_shadow=new DropShadow();
        yellow_shadow.setRadius(CIRCLE_SIZE);
        yellow_shadow.setColor(LIGHT_YELLOW);  
        
        //adds rotors to the current rotor list
        current_rotors.add(rotor_1);
        current_rotors.add(rotor_2);
        current_rotors.add(rotor_3);
        
        
        //generates the interface
        generateInterface();

        //adds everything to a group
        canvas.getChildren().addAll(button_list);
        canvas.getChildren().addAll(text_list);
        canvas.getChildren().addAll(rotor_list);
        
        //creates a timer and starts it
        AnimationTimer timer = new Step();
        timer.start();

        //starts the game
        Scene scene = new Scene(canvas, SCREEN_WIDTH, SCREEN_HEIGHT, DARK_GREY);
        scene.addEventHandler(KeyEvent.ANY, new UserKeyInput());
        primaryStage.setTitle("Enigma machine");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    
    
    //manages input, drawing and animation
    class Step extends AnimationTimer {
        @Override
        public void handle(long now) {
        	
        	
        }
    }

    
    //takes input
    class UserKeyInput implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
        	String key;
        	//checks if the player is touching a key
            KeyCode code = event.getCode();
            if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            	key =""+code;
            	if (validKey(key,alphabet)) {
            		key = rotorUpdate(key);
            		for (int i = 0; i < LETTER_AMOUNT; i++)
            			
            			if (alphabet[i].equals(key)) {
            				button_list[i].setEffect(yellow_shadow);
            			}
            			else {
            				button_list[i].setEffect(black_shadow);	
        				}
        			}
        		}
        	}
        }


    
  //generates the interface
    void generateInterface() {   
    	Text text;
    	Ellipse rotor;
    	Ellipse label;
    	Rectangle rect;
    	
     	//generates ellipses and text to go in them
    	for (int i = 0; i < 3; i++) {
    		//creates the ellipses
    		rotor = new Ellipse();
    		rotor.setCenterX(SCREEN_WIDTH/2-150+(i*140)); 
    		rotor.setCenterY(240); 
    		rotor.setRadiusX(50); 
    		rotor.setRadiusY(130);
    		rotor.setEffect(black_shadow);
    		rotor.setFill(LIGHT_GREY);
    		rotor.setStroke(Color.BLACK);
    		canvas.getChildren().addAll(rotor);
    		
    		//labels the rotors
     		text = new Text(SCREEN_WIDTH/2-190+i*140, 80, "Rotor "+(3-i));
     		text.setFont(small_font);
     		text.setFill(Color.WHITE);
     		canvas.getChildren().addAll(text);
     		
     		//adds ellipses to go behind the letters on the rotors
     		label = new Ellipse();
     		label.setCenterX(SCREEN_WIDTH/2-152+i*140);
     		label.setCenterY(235);	
     		label.setRadiusX(20); 
     		label.setRadiusY(30);
     		label.setFill(DARK_GREY);
     		canvas.getChildren().addAll(label);
     		
     		//adds menus to change the rotors positions
     		ObservableList<String> options = FXCollections.observableArrayList(current_rotors.get(2-i));
     		box_list[i] = new ComboBox<String>(options);
     		box_list[i].setLayoutX(SCREEN_WIDTH/2-180+i*140);
     		box_list[i].setLayoutY(270);

     		
    	    //creates text that represents rotor index's
    		rotor_list[2-i] = new Text(SCREEN_WIDTH/2-160+i*140, 240, current_rotors.get(i).get(0));
    		rotor_list[2-i].setFont(small_font);
    		rotor_list[2-i].setFill(Color.WHITE);
    	}
    	
    	canvas.getChildren().addAll(box_list);
		
		
    	//generates circles and keys
        for (int i = 0; i < button_list.length; i++) {
            	int x;
            	double y;
            	
            	if (i < 9) {
            		x = SCREEN_WIDTH/2-340+(i*80)+CIRCLE_SIZE/2;
            		y = SCREEN_HEIGHT*0.65;
            	}
            	else if (i >=9 && i < 17) {
            		x = SCREEN_WIDTH/2-1020+(i*80)+CIRCLE_SIZE/2;
            		y = SCREEN_HEIGHT*0.65+80;
            	}
            	else {
            		x = SCREEN_WIDTH/2-1700+(i*80)+CIRCLE_SIZE/2;
            		y = SCREEN_HEIGHT*0.65+160;
            	}
            	
            	
            	//creates the circle and gives the circles a message
        		button_list[i] = new Circle(x, y, CIRCLE_SIZE, Color.BLACK);
        		button_list[i].setEffect(black_shadow);
        		button_list[i].setStroke(LIGHT_GREY);
        		button_list[i].setStrokeWidth(3);
        		
            	text_list[i] = new Text(x-CIRCLE_SIZE*0.45, y+CIRCLE_SIZE*0.4, alphabet[i]);
            	text_list[i].setFont(medium_font);
            	text_list[i].setFill(LIGHT_GREY);
            	
        }
  
    }
    
    //passes the letter through the rotors and updates the rotors positions
    public static String rotorUpdate(String input){
    	
    	//converts the key into a number from 1 to 25
    	char key =  input.charAt(0);
    	String letter = ""+key;
    	
    	System.out.print(input);

    	//changes the letter (works)
    	letter = rotor_1.get(letter.charAt(0)-'A');   
    	System.out.print(letter);
    	//a to e
    	letter = rotor_2.get(letter.charAt(0)-'A');
    	System.out.print(letter);
    	//e to s
    	letter = rotor_3.get(letter.charAt(0)-'A');
    	System.out.print(letter);
    	//s to g
    	
    	
    	//shifts the rotors
        if (rotorChange(rotor_1,true)) {
        	if (rotorChange(rotor_2,true)) {
    			rotorChange(rotor_3,true);
        	}
        }
        
        //dont works
       
        //reverseSignal();
        
    	//reverses the signal
    	letter = alphabet_sorted[rotor_3.indexOf(letter)];   
    	System.out.print(letter);
    	
    	letter = alphabet_sorted[rotor_2.indexOf(letter)];   
    	System.out.print(letter);
    	
    	letter = alphabet_sorted[rotor_1.indexOf(letter)];   
    	System.out.print(letter);
    	

//    	letter = rotor_2.get(letter.charAt(0)-'A');
//    	System.out.print(letter);
//    	
//    	letter = rotor_1.get(letter.charAt(0)-'A');
//    	System.out.print(letter);
    	
    	
    	//returns the changed letters
    	return letter;
    }
    
    
    
    //shifts the letters
    public static boolean rotorChange(ArrayList<String> index, boolean real) {

    	String val;
    	boolean output = false;
    	val = index.get(25);

    	//rotates the first rotor
    	if (index == rotor_1) {
    		if (real) {
    			rotor_1.remove(25);
    			rotor_1.add(0,val);
    		}
    		//changes the message
    		rotor_list[0].setText(current_rotors.get(0).get(0)); 
    		
    		//changes the rotor counter index
    		if (rotor_counter_1+1>25) {
    			rotor_counter_1 = 0;
    			output = true;
    		} else {
    			rotor_counter_1 += 1;
    			output = false;
    		}
    		
    		
    	//rotates the second rotor
    	} else if  (index == rotor_2){
    		if (real) {
    			rotor_2.remove(25);
    			rotor_2.add(0,val);
    		}
    		//changes the message
    		rotor_list[1].setText(current_rotors.get(1).get(0)); 
    		
    		//changes the rotor counter index
    		if (rotor_counter_2+1>25) {
    			rotor_counter_2 = 0;
    			output = true;
    		} else {
    			rotor_counter_2 += 1;
    			output = false;
    		}
    	
    		
    	//rotates the third rotor
    	} else if  (index == rotor_3){
    		if (real) {
    			rotor_3.remove(25);
    			rotor_3.add(0,val);
    		}
    		//changes the message
    		rotor_list[2].setText(current_rotors.get(2).get(0)); 
    		
    		//changes the rotor counter index
    		if (rotor_counter_3+1>25) {
    			rotor_counter_3 = 0;
    			output = true;
    		} else {
    			rotor_counter_3 += 1;
    			output = false;
    		} 
    	}
    	return output;
    }
    
    
    //reverses the signal
//    public static string reverseSignal(int index) {
//    	
//    	
//    }
    
    
    
	// finds a random number between 2 inputs
	public static int randomRange(int a, int b) {
	    int highNum = Math.max(a, b);
	    int lowNum = Math.min(a, b);
	    int range = highNum - lowNum + 1;
	    return (int) (Math.random() * range) + lowNum;
	}
	
	
	
	
    //makes sure the player presses a letter of the alphabet
    public static boolean validKey(String key, String[] alphabet) {
    	//converts the alphabet to a list

		for (int i = 0; i<alphabet.length; i++) {
	    	if (key.equals(alphabet[i])) {
				return true;
	    	}
		}
    	return false;
    }
    
    
	//starts the program
    public static void main(String[] args) {
        launch(args);
    }
}

