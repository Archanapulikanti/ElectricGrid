package com.myCompany.electricGrid;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonParseException;
import com.myCompany.model.GridSite;

@SpringBootApplication
public class ElectricGridApplication {
	
	private static final Logger LOGGER = LogManager.getLogger(ElectricGridApplication.class);

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ElectricGridApplication.class, args);
		
		LOGGER.info("Starting to read JSON and prepare SQL statements");
		String inputJsonFileName = null;
		String outputMySqlFileName = null;
		int i = 0;
		for(String str:args) {
			if (i == 0) {
				inputJsonFileName = args[i];
			}
			if (i ==1) {
				outputMySqlFileName = args[i];
			}
			i++;
		}
		ElectricGridJsonReader electricGridJsonReader = new ElectricGridJsonReader();
		GridSite[] gridSiteArray = null;
		try {
			gridSiteArray = electricGridJsonReader.readJson(inputJsonFileName);
		} catch (JsonParseException e) {
			LOGGER.error("Unable to parse JSON file",e);
			throw e;
		} catch (IOException e) {
			LOGGER.error("Unable Find the JSON file",e);
			throw e;
		}
		if (gridSiteArray != null) {
			ElectricGridSqlWriter electricGridSqlWriter = new ElectricGridSqlWriter();
			electricGridSqlWriter.writeSql(gridSiteArray,outputMySqlFileName);
			LOGGER.info("Successfully converted JSON to SQL");
		}
	}


}
