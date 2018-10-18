package employeecrud.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import employeecrud.datasource.EmployeeMockDataSource;
import employeecrud.datasource.IDataSource;
import employeecrud.model.IEmployee;
import employeecrud.model.IPerson;
import employeecrud.model.PersonList;
import employeecrud.ui.model.EmployeeTableModel;
import employeecrud.ui.window.EmployeeDialog;

@SuppressWarnings("serial")
public class EmployeeCrudUi extends JFrame {	
	
	JFrame frame;
	IDataSource dataSource;
	PersonList employeeList;
	
       	
	
	public static void main(String[] args) {		

		EmployeeCrudUi main = new EmployeeCrudUi();
		main.setDataSource(new EmployeeMockDataSource());
		main.initEmployeeList();
		main.makeFrame();			
		
	}
	
	private void setDataSource(IDataSource dataSource) {
		
		this.dataSource = dataSource;
		
	}
	
	
	private void initEmployeeList() {
		
		
		this.employeeList = new PersonList();
		IPerson[] employeeData = this.dataSource.getData();				
		Collections.addAll(this.employeeList, employeeData);				
		
	}
	
	
	private void makeFrame(){			
		
		
		
		this.setLayout(new BorderLayout());				
		
        EmployeeTableModel model = new EmployeeTableModel(this.employeeList);
        JTable table = new JTable(model);               
        
        addSortingToHeaders(this.employeeList, table);       
        
        JButton deleteButton = createDeleteButton(this.employeeList, table);
        JButton addButton = createAddButton(this.employeeList, table);
        JButton editButton = createEditButton(this.employeeList, table);
        
        JPanel dataPanel = new JPanel();
        dataPanel.add(new JScrollPane(table));
        
        JToolBar toolBar = new JToolBar("Toolbar");
        toolBar.add(addButton);
        toolBar.add(editButton);
        toolBar.add(deleteButton);
        
        this.add(dataPanel, BorderLayout.CENTER);
        this.add(toolBar, BorderLayout.NORTH);
                       
         
        this.setTitle("Employee CRUD System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.pack();
        this.setVisible(true);

	 }

	private void addSortingToHeaders(PersonList employeeList, JTable table) {
		
		table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = table.columnAtPoint(e.getPoint());
                
                switch(col) {
	                case 0:
	                	employeeList.sortById();
	                	break;
	                case 1:
	                	employeeList.sortByName();
	                	break;
                }                
            }
        });
	}

	private JButton createDeleteButton(PersonList employeeList, JTable table) {
		JButton deleteButton = new JButton("delete selected");
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
            	
            	
                if (table.getSelectedRow() != -1) {
                	
                	int employeeId = (int) table.getValueAt(table.getSelectedRow(), 0); 
                	IEmployee employeeToRemove = (IEmployee) employeeList.getPersonById(employeeId);
                	
                	employeeToRemove.clearPartner();
                	
                    employeeList.remove(employeeToRemove);
                                        
                    table.revalidate();
                }
            }
        });
		return deleteButton;
	}
	
	private JButton createAddButton(PersonList employeeList, JTable table) {
		JButton addButton = new JButton("add employee");
		addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
            	
            	EmployeeDialog employeeWindow = new EmployeeDialog(employeeList);
            	
            	employeeWindow.pack();
            	employeeWindow.setVisible(true);
            	table.revalidate();
            	table.repaint();
            	
            }
        });
		return addButton;
	}
	
	private JButton createEditButton(PersonList employeeList, JTable table) {
		JButton addButton = new JButton("edit selected employee");
		addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
            	
            	int employeeId = (int) table.getValueAt(table.getSelectedRow(), 0); 
            	IEmployee employeeToEdit = (IEmployee) employeeList.getPersonById(employeeId);
            	
            	EmployeeDialog employeeWindow = new EmployeeDialog(employeeList);
            	employeeWindow.setEmployee(employeeToEdit);
            	
            	employeeWindow.pack();            	            	
            	employeeWindow.setVisible(true);
            	table.revalidate();
            	table.repaint();
            	
            }
        });
		return addButton;
	}



}