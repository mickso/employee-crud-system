package employeecrud.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import employeecrud.constants.ExceptionMessageConstants;

@SuppressWarnings("serial")
public class PersonList extends ArrayList<IPerson> {

	@Override
	public boolean add(IPerson e) {

		this.validateEntry(e);

		return super.add(e);
	}

	@Override
	public void add(int index, IPerson element) { //
		this.validateEntry(element);
		super.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends IPerson> c) {
		// TODO Auto-generated method stub
		return super.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends IPerson> c) {
		// TODO Auto-generated method stub
		return super.addAll(index, c);
	}

	@Override
	public IPerson set(int index, IPerson element) {

		IPerson currentElement = this.get(index);

		if (element.getId() == currentElement.getId() || !this.PersonWithIdExists(element.getId())) {

			return super.set(index, element);
		} else {

			throw new IllegalArgumentException(ExceptionMessageConstants.ID_EXISTS_IN_PERSON_LIST);

		}
	}

	public IPerson setByPersonId(IPerson IPerson) {

		int index = this.indexOf(IPerson);

		return this.set(index, IPerson);

	}

	public void removeById(int id) {

		this.remove(this.findPersonById(id));

	}

	public IPerson getPersonById(int id) {
		return this.findPersonById(id);
	}

	public void sortById() {

		this.sort(new Comparator<IPerson>() {
			@Override
			public int compare(final IPerson lhs, IPerson rhs) {

				Integer lhsId = lhs.getId();
				Integer rhsId = rhs.getId();

				return lhsId.compareTo(rhsId);

			}
		});
	}

	public void sortByName() {

		this.sort(new Comparator<IPerson>() {
			@Override
			public int compare(final IPerson lhs, IPerson rhs) {

				return lhs.getName().compareTo(rhs.getName());
			}
		});

	}

	private void validateEntry(IPerson e) {

		if (this.PersonWithIdExists(e.getId()))
			throw new IllegalArgumentException(ExceptionMessageConstants.ID_EXISTS_IN_PERSON_LIST);

	}

	private boolean PersonWithIdExists(int id) {

		IPerson match = this.findPersonById(id);

		return (match != null);

	}

	private IPerson findPersonById(int id) {

		List<IPerson> matches = this.stream().filter(p -> p.getId() == id).collect(Collectors.toList());

		if (matches.size() > 0) {
			return matches.get(0);
		}

		return null;
	}

}
