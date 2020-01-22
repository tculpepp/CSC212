/**
 * In this assignment, rewrite this program (mod1 SLP) using the GUI feature to accept user input. Users will press "E" to stop the program.  
 * @author tculpepp
 *
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class CalcPropertyTaxGUI extends Application {
	
	public static void main(String[] args) {
		launch(args); // Run this Application.
		}

	
	private Label propertyTaxLabel;	// A label for displaying the propertyTax
	private Button enterButton;   	// A button the user can press to enter a number.
    								//This is an alternative to pressing return.
	private Button clearButton;   	// A button that clears all the data that the
    								//user has entered.
	
	private TextField houseValueInput;  // input box for houseValue
	private TextField taxRateInput; //input box for taxRate
	
	private Label message;  // A message at the top of the program.  It will
							//   show an error message if the user's input is
							//   not a legal number.  Otherwise, it just tells
							//   the user to enter a number and press return.

	
	public void start(Stage stage) {
		houseValueInput = new TextField();
			houseValueInput.setPrefColumnCount(10);
			houseValueInput.setAlignment(Pos.CENTER);
			houseValueInput.setPromptText("Assessed Value"); //to set the hint text
			//houseValueInput.getParent().requestFocus(); //to not setting the focus on that node so that the hint will display immediately
		taxRateInput = new TextField();
			taxRateInput.setPrefColumnCount(10);
			taxRateInput.setAlignment(Pos.CENTER);
			taxRateInput.setPromptText("Tax Rate"); //to set the hint text
			//taxRateInput.getParent().requestFocus(); //to not setting the focus on that node so that the hint will display immediately
		
		enterButton = new Button("Enter");
			enterButton.setOnAction( e -> doEnter() );
			enterButton.setMaxSize(1000,1000);
			enterButton.setFocusTraversable(false); //eliminate the button from 'tab' access
			enterButton.setDefaultButton(true); // Pressing return will be equivalent to clicking this button.
		clearButton = new Button("Clear");
	    	clearButton.setOnAction( e -> doClear() );
	    	clearButton.setMaxSize(1000,1000);
	    	enterButton.setFocusTraversable(false);//eliminate the button from 'tab' access
	    
	    propertyTaxLabel = makeLabel("Property tax due this year:\r");
	    
	    message = new Label("Enter house value and tax rate, then press return");
	    	message.setFont(Font.font(16));
	    	message.setTextFill(Color.WHITE);
	    	message.setWrapText(true);
	    	message.setAlignment(Pos.CENTER);
	       
	    TilePane topPanel = new TilePane(5,5,houseValueInput, enterButton, taxRateInput, clearButton);
	    	topPanel.setPrefColumns(2);
	    	topPanel.setPrefRows(2);
	    	topPanel.setAlignment(Pos.CENTER);
	    TilePane bottomPanel = new TilePane(5,5,propertyTaxLabel);
	    	bottomPanel.setPrefColumns(1);
	    TilePane root = new TilePane(3,3,message, topPanel, propertyTaxLabel);
	    	root.setPrefColumns(1);
	    	root.setStyle("-fx-border-color:black; " + 
                    "-fx-border-width:3; -fx-background-color:black");
	    
	    Scene scene = new Scene(root);
	    stage.setScene(scene);
	    stage.setTitle("Property Tax Calculator");
	    stage.setResizable(false);
	    stage.show();
	}
	
	/**
     * A utility routine for creating the labels that are used
     * for display.  This routine is used in the start() method.
     * @param text The text to show on the label.
     */
	private Label makeLabel(String text) {
	       Label label = new Label(text);
	       label.setMaxSize(1000,1000);
	       label.setTextFill(Color.WHITE);
	       label.setAlignment(Pos.CENTER);
	       label.setTextAlignment(TextAlignment.CENTER);
	       label.setStyle("-fx-background-color: black; " +
	                              "-fx-font-family: monospace; -fx-font-weight: bold; -fx-font-size: 16;");
	       return label;
	    }
	
	/**
     * Clear all data, restoring the program to its original state.
     * This method is called when the user clicks the Clear button.
     */
    private void doClear() {
    	taxRateInput.setText("");
    	houseValueInput.setText("");
        showData(0);
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
        showData(calcPropertyTax(houseValue, taxRate));
      }
    
    
    private boolean validateInput(String userInput, byte type) {
    	try {
		        Float.parseFloat(userInput);
		        return true;
		    }  
	    catch(NumberFormatException e){
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
        

    private void showData(float propertyTax) {
    	if (propertyTax != 0) {
    		propertyTaxLabel.setText("Property tax due this year:\r $"+(String.format("%,.2f", propertyTax)));
    	}
    	else {
    		propertyTaxLabel.setText("Property tax due this year:\r");
    	}
        message.setText("Enter house value and tax rate, then press return");
        houseValueInput.requestFocus();
    }

    private float calcPropertyTax(float houseValue, float taxRate) {
    	return houseValue * (taxRate/100);
    }
  
}  // end CalcPropertyTaxGUI