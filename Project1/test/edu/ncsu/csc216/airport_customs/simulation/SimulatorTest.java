package edu.ncsu.csc216.airport_customs.simulation;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airport_customs.arriving_passengers.ArrivingAirplanes;

/**
 * JUnit test case for the Simulator class.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class SimulatorTest {
	/** One test Simulator to be used for testing. */
	private Simulator sim;
	/** A second Simulator to be used for testing. */
	private Simulator sim2;
	/** A third Simulator to be used for testing. */
	private Simulator sim3;
	/** A fourth Simulator to be used for testing. */
	private Simulator sim4;
	/** A fifth Simulator to be used for testing. */
	private Simulator sim5;

	/**
	 * Sets up the two simulators to be used for testing as well as resetting
	 * the passenger generator so the information is the same.
	 * 
	 * @throws java.lang.Exception
	 *             Throws exceptions.
	 */
	@Before
	public void setUp() throws Exception {
		// Reset for first simulation passenger generation.
		ArrivingAirplanes.resetFactory();
		// 3 Desks 7 Passengers
		sim = new Simulator(3, 7);

		// Reset for second simulation passenger generation.
		ArrivingAirplanes.resetFactory();
		// 4 Desks 7 Passengers
		sim2 = new Simulator(4, 7);

		// Reset for third simulation passenger generation.
		ArrivingAirplanes.resetFactory();
		// 15 Desks 1000 Passengers
		sim3 = new Simulator(15, 1000);

		// Reset for fourth simulation passenger generation.
		ArrivingAirplanes.resetFactory();
		// 17 Desks 1000 Passengers
		sim4 = new Simulator(17, 1000);

		// Reset for fifth simulation passenger generation.
		ArrivingAirplanes.resetFactory();
		// 10 Desks 30 Passengers
		sim5 = new Simulator(10, 30);
	}

	/**
	 * Test method for step() which steps the simulation one step at a time.
	 */
	@Test
	public void testStep() {
		//No steps taken so far.
		assertEquals(0, sim.getStepsTaken());
		sim.step();
		//One step taken
		assertEquals(1, sim.getStepsTaken());
		sim.step();
		sim.step();
		sim.step();
		sim.step();
		//Five steps taken
		assertEquals(5, sim.getStepsTaken());
	}

	/**
	 * Test method for getStepsTaken() which returns how many steps the
	 * simulator has taken so far.
	 */
	@Test
	public void testGetStepsTaken() {
		//No steps have been taken.
		assertEquals(0, sim.getStepsTaken());
		sim.step();
		//One step was made.
		assertEquals(1, sim.getStepsTaken());
	}

	/**
	 * Test method for totalNumberOfSteps() which is the total number of steps
	 * the simulation must complete before it is finished.
	 */
	@Test
	public void testTotalNumberOfSteps() {
		//7 Passengers means there will be 14 steps
		assertEquals(14, sim.totalNumberOfSteps());
		assertEquals(14, sim2.totalNumberOfSteps());
	}

	/**
	 * Test method for moreSteps() which returns true or false depending on if
	 * there are more stpes to be taken or not.
	 */
	@Test
	public void testMoreSteps() {
		sim.step();
		//Only one step taken
		assertTrue(sim.moreSteps());
		for (int i = 1; i <= 13; i++) {
			sim.step();
		}
		//14 steps have been taken
		assertFalse(sim.moreSteps());

	}

	/**
	 * Test method for getCurrentIndex() which gets the current line that is
	 * being used for either adding a passenger to the line or processing the
	 * passenger through customs and removing them from the line.
	 */
	@Test
	public void testGetCurrentIndex() {
		//No steps taken so current index is -1.
		assertEquals(-1, sim.getCurrentIndex());
		sim.step();
		//One step taken, first passenger is visitor so index will be 2.
		assertEquals(2, sim.getCurrentIndex());
		sim.step();
		sim.step();
		//Three steps taken, third passenger is resident so index will be 0.
		assertEquals(0, sim.getCurrentIndex());
		sim.step();
		sim.step();
		sim.step();
		sim.step();
		//Seven steps taken, seventh passenger is a resident so index will be 0.
		assertEquals(7, sim.getStepsTaken());
		assertEquals(0, sim.getCurrentIndex());
		
		//No steps taken so current index is -1.
		assertEquals(-1, sim2.getCurrentIndex());
		sim2.step();
		//One step taken, first passenger is visitor so index will be 3.
		assertEquals(3, sim2.getCurrentIndex());
		sim2.step();
		//Two step taken, second passenger is resident so index will be 2.
		assertEquals(2, sim2.getCurrentIndex());
	}

	/**
	 * Test method for getCurrentPassengerColor() which returns the color of the
	 * current passenger so that it can be displayed in the GUI.
	 */
	@Test
	public void testGetCurrentPassengerColor() {
		// Color for visitors who's process time is less than half.
		Color vis = new Color(255, 153, 153);
		// Color for visitors who's process time is more than half.
		Color vis2 = new Color(255, 0, 0);
		// Color for residents who's process time is less than half.
		Color res = new Color(153, 153, 255);
		// Color for residents who's process time is more than half.
		Color res2 = new Color(0, 0, 255);

		sim.step();
		//One step taken, first passenger is visitor so color will be for visitor.
		assertEquals(vis, sim.getCurrentPassengerColor());
		sim.step();
		//Two steps taken, second passenger is resident so color will be for resident.
		assertEquals(res, sim.getCurrentPassengerColor());
		sim.step();
		//Three steps taken, third passenger is resident so color will be for resident.
		assertEquals(res, sim.getCurrentPassengerColor());
		sim.step();
		//Four steps taken, fourth passenger is visitor so color will be for visitor.
		assertEquals(vis, sim.getCurrentPassengerColor());
		sim.step();
		//Five steps taken, fifth passenger is resident so color will be for resident.
		assertEquals(res2, sim.getCurrentPassengerColor());
		sim.step();
		//Six steps taken, sixth passenger is visitor so color will be for visitor.
		assertEquals(vis2, sim.getCurrentPassengerColor());
		sim.step();
		//Seven steps taken, seventh passenger is resident so color will be for resident.
		assertEquals(res, sim.getCurrentPassengerColor());

		// Checks the color of the second simulation.
		sim2.step();
		assertEquals(vis, sim2.getCurrentPassengerColor());
		sim2.step();
		assertEquals(res, sim2.getCurrentPassengerColor());
		sim2.step();
		assertEquals(res, sim.getCurrentPassengerColor());
		sim2.step();
		assertEquals(vis, sim2.getCurrentPassengerColor());
	}

	/**
	 * Test method for passengerClearedCustoms() which returns true or false
	 * depending on if the passenger currently at the customs desk has finished
	 * customs or not.
	 */
	@Test
	public void testPassengerClearedCustoms() {
		sim.step();
		//Passenger just got in line didn't process.
		assertFalse(sim.passengerClearedCustoms());
		sim.step();
		sim.step();
		sim.step();
		//Passenger just got in line didn't process.
		assertFalse(sim.passengerClearedCustoms());
		sim.step();
		sim.step();
		sim.step();
		sim.step();
		//Passenger finished waiting and cleared customs.
		assertTrue(sim.passengerClearedCustoms());

	}

	/**
	 * Test method for averageWaitTime() which returns the average wait time for
	 * the passengers during the simulation..
	 */
	@Test
	public void testAverageWaitTime() {
		int steps;

		sim.step();
		assertEquals(.0, sim.averageWaitTime(), 0.00001);
		for (int i = 1; i < 8; i++)
			sim.step();
		assertEquals(8, sim.getStepsTaken());
		assertEquals(0.0, sim.averageWaitTime(), 0.00001);
		sim.step();
		assertEquals(9, sim.getStepsTaken());
		assertEquals(0.0, sim.averageWaitTime(), 0.00001);
		// ------- Should be no wait time above this line. --------
		sim.step();
		sim.step();
		// Step 11 wait time should be 0.65
		assertEquals(11, sim.getStepsTaken());
		assertEquals(0.65, sim.averageWaitTime(), 0.00001);
		sim.step();
		// Step 12 wait time should be 1.0966666666666667
		assertEquals(12, sim.getStepsTaken());
		assertEquals(1.09666666, sim.averageWaitTime(), 0.00001);
		sim.step();
		// Step 13 wait time should be 2.113888888888889
		assertEquals(13, sim.getStepsTaken());
		assertEquals(2.113888888, sim.averageWaitTime(), 0.00001);
		sim.step();
		// Step 14 wait time should be 3.0595238095238093
		assertEquals(14, sim.getStepsTaken());
		assertEquals(3.05952, sim.averageWaitTime(), 0.00001);

		// --------------Testing Second Simulation--------------
		for (int i = 0; i < 12; i++)
			sim2.step();
		// Step 12 wait time should be 0.4633333333333333
		assertEquals(12, sim2.getStepsTaken());
		assertEquals(0.4633333333333333, sim2.averageWaitTime(), 0.00001);
		sim2.step();
		// Step 13 wait time should be 0.8666666666666667
		assertEquals(0.8666666666666667, sim2.averageWaitTime(), 0.00001);
		sim2.step();
		assertEquals(1.9904761904761905, sim2.averageWaitTime(), 0.00001);

		// --------------Testing Third Simulation--------------
		steps = sim3.totalNumberOfSteps();
		for (int i = 0; i < steps; i++)
			sim3.step();
		assertEquals(2000, sim3.getStepsTaken());
		//After finishing the simulation the average wait time should be 101.836167.
		assertEquals(101.836167, sim3.averageWaitTime(), 0.00001);

		// --------------Testing Fourth Simulation--------------
		steps = sim4.totalNumberOfSteps();
		for (int i = 0; i < steps; i++)
			sim4.step();
		//After finishing the simulation the average wait time should be 81.109817.
		assertEquals(81.109817, sim4.averageWaitTime(), 0.00001);

		// --------------Testing Fifth Simulation--------------
		steps = sim5.totalNumberOfSteps();
		for (int i = 0; i < steps; i++)
			sim5.step();
		//After finishing the simulation the average wait time should be 3.100556.
		assertEquals(3.100556, sim5.averageWaitTime(), 0.00001);
	}

	/**
	 * Test method for averageProcessTime() which returns the average process
	 * time for the passengers during the simulation.
	 */
	@Test
	public void testAverageProcessTime() {
		int steps = sim.totalNumberOfSteps();
		for (int i = 0; i < steps; i++)
			sim.step();
		assertEquals(4.52142857142857, sim.averageProcessTime(), 0.00001);
	}

}
