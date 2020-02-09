/**
 /**
 * MultiPropTaxGui is a simple program to take user inputs
 * & and calculate a property tax amount for multiple houses.
 * @author tculpepp
 */
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MultiPropTaxGUI extends Application {
	
	private TextField numHousesInput, taxRateInput, houseValues[];  // input boxes
	private Label messageLabel; //labels to interact with
	private FlowPane centerFlow;
	private Button nextButton, enterButton; 
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		numHousesInput = new TextField();
		numHousesInput.setMaxWidth(50);
		taxRateInput = new TextField(); //input for tax rate
		taxRateInput.setMaxWidth(100);
		
		messageLabel = new Label(); //error label in case bad data is entered
		Label exitLabel = new Label("(press 'E' to exit)"); //instructions on how to exit
		
		enterButton = new Button("Calculate"); //setup the enter button
		enterButton.setOnAction(e -> doEnter());
		enterButton.setVisible(false); 
		
		nextButton = new Button("Next");
		nextButton.setDefaultButton(true);
		nextButton.setOnAction(e -> { 
			buildForm();
			stage.sizeToScene(); //ensure the stage re-sizes
		});
		
		centerFlow = new FlowPane();
		centerFlow.setPrefWrapLength(150); // preferred height = 150
		centerFlow.setOrientation(Orientation.VERTICAL);
		
		VBox bottomVBox = new VBox(messageLabel, exitLabel);
		bottomVBox.setStyle("-fx-alignment: center;");
		VBox leftVBox = new VBox(new Label("Number of Houses:"),numHousesInput,new Label("Tax Rate (%):"),taxRateInput,nextButton,enterButton);
		leftVBox.setStyle("-fx-padding:10px;");
		VBox centerVBox = new VBox(new Label("House Values:"), centerFlow);
		centerVBox.setStyle("-fx-padding:10px;");
		
		BorderPane root = new BorderPane();
		root.setMinWidth(335);
		root.setLeft(leftVBox);
		root.setCenter(centerVBox);
		root.setBottom(bottomVBox);
		
		Scene scene = new Scene(root);
    	scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> { //event filter to catch an E to exit
    		if (e.getCode() == KeyCode.E) System.exit(0);
    	});
	    stage.setScene(scene);
	    stage.setTitle("Property Tax Calculator");
	    stage.show();
	}
	private void buildForm() { //builds an array of textfields and adds them to the stage
		int numHouses = (int)validateInput(numHousesInput);
		if (numHouses != 0) {
			numHousesInput.setStyle("-fx-border-color: black"); //back to black in case it's highlighted
			numHousesInput.setDisable(true); //disable the texfield so there is no confusion later
			houseValues = new TextField[numHouses]; //initialize the array houseValues[]
			for (int i=0; i<numHouses; i++) { //Iterate through and create entry fields for each value
				TextField houseValue = new TextField();
	            houseValues[i] = houseValue;
	            houseValue.setPrefWidth(100); //set the width
	            centerFlow.getChildren().add(houseValues[i]);
			}
			nextButton.setVisible(false); //turn off the next button
			enterButton.setVisible(true); //turn on the enter button
			enterButton.setDefaultButton(true); // the enter button is now default
		}
	}
	private void doEnter() {
		float houseTotal = 0; //holds total house value to be taxed
		int numValidated = 0; //holds number of validated entries
		float taxRate = validateInput(taxRateInput);
		for (TextField hv : houseValues) {
			if (validateInput(hv)!=0) { //if input validation =
				houseTotal = Float.parseFloat(hv.getText()) + houseTotal;
				numValidated++;
			}
		}
		if (numValidated == houseValues.length && taxRate !=0) {
			float taxAmount = houseTotal * taxRate/100;
			messageLabel.setStyle("-fx-text-fill: black; -fx-font-weight:bolder; -fx-font-size:15;");
			messageLabel.setText("Property Tax: $"+(String.format("%,.2f", taxAmount)));
		}
	}
	//a method to check the input and return the parsed value or 0 if failed parse
	private float validateInput(TextField userInput) {
    	try {
	    	float validResult = Float.parseFloat(userInput.getText()); //try to parse the user input as a float
	    	userInput.setStyle("-fx-border-color: black"); //make sure the text field isn't still highlighted
	    	return validResult;
	    }	catch (NumberFormatException e){
	    	messageLabel.setText("Invalid Entry, Please Try Again"); //set the messageLabel text
	    	messageLabel.setStyle("-fx-text-fill: red");			//color it red
	    	userInput.setStyle("-fx-border-color: red"); 			//highlight the error box in red
	    	return 0;
	    }
    }
}