package gui;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import core.RadioScrobbler;

/**
 * custom tray icon which displays the current track in the popup, listens to a
 * RadioScrobbler object to update the popup text
 * 
 * @author Bart Mesuere
 * 
 */
public class MenuIcon extends TrayIcon implements ChangeListener {

	// location of the image
	// TODO: not working when packaged
	private static final String IMG = "images/note.gif";

	/**
	 * creates a new menuicon and adds it to the system tray
	 */
	public MenuIcon() {
		super(Toolkit.getDefaultToolkit().getImage(IMG));
		try {
			SystemTray.getSystemTray().add(this);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * updates the popup text when the state of the radioscrobber object changes
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO: check before casting
		RadioScrobbler rs = (RadioScrobbler) e.getSource();
		setToolTip(rs.getProvider().getName() + ": " + rs.getCurrentTrack());
	}

}
