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
	private Label messageLabel; //labels to interact with
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		houseValueInput = new TextField(); //input for house value
		houseValueInput.setMaxWidth(100);
		
		taxRateInput = new TextField(); //input for tax rate
		taxRateInput.setMaxWidth(100);
		
		Label instLabel = new Label("Enter house value and tax rate to\r"
				+ "calculate a property tax amount."); //instructions to the user
		
		messageLabel = new Label(); //error label in case bad data is entered
		
		Label exitLabel = new Label("Press 'E' to exit"); //instructions on how to exit
		
		Button enterButton = new Button("Enter"); //setup the enter button
		enterButton.setOnAction(e -> doEnter());
		enterButton.setDefaultButton(true); // set the default action to ENTER
    	
		GridPane root = new GridPane();
		root.add(instLabel, 0, 0, 2, 1);				//c0 r0 cspan2 rspan1
		root.add(new Label("House Value ($): "), 0, 1);	//c0 r1
		root.add(houseValueInput, 1, 1); 				//c1 r1
		root.add(new Label("Tax Rate (%): "), 0, 2);	//c0 r2
		root.add(taxRateInput, 1, 2);					//c1 r2
		root.add(messageLabel, 0, 3, 2, 1);				//c0 r3 cspan2 rpsan1
		root.add(enterButton, 0, 4);					//c0 r4
		root.add(exitLabel, 1, 4, 2, 1);				//c1 r4 cspan2 rspan1
		root.setStyle("-fx-padding:10px; -fx-border-width:2px; -fx-vgap:5px;");
		GridPane.setHalignment(enterButton, HPos.CENTER);
		GridPane.setHalignment(messageLabel, HPos.CENTER);
		
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
	private void doEnter() {
	    float houseValue = validateInput(houseValueInput);
	    float taxRate = validateInput(taxRateInput);
	    while (houseValue == 0 || taxRate == 0) { //see if both variables are set before calculating
	    	return;
	    }
	    float taxAmount = houseValue * (taxRate/100); //calc the tax amount
		messageLabel.setStyle("-fx-text-fill: black");
		messageLabel.setText("Property Tax: $"+(String.format("%,.2f", taxAmount)));
	}
	//a method to check the input and return the parsed value or 0 if failed parse
    private float validateInput(TextField userInput) {
    	try {
	    	float validResult = Float.parseFloat(userInput.getText()); //try to parse the user input as a float
	    	userInput.setStyle("-fx-border-color: black"); //make sure the text field isn't still highlighted
	    	return validResult;
	    }	catch (NumberFormatException e){
	    	messageLabel.setText("Invalid entry, please try again."); //set the errorLabel text
	    	messageLabel.setStyle("-fx-text-fill: red");						//and make it visible
	    	userInput.setStyle("-fx-border-color: red"); //highlight the error box in red
	    	return 0;
	    }
    }
}