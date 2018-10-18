package employeecrud.ui.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import employeecrud.factory.PersonFactory;
import employeecrud.model.IEmployee;
import employeecrud.model.IPerson;
import employeecrud.model.PersonList;
import employeecrud.ui.combobox.EmployeeRenderer;
import employeecrud.ui.constants.UIMessageConstants;

@SuppressWarnings("serial")
public class EmployeeDialog extends JDialog {

	private IEmployee employee;

	private JTextField employeeNameTextField;
	private JComboBox<Object> employeePartnerComboBox;

	private PersonList employeeList;

	public JTextField getEmployeeNameTextField() {

		return this.employeeNameTextField;
	}

	public JComboBox<Object> getEmployeePartnerComboBox() {

		return this.employeePartnerComboBox;
	}

	public void setEmployee(IEmployee employee) {

		this.employee = employee;

		if (employee.getPartner() != null)
			this.employeePartnerComboBox.setSelectedItem(employee.getPartner());

		this.employeePartnerComboBox.removeItem(this.employee);

		this.employeeNameTextField.setText(employee.getName());
	}

	public boolean isAddMode() {

		return this.employee == null;

	}

	public EmployeeDialog(PersonList employeeList) {

		this.employeeList = employeeList;

		this.setTitle("Add new employee");
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(400, 150));

		this.add(createEmployeeForm(), BorderLayout.NORTH);
		this.add(this.createSaveButton(), BorderLayout.SOUTH);

	}

	@SuppressWarnings("unchecked")
	private JPanel createEmployeeForm() {

		this.employeeNameTextField = new JTextField();
		this.employeePartnerComboBox = new JComboBox<Object>();
		this.employeePartnerComboBox.setRenderer(new EmployeeRenderer());
		this.setComboBoxValues(employeeList);

		JPanel employeeFormPanel = new JPanel();
		employeeFormPanel.setLayout(new GridLayout(2, 1));
		employeeFormPanel.add(this.employeeNameTextField);
		employeeFormPanel.add(this.employeePartnerComboBox);

		employeeFormPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		return employeeFormPanel;

	}

	private void setComboBoxValues(PersonList employeeList) {

		this.employeePartnerComboBox.addItem("");

		for (IPerson employee : employeeList) {
			this.employeePartnerComboBox.addItem(employee);
		}
	}

	private void saveForm() throws IllegalArgumentException {

		String enteredName = employeeNameTextField.getText();
		
		if(enteredName == null || enteredName.length() < 1) {
			this.showMessage(UIMessageConstants.NAME_IS_EMPTY);
			return;
		}
		
		
		IEmployee tmpEmployee = employee;

		if (isAddMode())
			tmpEmployee = (IEmployee) PersonFactory.create("employee", enteredName);
		else
			tmpEmployee.setName(enteredName);

		Object selectedPartner = employeePartnerComboBox.getSelectedItem();

		if (selectedPartner instanceof IEmployee)
			tmpEmployee.setPartner((IEmployee) selectedPartner);
		else
			tmpEmployee.clearPartner();

		if (isAddMode())
			employeeList.add(tmpEmployee);
		
		this.dispose();

	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	private JButton createSaveButton() {
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					saveForm();
				} catch (IllegalArgumentException e) {
					showMessage(e.getMessage());

				}
			}
		});
		return saveButton;

	}

}
