package it.rdev.rubrica.controller;

import java.sql.SQLException;
import java.util.List;

import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.ContactDAO;
//import it.rdev.rubrica.model.impl.rdbms.ContactDAOImpl;
import it.rdev.rubrica.model.impl.DaoManager;

public class RubricaController {
	
	private ContactDAO dao;
	
	public RubricaController() {
		dao = DaoManager.createContactDAO();
	}

	public List<Contact> getContactList() {
		return dao.getAll();
	
	}
	
	public String addContact(Contact c) {
		String view = "LIST";
		// Controlli ore lavorate
		// Controlli anagrafica
		// altri controlli
		try {
			dao.persist(c);
		} catch (SQLException e) {
			e.printStackTrace();
			view = "LIST-ERROR";
		}
		return view;
	}
	
	public String UpdateContact(Contact c) {
		String view = "LIST";
		
		try {
			dao.update(c);
		} catch (SQLException e) {
			e.printStackTrace();
			view = "LIST-ERROR";
		}
		return view;
	}
	
	public boolean removeContact(Contact c) {
		try {
			return dao.delete(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
