package employeecrud.model;

public class Employee{
	
	private int id;
	private String name;
	private Employee partner;
	
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Employee getPartner() {
		return this.partner;
	}
	
	public void setPartner(Employee partner) throws Exception {
		
		if(this.id == partner.getId()) {			
			throw new IllegalArgumentException("Partner ID same as Employee ID");			
		}
		
		this.partner = partner;
		
	}	
	
	public Employee(int id, String name) {
		
		this.id = id;
		this.name = name;
		
	}

}
