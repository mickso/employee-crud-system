package employeecrud.model;

import employeecrud.constants.ExceptionMessageConstants;

public class Employee extends Person implements IEmployee {

	private IEmployee partner;

	public IEmployee getPartner() {
		return this.partner;
	}

	public void setPartner(IEmployee partner) throws IllegalArgumentException {

		if (this.id == partner.getId()) {
			throw new IllegalArgumentException(ExceptionMessageConstants.PARTNER_HAS_EMPLOYEE_ID);
		}

		if (this.hasPartner(partner))
			return;

		if (this.partner != null) {
			this.partner.clearPartner();
		}

		this.partner = partner;

		if (!partner.hasPartner(this)) {
			partner.setPartner(this);
		}

	}

	public void clearPartner() {

		if (this.partner != null) {

			IEmployee tmpPartner = this.partner;
			this.partner = null;
			tmpPartner.clearPartner();
		}

	}

	public boolean hasPartner(IEmployee partner) {

		return this.partner != null && this.partner.getId() == partner.getId();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof IEmployee) {

			Employee comparingObj = (Employee) obj;

			if (this.id == comparingObj.getId()) {
				return true;
			}

		}

		return false;

	}

	public Employee(int id, String name) {
		super(id, name);
	}

}