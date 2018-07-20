package edu.ncsu.csc216.androtech.model.repair_center;

import java.util.Random;

import edu.ncsu.csc216.androtech.model.devices.Device;

/**
 * The RepairCenter class is used to create the list of TechDroids that will be
 * used to repair the devices. This class will order the list of TechDroids
 * according to their type: VRDroids are on top, followed by ExpertDroids, and
 * then ComDroids are at the bottom of the list.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public class RepairCenter {
	/** The maximum number of TechDroids that can be active at one time. */
	private static final int MAX_DROIDS = 30;
	/** The default number of TechDroids. */
	private static final int DEFAULT_SIZE = 5;
	/** The current size of the list of TechDroids. */
	private int size;
	/** The array of TechDroids used for fixing devices. */
	private TechDroid[] repairDroids = new TechDroid[MAX_DROIDS + 1];
	/** The number of ComDroids created so far. */
	private int comDroids;
	/** The number of VRDroids created so far. */
	private int vrDroids;
	/** The number of ExpertDroids created so far. */
	private int expertDroids;

	/**
	 * Constructor for the RepairCenter object. This initializes the first five
	 * TechDroids to being 2 ComDroids, 2 VRDroids, and one ExpertDroid.
	 */
	@SuppressWarnings("static-access")
	public RepairCenter() {
		repairDroids[0].startDroidNumberingAt01();
		comDroids = 0;
		vrDroids = 0;
		expertDroids = 0;
		size = 0;
		initDroids();

	}

	/**
	 * This class is used to set up the first five TechDroids exactly how they
	 * should be set up.
	 * 
	 */
	private void initDroids() {

		add(new ComDroid());
		comDroids++;

		add(new ExpertDroid());
		expertDroids++;

		add(new VRDroid());
		vrDroids++;

		add(new ComDroid());
		comDroids++;

		add(new VRDroid());
		vrDroids++;

		size = DEFAULT_SIZE;
	}

	private void add(TechDroid droid) {
		String addingDroid = droid.getDroidID().substring(2, 3);
		int insertPoint = 0;

		if (size == 0) {
			repairDroids[0] = droid;
		} else if (size == 1) {
			if (repairDroids[0].getDroidID().substring(2, 3)
					.equalsIgnoreCase("v")) {
				repairDroids[1] = droid;
			} else {
				repairDroids[1] = repairDroids[0];
				repairDroids[0] = droid;
			}
		} else {
			if (addingDroid.equalsIgnoreCase("C")) {
				insertPoint = size;
			} else if (addingDroid.equalsIgnoreCase("E")) {
				insertPoint = size - comDroids;
			} else {
				insertPoint = 0;
			}

			// Adding the TechDroid in the right location.
			for (int i = size; i >= insertPoint; i--) {
				repairDroids[i + 1] = repairDroids[i];
			}

			repairDroids[insertPoint] = droid;
		}

		size++;
	}

	/**
	 * Creates a new TechDroid depending on the list of existing devices.
	 */
	public void addTechDroid() {
		Random randomNum = new Random();

		if (size < MAX_DROIDS) {
			if (((totalNumberOfDroids()) / 3) <= (expertDroids + vrDroids)) {
				add(new ComDroid());
				comDroids++;
			} else {

				double randomNumber = randomNum.nextDouble();

				if (randomNumber >= 0.34) {
					add(new ExpertDroid());
					expertDroids++;
				} else {
					add(new VRDroid());
					vrDroids++;
				}
			}
		}
	}

	/**
	 * Getter method that returns the number of TechDroids that are able to do
	 * repairs to devices.
	 * 
	 * @return Returns the size of the array of techDoirds.
	 */
	public int numberOfAvailableDroids() {
		int i = 0;
		int coutner = 0;

		while (i < size) {
			if (!repairDroids[i].isAssigned())
				coutner++;
			i++;
		}

		return coutner;
	}

	/**
	 * Gets the techDroid at a specific index and returns it.
	 * 
	 * @param index
	 *            The index it is looking at to find the techDroid.
	 * @return Returns the techDroid at the index.
	 */
	public TechDroid getDroidAt(int index) {
		if (index >= size)
			return null;
		else
			return repairDroids[index];
	}

	/**
	 * Returns the total number of TechDroids in the array.
	 * 
	 * @return size The size of the array of TechDroids.
	 */
	public int totalNumberOfDroids() {
		return size;
	}

	/**
	 * Releases the Device that the techDroid at the index is currently fixing.
	 * 
	 * @param index
	 *            The index it is looking at to find the techDroid.
	 * @return Returns the device that the techDroid was working on.
	 */
	public Device release(int index) {
		if (index < size)
			if(repairDroids[index] != null)
				return repairDroids[index].release();
			else 
				return null;
		else
			return null;
	}

	/**
	 * Prints the list of techDroids to a string.
	 * 
	 * @return Returns the string of techDroids.
	 */
	public String printDroids() {
		// StringBuffer that will display the list of TechDroids.
		StringBuffer list = new StringBuffer();
		int i = 0;

		// Creates the information inside the StringBuffer
		while (i < size) {
			if (repairDroids[i] != null) {
				list.append(repairDroids[i].toString());
				list.append("\n");
			}
			i++;
		}

		// Converts the StringBuffer list to an actual String
		return list.toString();
	}

}
