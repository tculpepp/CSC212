/**
 * In this assignment, rewrite this program (mod1 SLP) using the GUI feature to accept user input. Users will press "E" to stop the program.  
 * @author tculpepp
 *
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CalcPropertyTaxGUI extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	private Button enterButton;
	private TextField houseValueInput;  // input box for houseValue
	private TextField taxRateInput; //input box for taxRate
	private Label message; //top label to give the user instructions
	private Label propertyTaxLabel;	// A label for displaying the propertyTax

	public void start(Stage stage) {
		houseValueInput = new TextField();
			houseValueInput.setMaxWidth(100);
		taxRateInput = new TextField();
			taxRateInput.setMaxWidth(100);
		enterButton = new Button("Enter");
			enterButton.setOnAction(e -> doEnter());
			enterButton.setDefaultButton(true); // Pressing return will be equivalent to clicking this button.
		message = new Label("Enter house value and tax rate, then press ENTER.\r(press E to exit)");
	    	message.setWrapText(true);
	    	message.setTextAlignment(TextAlignment.CENTER);
	    	message.setAlignment(Pos.CENTER);
	    	message.setMaxWidth(200);
	    propertyTaxLabel = new Label();
	    	propertyTaxLabel.setAlignment(Pos.CENTER);
	    	propertyTaxLabel.setMaxWidth(Double.POSITIVE_INFINITY);

	    HBox houseValuePane = new HBox( new Label("House Value ($): "),houseValueInput);
	    HBox taxRatePane = new HBox( new Label("Tax Rate (%): "),taxRateInput);
	    	taxRatePane.setAlignment(Pos.CENTER_RIGHT);
	    HBox buttonPane = new HBox (enterButton);
	    	buttonPane.setAlignment(Pos.CENTER);
        VBox root = new VBox( 10, message, houseValuePane, taxRatePane, buttonPane, propertyTaxLabel);
        	root.setPadding(new Insets(10,5,10,5));

	    Scene scene = new Scene(root);
	    	scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> { //event filter to catch an E to exit
	    		if (e.getCode() == KeyCode.E) {
	    			System.exit(0);
	    		}
	    	});
	    stage.setScene(scene);
	    stage.setTitle("Property Tax Calculator");
	    stage.setResizable(false);
	    stage.show();
	}

    private void doEnter() {
        float houseValue;
	    float taxRate;
	    if (validateInput(houseValueInput.getText(),(byte)0)) {
	    	houseValue = Float.parseFloat(houseValueInput.getText());
	    }
	    else {
		   return;
	    }
	    if (validateInput(taxRateInput.getText(),(byte)1)) {
	    	taxRate = Float.parseFloat(taxRateInput.getText());
	    }
	    else {
		   return;
	    }
	    float taxAmount = houseValue * (taxRate/100);
	    if (taxAmount >= 0) {
    		propertyTaxLabel.setText("Property Tax: $"+(String.format("%,.2f", taxAmount)));
    	}
      }
    //method checks user input, returns true if valid and sends focus to the textbox and returns false if invalid.
    private boolean validateInput(String userInput, byte type) {
    	try {
		        Float.parseFloat(userInput);
		        return true;
		    }  
	    catch(NumberFormatException e){  //if it wasn't a number, notify user and return focus
	    	message.setText("\"" + userInput + "\" is not a legal number.");
		    	if (type == 0) {
		    		houseValueInput.selectAll();
			    	houseValueInput.requestFocus();
		    	}
		    	else if (type==1) {
		    		taxRateInput.selectAll();
		    		taxRateInput.requestFocus();
		    	}
		    	return false;
		    }
    }
}