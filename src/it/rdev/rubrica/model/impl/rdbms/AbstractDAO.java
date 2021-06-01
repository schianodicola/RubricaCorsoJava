package it.rdev.rubrica.model.impl.rdbms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.rdev.rubrica.model.DAO;

/**
 * 
 * Classe astratta che contiene la logica comune per le operazioni di CRUD
 * 
 * @author Danilo Di Nuzzo
 *
 * @param <T> tipo concreto su cui effettuare le operazioni di CRUD
 */
public abstract class AbstractDAO<T> implements DAO<T> {
	
	protected ResultSet executeQuery(String query) throws SQLException {
		return DataSource.getInstance().getConnection().createStatement().executeQuery(query);
	}
	
	protected ResultSet executeQuery(String query, Object ... params) throws SQLException {
		PreparedStatement ps = composePreparedStatement(query, params);
		return ps.executeQuery();
	}
	
	protected Long executeInsert(String query, Object ... params) throws SQLException {
		PreparedStatement ps = composePreparedStatement(query, params);
		int row = ps.executeUpdate();
		if( row == 0 ) {
			throw new SQLException("Nessuna riga inserita");
		}
		Long generatedId = null;
		try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
            	generatedId = generatedKeys.getLong(1);
            } else {
            	throw new SQLException("Errore nell'inserimento, nessun id restituito");
            }
        }
		return generatedId;
	}
	
	protected Integer executeUpdate(String query, Object ... params) throws SQLException {
		return composePreparedStatement(query, params).executeUpdate();
	}
	
	private PreparedStatement composePreparedStatement(String query, Object ... params) throws SQLException {
		Connection conn = DataSource.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		for(int i = 1; i <= params.length; i++) {
			ps.setObject(i, params[i-1]);
		}
		return ps;
	}

}
