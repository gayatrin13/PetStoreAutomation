package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoint;
import api.payload.pojos.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {

	@Test(priority = 1, dataProvider = "getUsers", dataProviderClass = DataProviders.class)
	public void createUsers(String id, String fName, String lName, String username, String email, String password,
			String phNO) {
		System.out.println("creating user :" + username + " id :" + Float.valueOf(id).intValue());
		User user = new User(Float.valueOf(id).intValue(), fName, lName, username, email, password, phNO, 0);
		Response res = UserEndpoint.createUser(user);
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(priority = 2, dataProvider = "usernames", dataProviderClass = DataProviders.class)
	public void deleteUsers(String username) {
		System.out.println("deleting user :" + username);
		Response res = UserEndpoint.deleteUser(username);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);

	}

}
