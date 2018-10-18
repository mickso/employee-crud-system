package employeecrud.ui.combobox;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import employeecrud.model.IEmployee;

@SuppressWarnings("serial")
public class EmployeeRenderer extends BasicComboBoxRenderer {
	
  public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {
    super.getListCellRendererComponent(list, value, index, isSelected,
        cellHasFocus);
    
    if(value instanceof IEmployee) {
    	
    	IEmployee employee = (IEmployee) value;
        setText(employee.getAlias());
    	
    } else {
    	setText((String) value);
    }        


    return this;
  }
}