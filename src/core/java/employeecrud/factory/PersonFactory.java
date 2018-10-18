package employeecrud.factory;

import employeecrud.model.Employee;
import employeecrud.model.IPerson;
import employeecrud.model.Person;

public class PersonFactory {

	private static int incrementedId = 0;

	public static IPerson create(String type, String name) {

		IPerson object;
		if (type == "employee") {
			object = new Employee(incrementedId, name);
		} else {
			object = new Person(incrementedId, name);
		}

		incrementedId++;

		return object;
	}

	public static void resetIncrementedId() {
		incrementedId = 0;
	}

}
