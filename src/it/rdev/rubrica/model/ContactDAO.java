package it.rdev.rubrica.model;

import java.util.List;

public interface ContactDAO extends DAO<Contact> {

	public List<Contact> getAll();
	//public boolean persist(Contact t);
	
}
