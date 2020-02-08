/**
 * CalcPropertyTaxGui is a simple program to take user
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

public class CalcPropertyTaxGUI extends Application {
	
	private TextField houseValueInput, taxRateInput;  // input boxes
	private Label message, propertyTaxLabel, errorLabel; //labels to interact with
	
	public void start(Stage stage) {
		houseValueInput = new TextField(); //input for house value
		houseValueInput.setMaxWidth(100);
		
		taxRateInput = new TextField(); //input for tax rate
		taxRateInput.setMaxWidth(100);
		
		message = new Label("Enter house value and tax rate to\rcalculate a property tax amount."); //instructions to the user
		
		propertyTaxLabel = new Label(); //label for the property tax text field
		
		errorLabel = new Label(); //error label in case bad data is entered
		errorLabel.setStyle("-fx-text-fill: red");
		errorLabel.setVisible(false); //hide it until needed
		
		Label exitLabel = new Label("Press 'E' to exit"); //instructions on how to exit
		
		Button enterButton = new Button("Enter"); //setup the enter button
		enterButton.setOnAction(e -> doEnter());
		enterButton.setDefaultButton(true); // set the default action to ENTER
    	
		GridPane root = new GridPane();
		root.add(message, 0, 0, 2, 1);					//c0 r0 cspan2 rspan1
		root.add(new Label("House Value ($): "), 0, 1);	//c0 r1
		root.add(houseValueInput, 1, 1); 				//c1 r1
		root.add(new Label("Tax Rate (%): "), 0, 2);	//c0 r2
		root.add(taxRateInput, 1, 2);					//c1 r2
		root.add(propertyTaxLabel, 0, 3, 2, 1);			//c0 r3 cspan2 rpsan1
		root.add(enterButton, 0, 4);					//c1 r4
		root.add(exitLabel, 1, 4, 2, 1);				//c1 r4 cspan2 rspan1
		root.add(errorLabel, 0, 5, 2, 1);				//c0 r5 cspan2 rspan1
		root.setStyle("-fx-padding:10px; -fx-border-width:2px; -fx-vgap:5px;");
		GridPane.setHalignment(enterButton, HPos.CENTER);
		GridPane.setHalignment(propertyTaxLabel, HPos.CENTER);
		GridPane.setHalignment(errorLabel, HPos.CENTER);
		
		Scene scene = new Scene(root);
    	scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> { //event filter to catch an E to exit
    		if (e.getCode() == KeyCode.E) {
    			System.exit(0);
    		}
    	});

	    stage.setScene(scene);
	    stage.setTitle("Property Tax Calculator");
	    stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	private void doEnter() {
	    propertyTaxLabel.setText(""); //clear out the label so it doesn't show in error if we calc again
	    errorLabel.setVisible(false);
	    float houseValue = validateInput(houseValueInput);
	    float taxRate = validateInput(taxRateInput);
	    if (houseValue != 0 && taxRate != 0) { //see if both variables are set before calculating
	    	float taxAmount = houseValue * (taxRate/100); //calc the tax amount
	    	propertyTaxLabel.setText("Property Tax: $"+(String.format("%,.2f", taxAmount)));
	    }	
	}
	//a method to check the input and return true if valid or false if invalid
    private float validateInput(TextField userInput) {
    	try {
	    	float validResult = Float.parseFloat(userInput.getText()); //try to parse the user input as a float
	    	userInput.setStyle("-fx-border-color: black"); //make sure the text field isn't still highlighted
	    	return validResult;
	    }	catch (NumberFormatException e){
	    	errorLabel.setText("\"" + userInput.getText() + "\" is not a valid entry."); //set the errorLabel text
	    	errorLabel.setVisible(true); 												//and make it visible
	    	userInput.setStyle("-fx-border-color: red"); //highlight the error box in red
	    	return 0;
	    }
    }
}