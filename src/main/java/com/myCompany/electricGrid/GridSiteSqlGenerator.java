package com.myCompany.electricGrid;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.myCompany.model.GridSite;
import com.myCompany.model.Parameters;

public class GridSiteSqlGenerator {
	private static final Logger LOGGER = LogManager.getLogger(GridSiteSqlGenerator.class);
	
	public String createGridTableQuery() {
		LOGGER.info("Create Grid Table");
		String gridCreateQuery = "CREATE TABLE gridSite (\r\n"
				+ "	gridId INT AUTO_INCREMENT PRIMARY KEY,\r\n"
				+ "	uniqueID INT,\r\n"
				+ "    NAME VARCHAR(500),\r\n"
				+ "    alarmColor VARCHAR(500),\r\n"
				+ "    id INT,\r\n"
				+ "	datasourcesCount INT,\r\n"
				+ "	_alertIcon VARCHAR(500),\r\n"
				+ "	elementCount INT\r\n"
				+ "	\r\n"
				+ ");";
		
		return gridCreateQuery;
	}
	
	public String createParameterTableQuery() {
		LOGGER.info("Create parameters table");
		String parametersCreateQuery = "CREATE TABLE parameters(\r\n"
				+ "	parametersId INT AUTO_INCREMENT PRIMARY KEY,\r\n"
				+ "	_key  TEXT,\r\n"
				+ "	_values TEXT,\r\n"
				+ "	gridId INT,\r\n"
				+ "	FOREIGN KEY (gridId) \r\n"
				+ "    REFERENCES gridSite(gridId)\r\n"
				+ "	);";
		
		return parametersCreateQuery;
	}
	
	public String insertGridJsonArrayToMySql(GridSite grideSite) {
		LOGGER.info("creating insert queries to insert values into Grid table");
		String insertGridQuery = "INSERT INTO gridSite (uniqueID,name,alarmColor,id,datasourcesCount,_alertIcon,elementCount)\r\n"
				+ "VALUES ('" + grideSite.uniqueID + "',' " + grideSite.name + "', "+ grideSite.alarmColor + "," + grideSite.id + ","+ grideSite.datasourcesCount + ",'"  + grideSite._alertIcon+"'," +grideSite.elementCount +");";
	
		return insertGridQuery;
	}
	
	public List<String> insertParametersJsonArrayToMySql(GridSite grideSite) {
		LOGGER.info("creating insert queries to insert values into Parameters table");
		List<String> parametersQueries = new ArrayList<String>();
		String insertParametersQuery;
		for(Parameters parameters : grideSite.parameters) {
			insertParametersQuery = "INSERT INTO parameters (_key, _value,gridId)\r\n"
					+ "     VALUES ( '"+parameters.key+"','"+parameters.value+"',"+"(SELECT gridId FROM gridSite WHERE uniqueID='"+grideSite.uniqueID+"'));"; 
			parametersQueries.add(insertParametersQuery);
		}
		return parametersQueries;
	}


}
