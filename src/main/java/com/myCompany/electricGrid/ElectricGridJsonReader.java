package com.myCompany.electricGrid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myCompany.model.GridSite;

public class ElectricGridJsonReader {
	private static final Logger LOGGER = LogManager.getLogger(ElectricGridJsonReader.class);
	
	public GridSite[] readJson(String inputJsonFileName) throws IOException {
		LOGGER.info("Trying to read JSON file");
		GridSite[] gridSiteArray = null;
			LOGGER.info("Reading JSON file");
			String jsonArrayStr = null;
			try {
				jsonArrayStr = new String(Files.readAllBytes(Paths.get("C://Users//kiran.govind//Downloads/" + inputJsonFileName + ".json")));
			    ObjectMapper objectMapper = new ObjectMapper();
				gridSiteArray = objectMapper.readValue(jsonArrayStr, GridSite[].class);
			} catch (JsonParseException e) {
				LOGGER.error("Unable to parse JSON file",e);
				throw e;
			} catch (IOException e) {
				LOGGER.error("Unable to read JSON file",e);
				throw e;
			}
		
		return gridSiteArray;
	}
}
