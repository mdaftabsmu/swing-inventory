import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginDemoTest extends JDialog {
	private JLabel labelProductName = new JLabel("Month:");
	private JLabel labelCategory = new JLabel("Year:");
	
	private JTextField fieldProductName = new JTextField(10);
	private JTextField fieldDescription = new JTextField(10);
	
	private JButton buttonSave = new JButton("Save");
	private JButton buttonCancel = new JButton("Cancel");
	LoginDemoTest() {
		setTitle("Create Product");
		
		setLayout(new GridBagLayout());
		
    	GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.anchor = GridBagConstraints.WEST;
		
		add(labelProductName, constraints);
		
		constraints.gridx = 1;
		add(fieldProductName, constraints);
		
		constraints.gridy = 1;
		constraints.gridx = 0;		
		add(labelCategory, constraints);
		
		
		constraints.gridx = 1;
		add(fieldDescription, constraints);		
		
			
		
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelButtons.add(buttonSave);
		panelButtons.add(buttonCancel);
		
		constraints.gridy = 4;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		
		add(panelButtons, constraints);		
		
		setTitle("Create Product");
		
		setSize(380, 280);
		setLocationRelativeTo(null);
		setVisible(true);
    }
    public static void main(String[] args) {
        new LoginDemoTest();
    }
    
}