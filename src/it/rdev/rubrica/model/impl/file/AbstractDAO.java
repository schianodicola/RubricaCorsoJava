package it.rdev.rubrica.model.impl.file;

import it.rdev.rubrica.model.DAO;

abstract class AbstractDAO<T, D> implements DAO<T> {
	
	protected void executeInsert() {}
	
	protected void executeUpdate() {}
	
}