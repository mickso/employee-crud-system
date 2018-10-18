package employeecrud.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
class EmployeeListTest {
	
	private Employee a,b,c,d,e;
	
	private PersonList employeeList;
	
	@BeforeEach
	public void setUp() {
		
	    this.a = new Employee(4, "A");
	    this.b = new Employee(3, "B");
	    this.c = new Employee(2, "C");
	    this.d = new Employee(1, "D");
	    this.e = new Employee(0, "E");
	    
	    this.employeeList = new PersonList();	   
	}
	
	@Test
	public void addElements() {		
		this.employeeList.add(this.a);
		this.employeeList.add(this.b);
		this.employeeList.add(this.c);
		this.employeeList.add(this.d);
		this.employeeList.add(this.e);
		
	}
	
	@Test
	public void addElementWithExistingID() {
		
    	
    	Throwable exception = assertThrows(IllegalArgumentException.class,    			
                ()->{
                	this.employeeList.add(this.a);
            		this.employeeList.add(this.a);
            		} );
    	
    	assertEquals("There is already a Person with the same ID in this list", exception.getMessage());
				
	}
	
	
	
	@Test
	public void getEmployeeWithNonExistingId() {
					
		IEmployee match = (Employee) this.employeeList.getPersonById(0);
		
    	
    	assertEquals(null, match);
		

				
	}
	
	@Test
	public void sortEmployeesByName() {
		
		this.employeeList.add(this.b);
		this.employeeList.add(this.e);
		this.employeeList.add(this.d);
		this.employeeList.add(this.a);
		this.employeeList.add(this.c);
		
		this.employeeList.sortByName();
		
		PersonList resultList = new PersonList();
		
		resultList.add(this.a);
		resultList.add(this.b);
		resultList.add(this.c);
		resultList.add(this.d);
		resultList.add(this.e);
		
		
		assertEquals(resultList, this.employeeList);
	}
	
	@Test
	public void sortEmployeesById() {
		
		this.employeeList.add(this.b);
		this.employeeList.add(this.e);
		this.employeeList.add(this.d);
		this.employeeList.add(this.a);
		this.employeeList.add(this.c);
		
		this.employeeList.sortById();
		
		PersonList resultList = new PersonList();		

		resultList.add(this.e);
		resultList.add(this.d);
		resultList.add(this.c);
		resultList.add(this.b);
		resultList.add(this.a);
		
		assertEquals(resultList, this.employeeList);
	}
	
	@Test
	public void removeEmployeeById() {
		
		this.employeeList.add(this.a);
		
		assertEquals(1, this.employeeList.size());
		
		this.employeeList.removeById(4);
		assertEquals(0, this.employeeList.size());
		
	}
	
	@Test
	public void updateEmployeeWithDuplicateId() {
		
		this.employeeList.add(this.a);
		this.employeeList.add(this.b);
		
		Employee tmp = new Employee(4 , "Henk");
			
		
    	Throwable exception = assertThrows(IllegalArgumentException.class,    			
                ()->{
                	this.employeeList.set(1, tmp);
            		} );			
	}
	
	
	@Test
	public void updateEmployeeById() {
		
		this.employeeList.add(this.a);
		this.employeeList.add(this.b);
		
		Employee tmp = new Employee(4 , "Henk");
		
		this.employeeList.setByPersonId(tmp);
		
		
		
		IPerson tmpTwo = this.employeeList.getPersonById(4);
		assertEquals("Henk", tmpTwo.getName());
	}
}