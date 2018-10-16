package employeecrud.model;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
class EmployeeTest {
	
	private Employee a,b,c,d,e;
	
	@BeforeEach
	public void setUp() {
		
	    this.a = new Employee(1, "A");
	    this.b = new Employee(2, "B");
	    this.c = new Employee(3, "C");
	    this.d = new Employee(4, "D");
	    this.e = new Employee(5, "E");
	    
	}


    @Test
    void employeeInit() {    
    	assertEquals(1, this.a.getId());
    	assertEquals("A", this.a.getName());
    }
    
    @Test
    void employeeSetPartner() {
    	try {
			this.a.setPartner(this.b);
			assertEquals(this.b, this.a.getPartner());
			
		} catch (Exception e) {
						
			String failMessage = "Unexpected exception: " + e.getMessage();
			fail(failMessage);
		}
    }
    
    @Test 
    void employeeSetPartnerToSelfIllegalArgumentException() {
    	
    	Throwable exception = assertThrows(IllegalArgumentException.class,    			
                ()->{this.a.setPartner(this.a);} );
    	
    	assertEquals("Partner ID same as Employee ID", exception.getMessage());
    }
}