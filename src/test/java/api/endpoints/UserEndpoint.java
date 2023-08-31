package api.endpoints;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import api.base.TestBase;
import api.payload.pojos.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoint extends TestBase {

	static User u = new User();

	public static Response createUser(User user) {
		System.out.println("*********createUser()");
		return RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).body(user).when()
				.post(endPointProps.getProperty("createUser"));
	}

	public static Response getUser(String username) {
		System.out.println("************getUser()");
		return RestAssured.given().pathParam("username", username)
				.get(endPointProps.getProperty("getUser"));
	}

	public static Response updateUser(String username, User user) {
		System.out.println("*********updateUser()");
		return RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON).body(user)
				.pathParam("username", user.username).when().put(endPointProps.getProperty("updateUser"));
	}

	public static Response deleteUser(String username) {
		System.out.println("*********deleteUser()");
		return RestAssured.given().pathParam("username", username).delete(endPointProps.getProperty("deleteUser"));

	}

	public static Response postUserUsingFile(String filepath) {

		try {
			return RestAssured.given().contentType(ContentType.JSON).body(Files.readString(Paths.get(filepath)))
					.post(endPointProps.getProperty("createUser"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
