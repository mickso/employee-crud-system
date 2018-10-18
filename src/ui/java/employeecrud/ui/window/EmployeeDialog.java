package employeecrud.ui.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import employeecrud.factory.PersonFactory;
import employeecrud.model.IEmployee;
import employeecrud.model.IPerson;
import employeecrud.model.PersonList;
import employeecrud.ui.combobox.EmployeeRenderer;

@SuppressWarnings("serial")
public class EmployeeDialog extends JDialog {
	
	private IEmployee employee;
	
	
	private JTextField employeeNameTextField;
	private JComboBox<Object> employeePartnerComboBox;
	
	private PersonList employeeList;
	
	public EmployeeDialog(PersonList employeeList) {
		
		this.employeeList = employeeList;		
		
		this.setTitle("Add new employee");
		
		setModal(true);
		this.employeeNameTextField = new JTextField();
		this.employeePartnerComboBox = new JComboBox<Object>();		
		this.employeePartnerComboBox.setRenderer(new EmployeeRenderer());
		
		
        JPanel employeeFormPanel = new JPanel();
        employeeFormPanel.setLayout(new GridLayout(2,1));
        employeeFormPanel.add(this.employeeNameTextField);
        employeeFormPanel.add(this.employeePartnerComboBox);        
        
        this.setComboBoxValues(employeeList);
        
        employeeFormPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);        
        
        this.setPreferredSize(new Dimension(400, 150));
        
        this.add(employeeFormPanel, BorderLayout.NORTH);
        
        JButton saveButton = this.createSaveButton(this.employeeNameTextField, this.employeePartnerComboBox, this.employeeList, this);
       
        
        this.add(saveButton, BorderLayout.SOUTH);
		
	}
	
	private void setComboBoxValues(PersonList employeeList) {
		
		this.employeePartnerComboBox.addItem("");
		
		for(IPerson employee: employeeList) {			
			this.employeePartnerComboBox.addItem(employee);			
		}					
	}
	
	public void setEmployee(IEmployee employee) {
		
		this.employee = employee;
		
		if(employee.getPartner() != null) 			
			this.employeePartnerComboBox.setSelectedItem(employee.getPartner());
			
				
		this.employeeNameTextField.setText(employee.getName());	
	}
	
	public IEmployee getEmployee() {
		return this.employee;
	}
	
	public boolean isAddMode() {
		
		return this.getEmployee() == null;
		
	}
	
	private JButton createSaveButton(
			JTextField employeeNameTextField, 
			JComboBox<Object> employeePartnerComboBox, 
			PersonList employeesList, 
			EmployeeDialog parent) {
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
            	
            	
            	IEmployee tmpEmployee = parent.getEmployee();
            	            	            	
            	if(tmpEmployee == null)            		
            		tmpEmployee = (IEmployee) PersonFactory.create("employee", employeeNameTextField.getText());   
            	else
            		tmpEmployee.setName(employeeNameTextField.getText());
            	
            	Object selectedItem = employeePartnerComboBox.getSelectedItem();
            	
            	if(selectedItem instanceof IEmployee) {
            		
            		try {
						tmpEmployee.setPartner((IEmployee) selectedItem);
						
					} catch (Exception e) {
						
						e.printStackTrace();
					}
            	}
            	
            	if(parent.isAddMode())
            		employeesList.add(tmpEmployee);
            	
            	parent.dispose();
            }
        });
		return saveButton;
		
	}
	
	

}
