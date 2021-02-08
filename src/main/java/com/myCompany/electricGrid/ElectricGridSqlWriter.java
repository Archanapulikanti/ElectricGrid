package com.myCompany.electricGrid;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.myCompany.model.GridSite;

public class ElectricGridSqlWriter {
	GridSiteSqlGenerator gridSiteSqlGenerator = new GridSiteSqlGenerator();
	
	private static final Logger LOGGER = LogManager.getLogger(ElectricGridSqlWriter.class);
	
	private String createGridTableQuery;
	private String createParameterTableQuery;
	private List<String> insertStatementForGrid = new ArrayList<String>();
	private List<String> insertStatementForParameters = new ArrayList<String>();
	
	public void writeSql(GridSite[] gridSiteArray,String outputMySqlFileName) {
		LOGGER.info("Writing SQL");
		createSql(gridSiteArray);
		if(outputMySqlFileName != null) {
			try {
				PrintWriter out = new PrintWriter("C://Users//kiran.govind//Downloads/" + outputMySqlFileName + ".txt");
				out.println(createGridTableQuery);
				out.println(createParameterTableQuery);
				out.println(insertStatementForGrid);
				for(String parameterInsertStatments : insertStatementForParameters ) {
					out.println(parameterInsertStatments);
				}
				out.close();
			} catch (FileNotFoundException e) {
				LOGGER.error("Unable to create file",e);
				e.printStackTrace();
			}

		}else {
			for(String parameterInsertStatments : insertStatementForParameters ) {
				System.out.println(parameterInsertStatments);
			}
		}
	}

	private void createSql(GridSite[] gridSiteArray) {
		createGridTableQuery = gridSiteSqlGenerator.createGridTableQuery();
		createParameterTableQuery = gridSiteSqlGenerator.createParameterTableQuery();
		
		String insertGridStatement = null;
		List<String> insertParametersStatement = new ArrayList<String>();
		for (GridSite gridSite : gridSiteArray) {
			insertGridStatement = gridSiteSqlGenerator.insertGridJsonArrayToMySql(gridSite);
			insertStatementForGrid.add(insertGridStatement);
			insertParametersStatement = gridSiteSqlGenerator.insertParametersJsonArrayToMySql(gridSite) ;
			for(String str: insertParametersStatement) {
				insertStatementForParameters.add(str);
			}
		}
	}
}
