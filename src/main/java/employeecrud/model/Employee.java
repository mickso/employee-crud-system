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
		
		if(this.hasPartner(partner)) {			
			throw new IllegalArgumentException("Partner ID same as current partner ID");			
		}		
		
		if(this.partner != null) {
			this.partner.clearPartner();
		}
		
		this.partner = partner;
		
		if(!partner.hasPartner(this)) {
			partner.setPartner(this);			
		}	
		
	}	
	
	public void clearPartner() {
		this.partner = null;
	}
	
	public boolean hasPartner(Employee partner) {
		
		return this.partner != null && 
				this.partner.getId() == partner.getId();
	}
	
	public Employee(int id, String name) {
		
		this.id = id;
		this.name = name;		
	}
	
	
}