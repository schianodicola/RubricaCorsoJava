package it.rdev.rubrica.model.impl.rdbms;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.ContactDAO;

public class ContactDAOImpl extends AbstractDAO<Contact, BigInteger> implements ContactDAO {
	
	private final String TABLE_NAME = "contacts";
	private final String TABLE_MAIL = "mail";
	private final String TABLE_PHONE = "phone";

	public List<Contact> getAll() {
		List<Contact> contacts = new ArrayList<>();
		try {
			
			ResultSet rs = this.executeQuery("SELECT c.id, c.name, c.surname, m.email, p.number FROM contacts c inner join mail m on c.id=m.id_contacts inner join phone p on c.id=p.id_contacts;");
			
			List<String> emails=new ArrayList();
			List<String> phoneNumbers=new ArrayList();
			while(rs.next()) {
					Contact c= new Contact();
					c.setId(rs.getInt("id"));
					
					if(!contacts.contains(c)) {
						c.setName(rs.getString("name"));
						c.setSurname(rs.getString("surname"));
						contacts.add(c);
					}
					if(rs.getString("email") != null) c.addEmails(rs.getString("email"));
					if(rs.getString("number") != null) c.addPhoneNumbers(rs.getString("number"));
					
					
			}
			
			//contacts.add(c);
			

			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contacts;
	}

	@Override
	public boolean persist(Contact o) throws SQLException {
		
		BigInteger id= this.executeInsert("INSERT INTO contacts (name,surname) VALUES(?, ?) ", o.getName(), o.getSurname() );
		
		
		insertEmailAndPhone(o, id.intValue());
		
		return id != null;

	}

	@Override
	public boolean delete(Contact t) throws SQLException {

		try {
			this.executeUpdate("DELETE FROM contacts WHERE id=?", t.getId());
		}catch(SQLException e) {
			System.err.println("ERRORE - Delete");
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Contact t) throws SQLException {
		this.executeUpdate("DELETE FROM emails WHERE id_contact = ?", t.getId());
		this.executeUpdate("DELETE FROM phones WHERE id_contact = ?", t.getId());
		
		insertEmailAndPhone(t, t.getId());
		return true;
	}
	
	private void insertEmailAndPhone(Contact c, Integer id) throws SQLException {
		// numero di insert ottimizzato
		
		if( c.getEmails() != null ) {
			Object[] emailsParam = new Object[c.getEmails().size() * 2];
			Iterator<String> emailIt = c.getEmails().iterator();
			StringBuffer sb = new StringBuffer("INSERT INTO emails VALUES ");
			for(int i = 0; i<emailsParam.length && emailIt.hasNext();) {
				if( i > 0) {
					sb.append(", ");
				}
				sb.append("(?, ?)");
				emailsParam[i++] = emailIt.next();
				emailsParam[i++] = id;
			}
			this.executeUpdate(sb.toString(), emailsParam);
		}
		
		if( c.getPhoneNumbers() != null ) {
			Object[] phonesParam = new Object[c.getPhoneNumbers().size() * 2];
			Iterator<String> phoneIt = c.getPhoneNumbers().iterator();
			StringBuffer sb = new StringBuffer("INSERT INTO phones VALUES ");
			for(int i = 0; i<phonesParam.length && phoneIt.hasNext();) {
				if( i > 0) {
					sb.append(", ");
				}
				sb.append("(?, ?)");
				phonesParam[i++] = phoneIt.next();
				phonesParam[i++] = id;
			}
			this.executeUpdate(sb.toString(), phonesParam);
		}
	}

}
