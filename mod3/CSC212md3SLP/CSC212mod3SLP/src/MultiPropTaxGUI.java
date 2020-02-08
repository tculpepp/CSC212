/**
 /**
 * MultiPropTaxGui is a simple program to take user....
 * inputs and calculate a property tax amount.
 * @author tculpepp
 */
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MultiPropTaxGUI extends Application {
	
	private TextField numHousesInput, taxRateInput, houseValues[];  // input boxes
	private Label instLabel, messageLabel; //labels to interact with
	
	public void start(Stage stage) {
		numHousesInput = new TextField();
		numHousesInput.setMaxWidth(50);
		
		taxRateInput = new TextField(); //input for tax rate
		taxRateInput.setMaxWidth(100);
		
		instLabel = new Label("Enter house value and tax rate to\r"
							+ "calculate a property tax amount."); //instructions to the user
		messageLabel = new Label(); //displays error messages or property tax amount
		Label exitLabel = new Label("(press 'E' to exit)"); //instructions on how to exit
		
		Button enterButton = new Button("Enter"); //setup the enter button
		enterButton.setOnAction(e -> doEnter());
		enterButton.setDefaultButton(true); // set the default action to ENTER
		enterButton.setVisible(false); 	
		
		Button nextButton = new Button("Next");
		
		GridPane root = new GridPane();
		root.setGridLinesVisible(true);
		root.add(instLabel, 0, 0, 2, 1);				//c0 r0 cspan2 rspan1
		root.add(numHousesInput, 0, 1);
		root.add(new Label("Tax Rate (%): "), 0, 2);	//c0 r2
		root.add(taxRateInput, 0, 3);					//c0 r3
		root.add(messageLabel, 0, 6, 2, 1);
		root.add(nextButton, 0, 4);
		root.add(enterButton, 0, 5);
		root.setStyle("-fx-padding:10px; -fx-border-width:2px; -fx-vgap:5px;");
		nextButton.setOnAction(e -> {
			int numHouses = Integer.parseInt(numHousesInput.getText()); //get the number of houses and set it to variable
			houseValues = new TextField[numHouses]; //initialize the array houseValues[]
			for (int i=0; i<numHouses; i++) { //Iterate through and create entry fields for each value
				TextField houseValue = new TextField();
	            houseValues[i] = houseValue;
	            houseValue.setPrefWidth(100); //set the width
	            root.add(houseValues[i], 1, i+1); //add it to the grid in a column
			}
			
			stage.sizeToScene(); //ensure the stage re-sizes
			enterButton.setVisible(true); 
		});
		
		Scene scene = new Scene(root);
    	scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> { //event filter to catch an E to exit
    		if (e.getCode() == KeyCode.E) System.exit(0);
    	});
	    stage.setScene(scene);
	    stage.setTitle("Property Tax Calculator");
	    stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	private void doEnter() {
		float houseTotal = 0; //holds total house value to be taxed
		int numValidated = 0; //holds number of validated entries
		for (TextField hv : houseValues) {
			if (validateInput(hv)!=0) { //if input validation =
				houseTotal = Float.parseFloat(hv.getText()) + houseTotal;
				numValidated++;
				System.out.println(houseTotal);
			}
		}
		if (numValidated == houseValues.length) {
			float taxAmount = houseTotal * (Float.parseFloat(taxRateInput.getText())/100);
			System.out.println(taxAmount);
			messageLabel.setStyle("-fx-text-fill: black");
			messageLabel.setVisible(true);
			messageLabel.setText("Property Tax: $"+(String.format("%,.2f", taxAmount)));
		}
	}
	//a method to check the input and return true if valid or false if invalid
    //private boolean validateInput(TextField userInput) {
    private float validateInput(TextField userInput) {
    	try {
	    	float validatedInput = Float.parseFloat(userInput.getText()); //try to parse the user input as a float
	    	userInput.setStyle("-fx-border-color: black"); //make sure the text field isn't still highlighted
	    	return validatedInput;
	    }	catch (NumberFormatException e){
	    	messageLabel.setText("Invalid Entry"); 	//set the messageLabel text
	    	messageLabel.setStyle("-fx-text-fill: red");									//make the text red
	    	messageLabel.setVisible(true); 													//and make it visible
	    	userInput.setStyle("-fx-border-color: red"); //highlight the error box in red
	    	return 0;
	    }
    }
}