package it.rdev.rubrica.model.impl.file;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.ContactDAO;

public class ContactDAOImpl extends AbstractDAO<Contact, Integer> implements ContactDAO {

	@Override
	public boolean persist(Contact t) throws SQLException {
		System.out.println("Persist su file");
		return false;
	}

	@Override
	public boolean delete(Contact t) throws SQLException {
		System.out.println("delete su file");
		return false;
	}

	@Override
	public boolean update(Contact t) throws SQLException {
		System.out.println("update su file");
		return false;
	}

	@Override
	public List<Contact> getAll() {
		System.out.println("getAll su file");
		return new ArrayList<>();
	}

}
