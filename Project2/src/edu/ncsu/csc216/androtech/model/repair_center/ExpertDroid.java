package edu.ncsu.csc216.androtech.model.repair_center;

/**
 * This class is used to create the ExpertDroid, which can be used to fix either
 * VRDevices or ComDevices.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 */
public class ExpertDroid extends TechDroid {

	/**
	 * Constructor for the ExpertDroid. This is the repair droid that is able to
	 * fix either Device. Sends the letter E to the TechDroid constructor to be
	 * used to created the droidID
	 */
	public ExpertDroid() {
		super("E");
	}
}
