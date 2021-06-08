package it.rdev.rubrica.model.impl.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.ContactDAO;

public class ContactDAOImpl extends AbstractDAO<Contact, Integer> implements ContactDAO {

	@Override
	public boolean persist(Contact t) throws SQLException {
		System.out.println("Persist su file");
		List<String> contatto = new ArrayList<>();
		
		contatto.add(""+ t.getId());
		contatto.add(""+ t.getName());
		contatto.add(""+ t.getSurname());
		this.executeWriteAppend(contatto);
		
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
	
	protected void executeWriteAppend(List<String> buffer) {
		
		BufferedWriter bw= new BufferedWriter(DataSource.getInstance().getF());
		//System.out.println(buffer.toString());
		
		try {
			//bw.write("Ciao");
		    bw.write(buffer.toString());
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
