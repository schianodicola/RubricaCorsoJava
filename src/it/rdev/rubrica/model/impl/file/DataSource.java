package it.rdev.rubrica.model.impl.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import it.rdev.rubrica.config.ConfigKeys;
import it.rdev.rubrica.config.Configuration;

public class DataSource {
	private static DataSource ds;
	private BufferedReader reader;
	private BufferedWriter writer;
	private FileWriter f;
	
	
	public FileWriter getF() {
		return f;
	}

	public void setF(FileWriter f) {
		this.f = f;
	}

	public static DataSource getInstance() {
		if(ds == null) {
			try {
				ds = new DataSource();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("File non esistente");
			}
			
		}
			

		return ds;
	}
	
	public BufferedReader getReader() {
		return reader;
	}

	public BufferedWriter getWriter() {
		return writer;
	}
	
	private DataSource() throws FileNotFoundException{
		File rubrica = new File(Configuration.getInstance().getValue(ConfigKeys.FILE_PATH));
		try {
			f=new FileWriter(rubrica);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(rubrica.exists()) {
			try {
				reader = Files.newBufferedReader(rubrica.toPath(), Charset.forName("UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				rubrica.createNewFile();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	//chiusura file
	@Override
	protected void finalize() {
		if( reader != null ) {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		if( writer != null ) {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
