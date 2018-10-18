package employeecrud.ui.model;

import javax.swing.table.AbstractTableModel;

import employeecrud.model.IEmployee;
import employeecrud.model.PersonList;

@SuppressWarnings("serial")
public class EmployeeTableModel extends AbstractTableModel {

	private PersonList personList;
	private String[] columns;
	private final Class<?>[] columnsClasses;

	public EmployeeTableModel(PersonList personList) {
		super();

		this.personList = personList;
		this.columns = new String[] { "ID", "Name", "Partner" };

		this.columnsClasses = new Class[] { Integer.class, String.class, IEmployee.class };			

	}

	// Number of column of your table
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnsClasses[columnIndex];
	}

	
	// The object to render in a cell
	public Object getValueAt(int row, int col) {
		IEmployee employee = (IEmployee) this.personList.get(row);
		switch (col) {
		case 0:
			return employee.getId();
		case 1:
			return employee.getName();
		case 2:
			if(employee.getPartner() != null) {
				IEmployee partner = employee.getPartner();
				return partner.getAlias();		
			}			
		default:
			return null;
		}

	}
	
	@Override
	   public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	   {
		IEmployee employee = (IEmployee) this.personList.get(rowIndex);	       
	       if(1 == columnIndex) { 
	    	   employee.setName((String) aValue);
	       }	       
	   }
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
        return columnIndex > 0;
	}

	// Optional, the name of your column
	public String getColumnName(int col) {
		return columns[col];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.personList.size();
	}

}
