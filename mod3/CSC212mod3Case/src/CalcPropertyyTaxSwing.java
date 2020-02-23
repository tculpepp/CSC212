import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class CalcPropertyyTaxSwing {
	
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		while (true) {
			float taxRate = Float.parseFloat(JOptionPane.showInputDialog("Enter tax Rate (%):"));
			float houseValue = Float.parseFloat(JOptionPane.showInputDialog("Enter House Value:"));
			float taxAmount = (houseValue * (taxRate/100));
			JPanel panel = new JPanel(new GridLayout(2,1));
		    panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW). //input map to capture "e"
		    	put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), "exit");
		    panel.getActionMap().put("exit", new AbstractAction() { //action map to exit
		        public void actionPerformed(ActionEvent e) {
	               System.exit(0);
	            }
	        });
		    panel.add(new JLabel("Property Tax: $"+(String.format("%,.2f", taxAmount))));
		    panel.add(new JLabel("Press 'e' to exit"));
		    JOptionPane.showMessageDialog(null,panel);
		}
	}

}
