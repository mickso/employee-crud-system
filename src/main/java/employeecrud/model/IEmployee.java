package employeecrud.model;

public interface IEmployee extends IPerson {
	
	public Employee getPartner();	
	
	public void setPartner(Employee partner) throws Exception;
	
	public void clearPartner();	
	
	public boolean hasPartner(Employee partner);

}
