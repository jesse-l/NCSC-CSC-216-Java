package edu.ncsu.csc216.airport_customs.simulation;

import edu.ncsu.csc216.airport_customs.queues.TransitSystem;

/**
 * A calendar that determines the order in which passengers leave their queues.
 * The arrival hall is a queue, as are the wait lines at customs desks.
 * An event corresponds to passenger leaving their queue.
 * 
 * @author Jo Perry
 * @version 3.0
 */
public class EventCalendar {

	/** Collection of process stations for the simulation */
	private TransitSystem[] customsDesk;
	/** Queue of items that feed the process stations */
	private TransitSystem arrivalHall;

	/**
	 * Initializes the queues in the EventCalendar.
	 * 
	 * @param desk
	 *            all customs processing desks
	 * @param arrivalHall
	 *            passengers arriving from the arrival gates
	 */
	public EventCalendar(TransitSystem[] desk,
			TransitSystem arrivalHall) {
		this.customsDesk = desk;
		this.arrivalHall = arrivalHall;
	}

	/**
	 * Determines which customs desk or if arrival hall contains the next passenger to be processed. 
	 * With a tie, the desk with the smallest index wins. If the tie is with the arrival hall, the arrival hall wins.
	 * 
	 * @return The line whose front passenger has the earliest departure time.
	 * @throws IllegalStateException if all lines are empty.
	 */
	public TransitSystem nextToBeProcessed() {
		// Time the next item leaves a queue
		int nextToArrive = arrivalHall.departTimeNext();
		int soonest = 0;
		for (int k = 1; k < customsDesk.length; k++)
			if (customsDesk[k].departTimeNext() < customsDesk[soonest].departTimeNext())
				soonest = k;
		int customsDeskDeparture = customsDesk[soonest].departTimeNext();

		// Are all queues empty?
		if (nextToArrive == Integer.MAX_VALUE
				&& customsDeskDeparture == Integer.MAX_VALUE) {
			throw new IllegalStateException();
		}

		// Is the next event a passenger leaving the arrival hall?
		if (nextToArrive <= customsDeskDeparture) {
			return arrivalHall;
		}
		
		// The next event is a passenger leaving the customs area.
		return customsDesk[soonest];
	}
}