package employeecrud.model;

import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractEmployeeTest {
	
	protected Employee a, b, c, d, e;

	@BeforeEach
	protected void setUpEmployees() {

		this.a = new Employee(4, "A");
		this.b = new Employee(3, "B");
		this.c = new Employee(2, "C");
		this.d = new Employee(1, "D");
		this.e = new Employee(0, "E");
	}

}
