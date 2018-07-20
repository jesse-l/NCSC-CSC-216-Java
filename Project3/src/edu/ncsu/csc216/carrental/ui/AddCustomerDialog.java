package edu.ncsu.csc216.carrental.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.ncsu.csc216.carrental.model.Customer;

/**
 * This class is responsible for making a dialog box asking for information to
 * create a new customer.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class AddCustomerDialog extends JDialog {
	private static final long serialVersionUID = 1722461442896581806L;
	private Customer newCustomer;
	private final static String TITLE = "Add New Customer";
	GridBagConstraints gbc = new GridBagConstraints();
	private final static String SUBMIT = "Submit";
	private final static String CANCEL = "Cancel";
	private final static String FIRST_NAME = "Customer's First Name:";
	private final static String LAST_NAME = "Customer's Last Name:";
	private final static String CUSTOMER_ID = "Customer's ID Number:";

	private JLabel lbFirstName = new JLabel(FIRST_NAME);
	private JLabel lbLastName = new JLabel(LAST_NAME);
	private JLabel lbCustomerId = new JLabel(CUSTOMER_ID);

	private JTextField txtFirstName = new JTextField();
	private JTextField txtLastName = new JTextField();
	private JTextField txtID = new JTextField();

	private JButton btnAdd = new JButton(SUBMIT);
	private JButton btnCancel = new JButton(CANCEL);

	private JPanel pnlText = new JPanel();
	private JPanel pnlBtn = new JPanel();

	private String firstNameStr;
	private String lastNameStr;
	private String idStr;

	/**
	 * Constructor for the AddCustomerDialog. This method constructs the dialog
	 * box.
	 * 
	 * @param frame
	 *            This is the frame that is passed to it.
	 */
	public AddCustomerDialog(Frame frame) {
		super(frame, TITLE, true);
		setUp();
		this.getContentPane().add(pnlText, BorderLayout.CENTER);
		this.getContentPane().add(pnlBtn, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				firstNameStr = txtFirstName.getText().trim();
				lastNameStr = txtLastName.getText().trim();
				idStr = txtID.getText().trim();

				try {
					newCustomer = new Customer(firstNameStr, lastNameStr, idStr);
					setVisible(false);
				} catch (IllegalArgumentException error) {
					JOptionPane.showMessageDialog(null, error.getMessage());
				}
			}
		});

		pack();
	}

	/**
	 * This method is used to setUp the components of the GUI.
	 */
	private void setUp() {

		this.getContentPane().add(pnlText, BorderLayout.CENTER);
		pnlText.setLayout(new GridLayout(3, 2));
		pnlText.add(lbFirstName);
		pnlText.add(txtFirstName);
		pnlText.add(lbLastName);
		pnlText.add(txtLastName);
		pnlText.add(lbCustomerId);
		pnlText.add(txtID);

		pnlBtn.setLayout(new FlowLayout());
		pnlBtn.add(btnAdd);
		pnlBtn.add(btnCancel);

	}

	/**
	 * Returns the customer that is created using this dialog box.
	 * 
	 * @return Returns the customer the dialog created.
	 */
	public Customer getNewCustomer() {
		return newCustomer;
	}
}
