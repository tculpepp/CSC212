/**
 * Implementation of the Selection Sort algorithm.
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class SelectionSortGUI {
	
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		while (true) { //set a loop until user breaks it with "e"
			String numbers = JOptionPane.showInputDialog("Enter 10 numbers separated by commas: ");
			String sepNum[] = numbers.replaceAll("\\s", "").split(",");//clean the string and split it
			int arr[] = new int[sepNum.length]; //initialize array to hold the converted ints
			for (int i = 0; i < sepNum.length; i++) { //loop through the string array to convert to int array
			    try {
			        arr[i] = Integer.parseInt(sepNum[i]);
			    } catch (NumberFormatException nfe) {
			    	continue;
			    };
			}
			int i, j, minValue, minIndex, temp=0;
			for (i=0; i<arr.length; i++) { 		//outer loop grabs the a value
				minValue = arr[i]; 				//assign the current value as the  min
				minIndex = i; 					//the index number of the current value
				for (j=i; j<arr.length; j++) { 	//inner loop compares min to the other values
					if (arr[j] < minValue) {	//if compared value < min, replace min value and index
						minValue = arr[j];		//replace the min with the current value
						minIndex = j;			//replace the index with the current index
					}
				}
				if (minValue < arr[i]) {		//if a new min was found, we swap
					temp = arr[i];				//place the new min in temp
					arr[i] = minValue;			//swap old min into the new one's position
					arr[minIndex] = temp;		//swap new min into old min's position
				}
			}
			JPanel panel = new JPanel(new GridLayout(2,1));
		    panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW). //input map to capture "e"
		    	put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), "exit");
		    panel.getActionMap().put("exit", new AbstractAction() { //action map to exit
		        public void actionPerformed(ActionEvent e) {
	               System.exit(0);
	            }
	        });
		    panel.add(new JLabel("Sorted Numbers: "+ Arrays.toString(arr)));
		    panel.add(new JLabel("Press 'e' to exit"));
		    JOptionPane.showMessageDialog(null,panel);
		}
	}
}
