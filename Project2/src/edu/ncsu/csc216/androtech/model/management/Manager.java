package edu.ncsu.csc216.androtech.model.management;

import edu.ncsu.csc216.androtech.model.devices.*;

/**
 * Operations that must be supported to manage a service facility that has a
 * list of "tiered" items awaiting service and service droids that perform
 * repairs on devices.
 * 
 * @author Jo Perry
 * @author David Wright
 */
public interface Manager {

	/**
	 * Puts an item in the service wait list.
	 * 
	 * @param k
	 *            Kind of item
	 * @param b
	 *            Item data (such as serial number)
	 * @param c
	 *            Item data (such as owner name)
	 * @param x
	 *            Item tier
	 */
	public void putOnWaitingList(String k, String b, String c, int x);

	/**
	 * Puts an item in the list of those awaiting service.
	 * 
	 * @param v
	 *            The item to put on waiting list
	 */
	public void putOnWaitingList(Prioritizable v);

	/**
	 * Removes an item meeting the given filter from the list of items awaiting
	 * service.
	 * 
	 * @param filter
	 *            Filters the list of items considered for removal
	 * @param position
	 *            Position in the filtered list of the item to be removed
	 * @return The item that was removed, or null if nothing was removed
	 */
	public Prioritizable remove(String filter, int position);

	/**
	 * Fills the service bays with items awaiting service.
	 */
	public void assignDroids();

	/**
	 * Releases the item from the given service bay.
	 * 
	 * @param droid
	 *            index of the droid where the item is being serviced
	 * @return Item that was released from service, or null if the droid was
	 *         empty
	 */
	public Prioritizable releaseFromService(int droid);

	/**
	 * Adds a new service droid to the service center.
	 */
	public void addNewDroid();

	/**
	 * A string representation of the list of items awaiting service that meet
	 * the given filter.
	 * 
	 * @param filter
	 *            Determines which items are are of interest
	 * @return String String representation of the filtered list
	 */
	public String printWaitList(String filter);

	/**
	 * A string representation of the list of bays in the service area.
	 * 
	 * @return String representation of the service area
	 */
	public String printDroids();
}
