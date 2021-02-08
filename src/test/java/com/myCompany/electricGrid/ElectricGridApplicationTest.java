package com.myCompany.electricGrid;

import static org.assertj.core.api.Assertions.assertThatNoException;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElectricGridApplicationTest {
	
	@Test
	void testWriteMySqlToFile() throws IOException { 
		String[] fileNames =  {"input","outputFile"};
		ElectricGridApplication.main(fileNames);
		assertThatNoException();
	}
	
	@Test
	void testWriteSqlToConsole() throws IOException { 
		String[] fileNames =  {"input"};
		ElectricGridApplication.main(fileNames);
		assertThatNoException();
	}
}
