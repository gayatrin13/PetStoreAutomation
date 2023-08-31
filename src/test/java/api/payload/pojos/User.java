package api.payload.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {

	public int id;
	public String username;
	public String firstName;
	public String lastName;
	public String email;
	public String password;
	public String phone;
	public int userStatus;

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + ", phone=" + phone + ", userStatus=" + userStatus
				+ "]";
	}

}
