package com.myCompany.electricGrid;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.myCompany.model.GridSite;

public class ElectricGridJsonReaderTest {
	ElectricGridJsonReader electricGridJsonReader = new ElectricGridJsonReader();
	
	@Test
	void testReadJson() throws IOException { 
		String fileName = "input";
		GridSite[] gridSite = electricGridJsonReader.readJson(fileName);
		assertNotNull(gridSite);
	}
	
	@Test
	void testReadJsonWithNonExistingFile() throws IOException { 
		String fileName = "test";
		Assertions.assertThrows(IOException.class, () -> {
			electricGridJsonReader.readJson(fileName);
		  });
	}
	
	@Test
	void testReadJsonWithWrongJsonFormat() throws IOException { 
		String fileName = "testFile";
		Assertions.assertThrows(IOException.class, () -> {
			electricGridJsonReader.readJson(fileName);
		  });
	}
}
