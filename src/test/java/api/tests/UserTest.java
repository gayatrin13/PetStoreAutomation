package api.tests;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.base.TestBase;
import api.endpoints.UserEndpoint;
import api.payload.pojos.User;
import io.restassured.response.Response;

public class UserTest extends TestBase {
	Faker faker;
	User user;

	@BeforeClass
	public void setUpData() {
		faker = new Faker();
		user = new User(faker.idNumber().hashCode(), faker.name().username(), faker.name().firstName(),
				faker.name().lastName(), faker.internet().safeEmailAddress(), faker.internet().password(6, 15),
				faker.phoneNumber().cellPhone(), 0);
		logger = LogManager.getLogger(getClass());
	}

	@Test(priority = 1)
	public void createUserTest(ITestContext context) {
		logger.info("********createUserTest*********");
		Response response = UserEndpoint.createUser(user);
		User user1 = response.body().as(User.class);
		System.out.println("user respo : " + user1.toString());
//		System.out.println(response.asPrettyString());
//		System.out.println(user1);
//		System.out.println("user1.username: " + user1.username);
		context.setAttribute("username", user1.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2)
	public void getUserTest(ITestContext context) {
		logger.info("******getUserTest*****");
		String username = (String) context.getAttribute("username");
		System.out.println("username :" + username);
		Response resp = UserEndpoint.getUser(username);
		logger.log(Level.INFO, resp.then().log().body());
		User user = resp.getBody().as(User.class);

		Assert.assertEquals(resp.getStatusCode(), 200);
	}

	@Test(priority = 3)
	public void updateUserTest(ITestContext context) {
		logger.info("*******updateUserTest******");
		user.setFirstName(faker.name().firstName());
		user.setPassword(faker.internet().password());
		Response res = UserEndpoint.updateUser(user.username, user);
//		User u = res.getBody().as(User.class);
		logger.log(Level.INFO, res.then().log().all());
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(priority = 5)
	public void deleteUserTest() {
		logger.info("*********deleteUserTest**********");
		Response res = UserEndpoint.deleteUser(user.username);
		logger.log(Level.INFO, res.then().log().all());

		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(priority = 4)
	public void patchUser() {

	}
}
