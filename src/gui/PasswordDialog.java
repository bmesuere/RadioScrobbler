package gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

	private final JTextField username;
	private final JPasswordField password;
	private JButton ok, can;
	private boolean result = false;
	private GridBagConstraints gbc;

	public PasswordDialog() {
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
		gbc.gridy=1;
		gbc.gridx=0;
		add(new JLabel("Password:"),gbc);
		gbc.gridx=1;
		gbc.gridy=1;
		add(password, gbc);
		gbc.gridy=2;
		gbc.gridx=0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 5, 0, 5);
		addOKCancelPanel();
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void addOKCancelPanel() {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		createButtons(p);
		add(p, gbc);
	}

	private void createButtons(JPanel p) {
		p.add(ok = new JButton("OK"));
		ok.addActionListener(this);
		p.add(can = new JButton("Cancel"));
		can.addActionListener(this);
	}

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
	
	public String getUsername(){
		return username.getText();
	}
	
	public String getPassword(){
		return password.getText();
	}
	
	public boolean getResult(){
		return result;
	}
}
