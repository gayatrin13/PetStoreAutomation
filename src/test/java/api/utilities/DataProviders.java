package api.utilities;

import org.testng.annotations.DataProvider;

import api.base.TestBase;

public class DataProviders extends TestBase {

	@DataProvider(name = "getUsers")
	public Object[][] getAllUsers() {

		Object[][] object = ExcelDataReader.getCellData(
				System.getProperty("user.dir") + configProps.getProperty("DataFilePath"),
				configProps.getProperty("ExcelSheetName"));

		return object;
	}

	@DataProvider(name = "usernames")
	public String[] getUsernames() {
		String[] usernames = ExcelDataReader.getUsernames(
				System.getProperty("user.dir") +configProps.getProperty("DataFilePath"),
				configProps.getProperty("ExcelSheetName"));
		return usernames;
	}

}
