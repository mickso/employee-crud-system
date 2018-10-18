package employeecrud.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import employeecrud.model.Employee;
import employeecrud.model.IPerson;
import employeecrud.model.Person;
 
class PersonFactoryTest {	
	
	
	@Test
	public void createEmployees() {
		
		IPerson personA = PersonFactory.create("employee" ,"Bob");
		IPerson personB = PersonFactory.create("employee", "Puk");
		IPerson personC = PersonFactory.create("employee", "Karel");
		
		
		assertTrue((personA instanceof Employee));
		
		assertEquals(0, personA.getId());
		assertEquals(1, personB.getId());
		assertEquals(2, personC.getId());
		
		personC = null;
		
		IPerson personD = PersonFactory.create("employee", "Karel Junior");
		
		assertEquals(3, personD.getId());
	}
	
	@Test
	public void createPeople() {
		
		
		IPerson personA = PersonFactory.create("Person", "Bob");
		IPerson personB = PersonFactory.create("Person", "Puk");
		IPerson personC = PersonFactory.create("Person", "Karel");
		
				
				
		assertTrue((personA instanceof Person));
			
		
		assertEquals(0, personA.getId());
		
		
		assertEquals(1, personB.getId());
		assertEquals("Puk", personB.getName());
		
		
		assertEquals(2, personC.getId());
		
		personC = null;
		
		IPerson personD = PersonFactory.create("Person", "Karel Junior");
		
		assertEquals(3, personD.getId());
		
	}
	
	@AfterEach
	public void cleanUp() {
		PersonFactory.resetIncrementedId();
	}
	
	
}