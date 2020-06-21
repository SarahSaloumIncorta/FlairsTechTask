package com.flairstech.framework;

import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 
 * @author SarahSaloum
 *
 */
public class PropertiesLoader {

    private final String propertiesFilesPath = System.getProperty("user.dir") + "/resources/properties/";

    public PropertiesLoader() {

	FileReader reader = FileActions.readFile(propertiesFilesPath + "flairstech.properties");
	Properties props = new Properties();
	try {
	    props.load(reader);
	} catch (IOException e) {
	    ReportManager.test.fail("Cannot load properties file !");
	    e.printStackTrace();
	}
	loadPropertiesToSystemProperties(props);
    }

    public void loadPropertiesToSystemProperties(Properties props) {

	Enumeration<?> e = props.propertyNames();

	while (e.hasMoreElements()) {
	    String key = (String) e.nextElement();
	    System.setProperty(key, props.getProperty(key));
	}

    }
}
