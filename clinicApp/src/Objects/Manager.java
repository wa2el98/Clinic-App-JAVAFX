package Objects;

public class Manager {
	@Override
	public String toString() {
		return "Manager [name=" + name + ", lastname=" + lastname + ", ID=" + ID + ", username=" + username
				+ ", password=" + password + "]";
	}

	private String name;
	private String lastname;
	private String ID;
	private String username;
	private String password;

	public Manager(String name, String lastname, String ID, String username, String password) {
		this.name = name;
		this.lastname = lastname;
		this.ID = ID;
		this.username = username;
		this.password = password;

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

}