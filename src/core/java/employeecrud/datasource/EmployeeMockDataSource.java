package employeecrud.datasource;

import employeecrud.factory.PersonFactory;
import employeecrud.model.IEmployee;
import employeecrud.model.IPerson;

public class EmployeeMockDataSource implements IDataSource {

	@Override
	public IPerson[] getData() {

		
		IPerson[] personData =  new IPerson[]{
				PersonFactory.create("employee", "Tom"),
				PersonFactory.create("employee", "Sasha"),
				PersonFactory.create("employee", "Karin"),
				PersonFactory.create("employee", "John"),
				PersonFactory.create("employee", "Lee"),
				PersonFactory.create("employee", "Vincent"),
				PersonFactory.create("employee", "Brendan"),
				PersonFactory.create("employee", "Anna"),
				PersonFactory.create("employee", "Kees")				
		};			
		
		this.makePartners(personData);

		return personData;
	}

	private void makePartners(IPerson[] personData) {
		for (int i = 0; i < personData.length; i++) {

			if (i < (personData.length - 1) && (i % 2 == 0)) {

				try {

					IEmployee currentEmployee = (IEmployee) personData[i];
					IEmployee nextEmployee = (IEmployee) personData[i + 1];
					currentEmployee.setPartner(nextEmployee);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
	}

}
