package org.generation.italy.newEnteSportivo2.model_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.generation.italy.newEnteSportivo2.model_jdbc.dao.GaraDao;
import org.generation.italy.newEnteSportivo2.model_jdbc.dao.IscrizioneDao;
import org.generation.italy.newEnteSportivo2.model_jdbc.dao.PartecipazioneDao;
import org.generation.italy.newEnteSportivo2.model_jdbc.dao.Trigger;
import org.generation.italy.newEnteSportivo2.model_jdbc.dao.VelocistaDao;




public class TestJdbcEnteSportivo {

	Connection dbConnection;
	GaraDao garaDao;
	VelocistaDao velocistaDao;
	IscrizioneDao iscrizioneDao;
	PartecipazioneDao partecipazioneDao;
	
	
	public TestJdbcEnteSportivo() throws EnteSportivoModelException {
		

		this.dbConnection = JdbcConnection.readJdbcConnectionInstance("mariadb", "org.mariadb.jdbc.Driver", "localhost",
				"3306", "ente_sportivo", "root", "").getDbConnection();

		this.garaDao = new GaraDao(this.dbConnection);
		this.velocistaDao = new VelocistaDao(this.dbConnection);
		this.iscrizioneDao = new IscrizioneDao(this.dbConnection);
		this.partecipazioneDao = new PartecipazioneDao(this.dbConnection);

		// richiamare trigger
		
      Trigger.garaDao = this.garaDao;
      Trigger.velocistaDao= this.velocistaDao;
      Trigger.iscrizioneDao = this.iscrizioneDao;
      Trigger.partecipazioneDao= this.partecipazioneDao;
		

	}



	private void clearDatabase() throws EnteSportivoModelException {

		try {

			// DELETE FROM movimento
			PreparedStatement preparedStatement1 = this.dbConnection.prepareStatement(" DELETE FROM movimento ");
			preparedStatement1.executeUpdate();

			// DELETE FROM conto
			PreparedStatement preparedStatement2 = this.dbConnection.prepareStatement(" DELETE FROM conto ");
			preparedStatement2.executeUpdate();

			// DELETE FROM cliente
			PreparedStatement preparedStatement3 = this.dbConnection.prepareStatement(" DELETE FROM cliente ");
			preparedStatement3.executeUpdate();

		} catch (SQLException sqlException) {
			throw new EnteSportivoModelException("TestJdbcBanca -> clearDatabase -> " + sqlException.getMessage());
		}

	}


	public static void main(String[] args) throws EnteSportivoModelException { 
	}



	public GaraDao getGaraDao() {
		return garaDao;
	}



	public VelocistaDao getVelocistaDao() {
		return velocistaDao;
	}



	public IscrizioneDao getIscrizioneDao() {
		return iscrizioneDao;
	}



	public PartecipazioneDao getPartecipazioneDao() {
		return partecipazioneDao;
	}	
	
}
