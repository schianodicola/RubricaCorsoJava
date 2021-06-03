package it.rdev.rubrica;

import it.rdev.rubrica.controller.RubricaController;
import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.ui.AppFrame;

public class RubricaApp {

	public static void main(String[] strings) {
		new AppFrame().setVisible(true);
		
		/*
		RubricaController con = new RubricaController();
		Contact c = new Contact()	.setName("Salvio")
									.setSurname("Scotto")
		
									.addEmails("prova@gmail.com")
									.addPhoneNumbers("3337777");
		
		con.addContact(c);
		System.out.println(c.getId());
		System.out.println(con.getContactList());
		*/
	}
	
}
