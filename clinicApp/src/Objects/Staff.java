package Objects;

public class Staff {
	private String name;
	private String lastname;
	private String ID;
	private String username;
	private String password;
	private String role;
	private String permission;

	public Staff(String name, String lastname, String ID, String username, String password, String role,
			String permission) {
		this.name = name;
		this.lastname = lastname;
		this.ID = ID;
		this.username = username;
		this.password = password;
		this.role = role;
		this.permission = permission;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}
