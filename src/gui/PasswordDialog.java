package gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PasswordDialog extends JDialog implements ActionListener {

	// GUI objects used
	private final JTextField username;
	private final JPasswordField password;
	private JButton ok, can;
	private GridBagConstraints gbc;

	// boolean to check if user pressed ok or cancel
	private boolean result = false;

	/**
	 * create a new password dialog
	 */
	public PasswordDialog() {
		// boring GUI stuff
		super((JDialog) null, "Enter your last.fm credentials", true);
		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		username = new JTextField(15);
		password = new JPasswordField(15);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(new JLabel("Username:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(username, gbc);
		gbc.gridy = 1;
		gbc.gridx = 0;
		add(new JLabel("Password:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(password, gbc);
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 5, 0, 5);
		addOKCancelPanel();
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getRootPane().setDefaultButton(ok);
		setVisible(true);
	}

	/**
	 * Add the buttons to the dialog
	 */
	private void addOKCancelPanel() {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		createButtons(p);
		add(p, gbc);
	}

	/**
	 * Create new buttons and add them to given panel
	 * 
	 * @param p
	 *            JPanel to which we add the buttons
	 */
	private void createButtons(JPanel p) {
		p.add(ok = new JButton("OK"));
		ok.addActionListener(this);
		p.add(can = new JButton("Cancel"));
		can.addActionListener(this);
	}

	/**
	 * Close the dialog
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			result = true;
			setVisible(false);
		} else if (e.getSource() == can) {
			result = false;
			setVisible(false);
		}
	}

	/**
	 * returns the username
	 * 
	 * @return
	 */
	public String getUsername() {
		return username.getText();
	}

	/**
	 * return the password
	 * 
	 * @return
	 */
	public String getPassword() {
		return password.getText();
	}

	/**
	 * returns true when the user clicked ok
	 * 
	 * @return
	 */
	public boolean getResult() {
		return result;
	}
}
