package com.flairstech.framework;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * 
 * @author SarahSaloum
 *
 */
public class FileActions {

    public static FileReader readFile(String filePath) {

	FileReader reader = null;
	try {
	    reader = new FileReader(filePath);
	} catch (FileNotFoundException e) {
	    ReportManager.test.fail("File in this location " + filePath + " was not found!");
	    e.printStackTrace();
	}

	return reader;
    }

}
