package employeecrud.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@SuppressWarnings("serial")
public class EmployeeList extends ArrayList<Employee>{

	@Override
	public boolean add(Employee e) {
		
		this.validateEntry(e);
		
		return super.add(e);
	}

	@Override
	public void add(int index, Employee element) {		// 
		this.validateEntry(element);
		super.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends Employee> c) {
		// TODO Auto-generated method stub				
		return super.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Employee> c) {
		// TODO Auto-generated method stub
		return super.addAll(index, c);
	}
	
	public Employee getEmployeeById(int id) {
		return this.findEmployeeById(id);
	}
	
	public void sortById() {
		
		this.sort(new Comparator<Employee>(){
		   @Override
		   public int compare(final Employee lhs, Employee rhs) {
			   
			   Integer lhsId = lhs.getId();
			   Integer rhsId = rhs.getId();
			   
			   return lhsId.compareTo(rhsId);
			   
		   }
		});
	}
	
	public void sortByName() {
		
		this.sort(new Comparator<Employee>(){
			   @Override
			   public int compare(final Employee lhs, Employee rhs) {
				   
				   return lhs.getName().compareTo(rhs.getName());
			   }
			});
		
	}
	
	private void validateEntry(Employee e) {
		
		if(this.employeeWithIdExists(e.getId()))			
			throw new IllegalArgumentException("There is already an employee with the same ID in this list");			
		
	}
	
	private boolean employeeWithIdExists(int id) {
		
		Employee match = this.findEmployeeById(id);
		
		return (match != null);
		
	}
	
	private Employee findEmployeeById(int id) {
		
		List<Employee> matches = this.stream()
			.filter(p -> p.getId() == id)
			.collect(Collectors.toList());
		
		if(matches.size() > 0) {
			return matches.get(0);
		}
		
		return null;
		
	}
	
	
	
	

}
