package employeecrud.model;

public class Employee extends Person implements IEmployee{

	private Employee partner;
	
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

    @Override
    public boolean equals(Object obj) {
    	
    	Employee comparingObj = (Employee)obj;
    	
    	if (this.id == comparingObj.getId()) {
    		return true;
    	}
    	
    	return false;
    	
    }   
    
	public Employee(int id, String name) {
		super(id, name);
	}
	
}