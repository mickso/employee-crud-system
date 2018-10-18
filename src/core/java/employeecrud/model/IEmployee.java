package employeecrud.model;

public interface IEmployee extends IPerson {

	public IEmployee getPartner();

	public void setPartner(IEmployee partner) throws IllegalArgumentException;

	public void clearPartner();

	public boolean hasPartner(IEmployee partner);

}
