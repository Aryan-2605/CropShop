package CropShop.Support.dto;

import lombok.Getter;
import lombok.Setter;

public class UserPostDTO {
	@Getter
	@Setter
	String username;

	@Getter
	@Setter
	String password;

	// No-argument constructor (required by frameworks like Spring)
	public UserPostDTO() {
	}

	// Constructor with parameters to initialize username and password
	public UserPostDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
