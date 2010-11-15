package util;
import java.awt.EventQueue;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

/**
 * Base model class that implements registering and calling listeners
 * @author Pieter De Baets
 */
abstract public class AbstractModel {

	private final EventListenerList listenerList;
	private final ChangeEvent changeEvent;

	/*
	 * Create the Model
	 */
	public AbstractModel() {
		listenerList = new EventListenerList();
		changeEvent = new ChangeEvent(this);
	}

	/**
	 * Register a new listener
	 * @param l ChangeListener
	 */
	public void addChangeListener(ChangeListener l) {
		listenerList.add(ChangeListener.class, l);
	}

	/**
	 * Remove listener
	 * @param l ChangeListener
	 */
	public void removeChangeListener(ChangeListener l) {
		listenerList.remove(ChangeListener.class, l);
	}

	/**
	 * Notify all listeners that have registered interest for notification on this event type.
	 */
	protected void fireStateChanged() {
        // events have to be dispatched in the event-thread,
        // otherwise things get horribly brokenw
		if(!EventQueue.isDispatchThread()) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					fireStateChanged();
				}
			});
		}
		else {
			// call all listeners
			Object[] listeners = listenerList.getListenerList();
			for (int i = listeners.length - 2; i >= 0; i -= 2) {
				if (listeners[i] == ChangeListener.class) {
					((ChangeListener) listeners[i + 1]).stateChanged(changeEvent);
				}
			}
		}
	}
}
