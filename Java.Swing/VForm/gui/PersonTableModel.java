package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import data.Person;

public class PersonTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -644898091938774026L;

	private List<Person> data;
	
	private String[] colNames = {"ID", "Name", "Occupation", "Age Category", "Employment", "Polish Citizen", "Document ID", "Gender", "Phone Number"};
	
	public PersonTableModel(){
	}
	
	public void setData(List<Person> data) {
		this.data = data;
	}
	
	@Override
	public int getColumnCount() {
		return 9;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Person person = data.get(row);
		
		switch(col) {
		case 0: {
			return person.getId();
		}
		case 1: {
			return person.getName();
		}
		case 2: {
			return person.getOccupation();
		}
		case 3: {
			return person.getAge();
		}
		case 4: {
			return person.getEmployment();
		}
		case 5: {
			return person.isPLCitizen();
		}
		case 6: {
			return person.getDocID();
		}
		case 7: {
			return person.getGender();
		}
		case 8: {
			return person.getPhoneNumber();
		}
		}
		
		return null;
	}

}
