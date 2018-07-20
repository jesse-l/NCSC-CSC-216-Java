//Imported for the general package it is in
package edu.ncsu.csc216.airport_customs.ui;

//Imported to handle Scanner
import java.util.Scanner;
//Imported to access Simulator class
import edu.ncsu.csc216.airport_customs.simulation.Simulator;

/**
 * Simple class to run customs hall simulations. Input is from standard input.
 * Output is to standard output. No error checking is performed.
 * 
 * @author Jo Perry
 */
public class SimulationRunner {

	/**
	 * Starts the command line simple simulation.
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Number of passengers: ");
		int numberOfPassengers = sc.nextInt();
		System.out.print("Number of customs desks: ");
		int numberOfCustomsDesks = sc.nextInt();
		Simulator s = new Simulator(numberOfCustomsDesks, numberOfPassengers);
		while (s.moreSteps()) {
			s.step();
			if (s.passengerClearedCustoms())
				System.out.println("Step: " + s.getStepsTaken());
		}
		System.out.printf("Average Wait Time: %1$.2f minutes%n",
				s.averageWaitTime());
		System.out.printf("Average Process Time: %1$.2f minutes%n",
				s.averageProcessTime());
		System.out.printf("Average Wait Time: %1$.6f minutes%n",
				s.averageWaitTime());
		System.out.printf("Average Process Time: %1$.6f minutes%n",
				s.averageProcessTime());
		sc.close();
	}

}