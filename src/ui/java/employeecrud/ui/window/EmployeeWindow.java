package employeecrud.ui.window;

import java.awt.Panel;

import javax.swing.JFrame;

import employeecrud.model.IEmployee;

public class EmployeeWindow extends JFrame{
	
	private IEmployee employee;
	
	
	public EmployeeWindow(IEmployee employee) {
		
		super();
		this.employee = employee;					
	}

	@Override
	protected void frameInit() {
		
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add (new Panel());
        this.pack();
        this.setVisible (true);
		super.frameInit();
	}
	
	

}
