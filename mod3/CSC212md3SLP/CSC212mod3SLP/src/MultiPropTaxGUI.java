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
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		numHousesInput = new TextField();
		numHousesInput.setMaxWidth(50);
		taxRateInput = new TextField(); //input for tax rate
		taxRateInput.setMaxWidth(100);
		Label instLabel = new Label("Enter the number of houses and the tax rate below");
		messageLabel = new Label(); //error label in case bad data is entered
		messageLabel.setVisible(false); //hide it until needed
		Label exitLabel = new Label("(press 'E' to exit)"); //instructions on how to exit
		Button enterButton = new Button("Enter"); //setup the enter button
		enterButton.setOnAction(e -> doEnter());
		enterButton.setVisible(false); 	
		Button nextButton = new Button("Next");
		nextButton.setDefaultButton(true);
		
		FlowPane centerFlow = new FlowPane();
		centerFlow.setPrefWrapLength(200); // preferred height = 200
		centerFlow.setOrientation(Orientation.VERTICAL);
		centerFlow.setStyle("-fx-alignment: center; -fx-padding:10px;");
		
		VBox bottomVBox = new VBox(messageLabel, exitLabel);
		bottomVBox.setStyle("-fx-alignment: center; -fx-padding:10px;");
		
		VBox leftVBox = new VBox(new Label("Number of Houses:"),numHousesInput,new Label("Tax Rate (%): "),taxRateInput,nextButton,enterButton);
		leftVBox.setStyle("-fx-alignment: center; -fx-padding:10px;");

		nextButton.setOnAction(e -> { //validate and use the number of houses entered to build the entry boxes
			try {
				int numHouses = Integer.parseInt(numHousesInput.getText()); //get the number of houses and set it to variable
				centerFlow.getChildren().add(new Label("House Values:"));
				numHousesInput.setStyle("-fx-border-color: black");
				messageLabel.setVisible(false);
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
				instLabel.setText("Enter each house value in the boxes below.");
				stage.sizeToScene(); //ensure the stage re-sizes
			} catch (NumberFormatException e2) {
				messageLabel.setText("Invalid Entry, Please Try Again"); //set the messageLabel text
		    	messageLabel.setStyle("-fx-text-fill: red");		//color it red
		    	messageLabel.setVisible(true); 						//and make it visible
		    	numHousesInput.setStyle("-fx-border-color: red"); 	//highlight the error box in red
			}
		});
		
		BorderPane root = new BorderPane();
		root.setMinWidth(335);
		root.setStyle("-fx-alignment: center; -fx-padding:10px;");
		root.setTop(instLabel);
		root.setLeft(leftVBox);
		root.setCenter(centerFlow);
		root.setBottom(bottomVBox);
		
		Scene scene = new Scene(root);
    	scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> { //event filter to catch an E to exit
    		if (e.getCode() == KeyCode.E) System.exit(0);
    	});
	    stage.setScene(scene);
	    stage.setTitle("Property Tax Calculator");
	    stage.show();
	}
	private void doEnter() {
		float houseTotal = 0; //holds total house value to be taxed
		int numValidated = 0; //holds number of validated entries
		float taxRate = validateInput(taxRateInput);
		for (TextField hv : houseValues) {
			if (validateInput(hv)!=0) { //if input validation =
				houseTotal = Float.parseFloat(hv.getText()) + houseTotal;
				numValidated++;
				System.out.println(houseTotal);//***********DELETE THIS***************
			}
		}
		if (numValidated == houseValues.length && taxRate !=0) {
			float taxAmount = houseTotal * taxRate/100;
			System.out.println(taxAmount); //*********************************************** don't forget to delete this!
			messageLabel.setStyle("-fx-text-fill: black");
			messageLabel.setVisible(true);
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
	    	messageLabel.setStyle("-fx-text-fill: red");								//color it red
	    	messageLabel.setVisible(true); 												//and make it visible
	    	userInput.setStyle("-fx-border-color: red"); 								//highlight the error box in red
	    	return 0;
	    }
    }
}