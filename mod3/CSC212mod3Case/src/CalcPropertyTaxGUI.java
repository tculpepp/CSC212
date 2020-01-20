/**
 * In this assignment, rewrite this program (mod1 SLP) using the GUI feature to accept user input. Users will press "E" to stop the program.  
 * @author tculpepp
 *
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
		//houseValueInput.setPrefColumnCount(8);  // Makes the text box smaller than the default.
		
		taxRateInput = new TextField();
		
		
		enterButton = new Button("Enter");
		enterButton.setOnAction( e -> doEnter() );
		//enterButton.setMaxSize(1000,1000);
		enterButton.setDefaultButton(true); // Pressing return will be equivalent to clicking this button.
		clearButton = new Button("Clear");
	    clearButton.setOnAction( e -> doClear() );
	    //clearButton.setMaxSize(1000,1000);
	    
	    propertyTaxLabel =   makeLabel(" Property Tax due($): ");
	    
	    message = new Label("Enter house value and tax rate, then press return");
	    message.setFont(Font.font(16));
	    message.setTextFill(Color.WHITE);
	    
	    TilePane topInputPanel = new TilePane(3,3,houseValueInput,taxRateInput);
	    topInputPanel.setPrefColumns(2);
	    TilePane bottomInputPanel = new TilePane(3,3,enterButton,clearButton);
	    bottomInputPanel.setPrefColumns(2);
	       TilePane root = new TilePane(3, 3, message, topInputPanel, bottomInputPanel);
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
	       label.setStyle("-fx-background-color:white; " +
	                              "-fx-font-family: monospace; -fx-font-weight: bold");
	       return label;
	    }
	
	/**
     * Clear all data, restoring the program to its original state.
     * This method is called when the user clicks the Clear button.
     */
    private void doClear() {
        //stats = new StatCalc();
        houseValueInput.setText("");
        taxRateInput.setText("");
        showData();
    }
    
    /**
     * Respond when the clicks the Enter button by getting a number from
     * the text input box, adding it to the StatCalc and updating the
     * four display labels.  It is possible that an error will occur,
     * in which case an error message is put into the label at the top
     * of the window.  (Because the Enter button has been set to be the
     * default button for the program, this mehod is also invoked when
     * the user presses return.)
     */
    private void doEnter() {
        float houseValue;  //input house value
        float taxRate; //input tax rate
        try {
            houseValue = Float.parseFloat(houseValueInput.getText());
        }
        catch (NumberFormatException e) {
                // The user's entry is not a legal number.  
                // Put an error message in the message label 
                // and return without entering a number.
            message.setText("\"" + houseValueInput.getText() + "\" is not a legal number.");
            houseValueInput.selectAll();
            houseValueInput.requestFocus();
            return;
        }
        try {
            taxRate = Float.parseFloat(taxRateInput.getText());
        }
        catch (NumberFormatException e) {
                // The user's entry is not a legal number.  
                // Put an error message in the message label 
                // and return without entering a number.
            message.setText("\"" + taxRateInput.getText() + "\" is not a legal number.");
            taxRateInput.selectAll();
            taxRateInput.requestFocus();
            return;
        }
        //stats.enter(num);
        showData();
    } 
    /**
     *  Show the data from the StatCalc in the four output labels.
     */
    private void showData() {
        propertyTaxLabel.setText(" Property tax Due:  A LOT!!");
        /* Set the message label back to its normal text, in case it has
          been showing an error message.  For the user's convenience,
          select the text in the TextField and give the input focus
          to the text field.  That way the user can just start typing
          the next number. */
        message.setText("Enter house value and tax rate, then press return");
        houseValueInput.selectAll();
        houseValueInput.requestFocus();
    }

}  // end StatsCalcGUI
