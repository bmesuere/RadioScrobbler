package core;

import gui.MenuIcon;
import gui.PasswordDialog;
import gui.TrayMenu;

import java.io.IOException;

import providers.Provider;
import providers.StuBruProvider;
import util.AbstractModel;
import ch.rolandschaer.ascrblr.scrobbler.AudioscrobblerService;
import ch.rolandschaer.ascrblr.scrobbler.TrackInfo;
import ch.rolandschaer.ascrblr.util.ServiceException;

public class RadioScrobbler extends AbstractModel {

	private final static int INTERVAL = 5000;

	private final MenuIcon menuIcon;
	private final TrayMenu menu;

	private boolean running = false;

	private String username;
	private String password;

	private Provider provider;
	private TrackInfo laatsteTI;
	private String laatsteA;
	private String laatsteT;

	public RadioScrobbler() {
		menu = new TrayMenu(this);
		addChangeListener(menu);

		menuIcon = new MenuIcon();
		menuIcon.setPopupMenu(menu);
		addChangeListener(menuIcon);

		provider = new StuBruProvider();
		
		PasswordDialog pwd = new PasswordDialog();
		if(pwd.getResult()){
			username = pwd.getUsername();
			password = pwd.getPassword();
		}
		else
			System.exit(0);

		go();
	}

	private void go() {
		if (running)
			return;
		running = true;
		fireStateChanged();
		try {
			// inloggen
			AudioscrobblerService service = new AudioscrobblerService();
			service.setCredentials(username, password);

			// laatst tegengekomen artiest opslaan
			laatsteA = "";
			laatsteT = "";
			laatsteTI = null;

			// loop
			while (running) {
				// get currently playing
				String[] temp = provider.getNOA();

				// we hebben een nieuwe track
				if (!temp[0].equals(laatsteA) && !temp[1].equals(laatsteT)) {
					// oude track submitten
					if (laatsteTI != null) {
						service.submit(laatsteTI);
					}
					// nieuwe track bijhouden
					laatsteA = temp[0];
					laatsteT = temp[1];
					if (!laatsteT.equals("")) {
						// create a new TrackInfo only when there's info
						laatsteTI = new TrackInfo(
								laatsteA,
								laatsteT,
								System.currentTimeMillis() - 5000,
								ch.rolandschaer.ascrblr.scrobbler.TrackInfo.SourceType.R);
						fireStateChanged();
					} else {
						laatsteTI = null;
					}
				} else {
					// keep submitting the now playing
					if (laatsteTI != null) {
						service.notifyNew(laatsteTI);
					}
				}

				// check interval
				Thread.sleep(INTERVAL);
			}

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new RadioScrobbler();
	}

	public TrackInfo getCurrentTrackInfo() {
		return laatsteTI;
	}

	public String getCurrentTrack() {
		if (laatsteTI == null)
			return "Geen muziekinfo";
		else
			return laatsteA + " - " + laatsteT;
	}

	public void setProvider(Provider p) {
		provider = p;
	}

	public Provider getProvider() {
		return provider;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean state) {
		if (state) {
			(new Thread() {
				public void run() {
					go();
				}
			}).start();
		} else {
			running = false;
			fireStateChanged();
		}
	}
}
