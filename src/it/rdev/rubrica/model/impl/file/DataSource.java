package it.rdev.rubrica.model.impl.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class DataSource {
	private static DataSource ds;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public static DataSource getInstance() {
		if(ds == null) {
			ds = new DataSource();
		}
			

		return ds;
	}
	
	public BufferedReader getReader() {
		return reader;
	}

	public BufferedWriter getWriter() {
		return writer;
	}
	
	
}
