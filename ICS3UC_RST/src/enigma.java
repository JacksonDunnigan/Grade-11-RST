//import activity4.BallClicker.BallClickHandler;
import javafx.application.Application;
//import activity4.Pong.UserKeyInput;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
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
	
	//initializes the font
	Font font = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 35);
	
	//holds all the buttons
	Circle[] button_list = new Circle[LETTER_AMOUNT];
	
	//holds the text drawn on the circles
	Text[] text_list = new Text[LETTER_AMOUNT];
	
	//holds the rotors
	Ellipse[] rotor_list = new Ellipse[3];
	
	//defining the roters and alphabet
	String[] alphabet =  new String[]{"Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M"};
	String[] rotor_I = new String[] {"E","K","M","F","L","G","D","Q","V","Z","N","T","O","W","Y","H","X","U","S","P","A","I","B","R","C","J"};
	String[] rotor_II = new String[] {"A","J","D","K","S","I","R","U","X","B","L","H","W","T","M","C","Q","G","Z","N","P","Y","F","V","O","E"};
	String[] rotor_III = new String[] {"B","D","F","H","J","L","C","P","R","T","X","V","Z","N","Y","E","I","W","G","A","K","M","U","S","Q","O"};
	String[] rotor_IV = new String[] {"E","S","O","V","P","Z","J","A","Y","Q","U","I","R","H","X","L","N","F","T","G","K","D","C","M","W","B"};
	String[] rotor_V = new String[] {"V","Z","B","R","G","I","T","Y","U","P","S","D","N","H","L","X","A","W","M","J","Q","O","F","E","C","K"};
	String[] rotor_VI = new String[] {"J","P","G","V","O","U","M","F","Y","Q","B","E","N","H","Z","R","D","K","A","S","X","L","I","C","T","W"};
	String[] rotor_VII = new String[] {"N","Z","J","H","G","R","C","X","M","Y","S","W","B","O","U","F","A","I","V","L","P","E","K","Q","D","T"};
	String[] rotor_VIII = new String[] {"F","K","Q","H","T","L","X","O","C","B","J","S","P","D","Z","R","A","M","E","W","N","I","U","Y","G","V"};

			
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
    	
        //creates a canvas
        Group canvas = new Group();
        
        //shadows
        black_shadow=new DropShadow();
        black_shadow.setRadius(CIRCLE_SIZE);
        black_shadow.setColor(Color.BLACK);  
        
        yellow_shadow=new DropShadow();
        yellow_shadow.setRadius(CIRCLE_SIZE);
        yellow_shadow.setColor(LIGHT_YELLOW);  
        
        //generates the interface
        generateInterface();
        
        
        //adds everything to a group
        canvas.getChildren().addAll(button_list);
        canvas.getChildren().addAll(text_list);
        canvas.getChildren().addAll(rotor_list);

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

        	//checks if the player is touching a key
            KeyCode code = event.getCode();
            if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            	String key = ""+code;

            	if (validKey(key,alphabet)) {
            		for (int i = 0; i < LETTER_AMOUNT; i++) {
    			
            			if (alphabet[i].equals(key)) {
            				rotor_update();
            				text_list[i].setFill(Color.BLACK);
            				button_list[i].setFill(LIGHT_GREY);
            				button_list[i].setStroke(Color.BLACK);
            				button_list[i].setEffect(yellow_shadow);
            			}
            			else {
            				text_list[i].setFill(LIGHT_GREY);
            				button_list[i].setFill(Color.BLACK); 
            				button_list[i].setStroke(Color.WHITE);
            				button_list[i].setEffect(black_shadow);
            			}
            		}
            	}
            }
        }
    }
    
    
    
    //passes the letter through the rotors and updates the rotors positions
    void rotor_update(){
    	
    }
    
    
    
  //generates the interface
    void generateInterface() {   
    	
       	
     	//generates ellipses
    	for (int i = 0; i < rotor_list.length; i++) {
    		 rotor_list[i] = new Ellipse();
    		 rotor_list[i].setCenterX(SCREEN_WIDTH/2-150+(i*140)); 
    		 rotor_list[i].setCenterY(200); 
    		 rotor_list[i].setRadiusX(50); 
    		 rotor_list[i].setRadiusY(130);
    		 rotor_list[i].setEffect(black_shadow);
    		 rotor_list[i].setFill(LIGHT_GREY);
    		 rotor_list[i].setStroke(Color.BLACK);
    		 
    	 }
    	
    	
    	//generates circles 
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
        		button_list[i].setStroke(Color.WHITE);
        		button_list[i].setStrokeWidth(3);
        		
            	text_list[i] = new Text(x-CIRCLE_SIZE*0.45, y+CIRCLE_SIZE*0.4, alphabet[i]);
            	text_list[i].setFont(font);
            	text_list[i].setFill(LIGHT_GREY);
            	
        }
  
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

