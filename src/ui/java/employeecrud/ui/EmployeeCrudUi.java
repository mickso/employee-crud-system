package employeecrud.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
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
        
        JPanel dataPanel = new JPanel();
        dataPanel.add(new JScrollPane(table));
        
        JToolBar toolBar = new JToolBar("Toolbar");
        toolBar.add(deleteButton);
        
        this.add(dataPanel, BorderLayout.SOUTH);
        this.add(toolBar, BorderLayout.CENTER);        
                       
         
        this.setTitle("Editable Table Example");
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
		JButton deleteButton = new JButton("delete");
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



}