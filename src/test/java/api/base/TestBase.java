package api.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.RestAssured;

public class TestBase {
	public static Properties endPointProps;
	public static Properties configProps;
	public static Logger logger;
	static {
		System.out.println("static code from TestBase");
		endPointProps = new Properties();
		configProps = new Properties();
		try {

			configProps.load(new FileInputStream(new File(".\\src\\test\\resources\\config.properties")));
			endPointProps.load(new FileInputStream(new File(".\\" + configProps.getProperty("EndPointFilePath"))));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// logs
		logger = LogManager.getLogger();

		logger.info("**Logger started**");
		// set URI
		RestAssured.baseURI = endPointProps.getProperty("baseURL");
		System.out.println(RestAssured.baseURI);
	}

}
