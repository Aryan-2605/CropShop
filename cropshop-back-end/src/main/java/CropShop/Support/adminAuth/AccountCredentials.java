package CropShop.Support.adminAuth;

import lombok.Getter;
import lombok.Setter;

public class AccountCredentials {
	@Getter
	@Setter
	private String username;

	@Getter
	@Setter
	  private String password;


	  @Override
		public String toString() {
			return "AccountCredentials [username=" + username + ", password=" + password + "]";
		}

}
