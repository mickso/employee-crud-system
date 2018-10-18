package employeecrud.model;

public class Person implements IPerson{
	
	protected int id;
	protected String name;
	
	public int getId() {
		return this.id;		
	}
	
	public String getName() {
		return this.name;
	}
	
	public Person(int id, String name) {
		
		this.id = id;
		this.name = name;		
	}

}
