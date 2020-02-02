/**
 * CalcPropertyTaxGui is a simple program to take user
 * inputs and calculate a property tax amount.
 * @author tculpepp
 */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
		taxRateInput = new TextField();
		enterButton = new Button("Enter");
			enterButton.setOnAction(e -> doEnter());
			enterButton.setDefaultButton(true); // Pressing return will be equivalent to clicking this button.
		message = new Label("Enter house value and tax rate, then press ENTER.\r(press E to exit)");
		propertyTaxLabel = new Label();
    	
		GridPane root = new GridPane();
			root.add(message, 0, 0, 4, 1);					//c0, r0, span4, rspan1
			root.add(new Label("House Value ($): "), 1, 1);	//c1 r1
			root.add(houseValueInput, 2, 1); 				//c2 r1
			root.add(new Label("Tax Rate (%): "), 1, 2);	//c1 r2
			root.add(taxRateInput, 2, 2);					//c2 r2
			root.add(enterButton, 1, 3);					//c1 r3
			root.add(propertyTaxLabel, 2, 3, 3, 1);			//c2 r3 cspan2 rpsan1
			
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
        float houseValue;
	    float taxRate;
	    propertyTaxLabel.setText(""); //clear out the label so it doesn't show in error
	    message.setText("Enter house value and tax rate, then press ENTER.\r(press E to exit)");
	    try {
	    	houseValue = Float.parseFloat(houseValueInput.getText());
	    }	catch (NumberFormatException e){
	    	message.setText("\"" + houseValueInput.getText() + "\" is not a legal number.\r(press E to exit)");
	    	houseValueInput.requestFocus();
	    	return;
	    }
	    try {
	    	taxRate = Float.parseFloat(taxRateInput.getText()); 
	    }	catch (NumberFormatException e){
	    	message.setText("\"" + taxRateInput.getText() + "\" is not a legal number.\r(press E to exit)");
	    	taxRateInput.requestFocus();
	    	return;
	    }
	    float taxAmount = houseValue * (taxRate/100);
	    if (taxAmount >= 0) {
    		propertyTaxLabel.setText("Property Tax: $"+(String.format("%,.2f", taxAmount)));
    	}
      }
}