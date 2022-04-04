package Objects;

public class Patient {
	private String name;
	private String lastname;
	private String ID;

	public Patient(String name, String lastname, String ID) {
		this.name = name;
		this.lastname = lastname;
		this.ID = ID;
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

}
