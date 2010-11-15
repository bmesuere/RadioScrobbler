package gui;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import core.RadioScrobbler;


public class MenuIcon extends TrayIcon implements ChangeListener{
	
	private static final String IMG = "images/note.gif";
	
	public MenuIcon(){
		super(Toolkit.getDefaultToolkit().getImage(IMG));
		try {
			SystemTray.getSystemTray().add(this);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		RadioScrobbler rs = (RadioScrobbler) e.getSource();
		setToolTip(rs.getProvider().getName() + ": " + rs.getCurrentTrack());
	}

}
