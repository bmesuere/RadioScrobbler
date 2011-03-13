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
	private static final String NOTE = "images/note.gif";
	private static final String NOTE_PLAY = "images/note_play.gif";
	private static final String NOTE_PAUSE = "images/note_pause.gif";

	/**
	 * creates a new menuicon and adds it to the system tray
	 */
	public MenuIcon() {
		super(Toolkit.getDefaultToolkit().getImage(NOTE));
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
		if (rs.isRunning()) {
			setToolTip(rs.getProvider().getName() + ": " + rs.getCurrentTrack());
			setImage(Toolkit.getDefaultToolkit().getImage(NOTE_PLAY));
		} else {
			setToolTip("Paused");
			setImage(Toolkit.getDefaultToolkit().getImage(NOTE_PAUSE));
		}
	}

}
