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
				
	    this.a = new Employee(4, "A");
	    this.b = new Employee(3, "B");
	    this.c = new Employee(2, "C");
	    this.d = new Employee(1, "D");
	    this.e = new Employee(0, "E");	   
	}


    @Test
    void employeeInit() {    
    	assertEquals(4, this.a.getId());
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
    void setPartnerToObjectWithCurrentPartnerId() throws Exception {
    	
		this.a.setPartner(this.b);            		
		Employee ACopy = new Employee(this.a.getId(), this.a.getName());            		
		this.b.setPartner(ACopy);
    	
    	assertEquals(this.b, this.a.getPartner());
    	
    }
    
    @Test 
    void employeeSetPartnerToSelf() {
    	
    	Throwable exception = assertThrows(IllegalArgumentException.class,    			
                ()->{this.a.setPartner(this.a);} );
    	
    	assertEquals("Partner ID same as Employee ID", exception.getMessage());
    }
    
    
    @Test
    void employeePartnerTaken() {
    	
    	
    	try {
    		this.a.setPartner(this.b);
			this.b.setPartner(this.c);
			this.a.setPartner(this.e);
			this.c.setPartner(this.d);
			
			assertEquals(this.e.getId(), this.a.getPartner().getId());
			assertEquals(null, this.b.getPartner());
			assertEquals(this.c.getId(), this.d.getPartner().getId());
			
		} catch (Exception e) {
			
			String failMessage = "Unexpected exception: " + e.getMessage();
			fail(failMessage);
		}    	    	   
    }
}