package it.rdev.rubrica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import it.rdev.rubrica.controller.RubricaController;
import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.ui.AppFrame;

public class RubricaApp {

	public static void main(String[] strings) {
		//new AppFrame().setVisible(true);
		
		
		RubricaController con = new RubricaController();
		Contact c = new Contact()	
									.setId(10)
									.setName("Giuseppe")
									.setSurname("Rossi")
		
									.addEmails("prova@gmail.com")
									.addPhoneNumbers("3337777");
		
		//Scrittura su FILE o su DB (settare prima il "persistence.type" in rubrica.cfg)
		con.addContact(c);

		//Lettura da DB (settare prima il "persistence.type" in rubrica.cfg)
		System.out.println("Lista: "+ con.getContactList());
		
		
	}
	
}
