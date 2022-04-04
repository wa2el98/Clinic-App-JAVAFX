package connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Objects.Manager;
import Objects.Patient;
import Objects.Staff;

public class ConnectionClass {

	static Connection conn = null;

	public ConnectionClass() {
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		try {
			if (conn == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost/clinic?serverTimezone=IST", "root",
						"wael1998");
				System.out.println("SQL connection succeed");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return conn;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * DIRECTORY
	 * 
	 * 
	 * 
	 * 
	 */

	public static String getDirectory() throws ClassNotFoundException, SQLException {
		String result = null;

		try {
			Connection conn = getConnection();
			PreparedStatement posted = conn.prepareStatement("SELECT * FROM clinic.directory WHERE status=(?)");
			posted.setString(1, "cur");
			ResultSet ars = posted.executeQuery();

			if (ars.next())
				result = ars.getString(2);

			ars.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * STAFF
	 * 
	 * 
	 * 
	 * 
	 */

	public static Staff StaffCheckLogInData(String[] data) throws ClassNotFoundException, SQLException {
		String[] result = null;
		String name = data[0];
		String pass = data[1];

		try {
			Connection conn = getConnection();
			PreparedStatement posted = conn
					.prepareStatement("SELECT * FROM clinic.staff WHERE username=(?) AND password=(?)");
			posted.setString(1, name);
			posted.setString(2, pass);
			ResultSet ars = posted.executeQuery();

			if (ars.next())
				result = new String[] { "staff", ars.getString(1), ars.getString(2), ars.getString(3), ars.getString(4),
						ars.getString(5), ars.getString(6), ars.getString(7) };

			else
				return new Staff("null", "null", "null", "null", "null", "null", "null");

			ars.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// System.out.print(result);
		return new Staff(result[1], result[2], result[3], result[4], result[5], result[6], result[7]);
	}

	/*
	 * Patient Querries
	 */

	public static int InsertPatientData(Patient patient) {

		String firstname = patient.getName();
		String lastname = patient.getLastname();
		String ID = patient.getID();

		String res = new String("('" + firstname + "','" + lastname + "','" + ID + "')");

		try {
			Connection connection1 = ConnectionClass.getConnection();
			PreparedStatement posted = connection1
					.prepareStatement("INSERT INTO clinic.patient (firstname,lastName,ID) VALUES" + res);
			posted.executeUpdate();
			System.out.println("Saved to DB");
		} catch (Exception e) {
			System.out.println(e);
		}

		return 1;
	}

	public static int ExistPatient(String Id) throws ClassNotFoundException {
		String result[] = null;

		try {
			Connection conn = getConnection();
			PreparedStatement posted = conn.prepareStatement("SELECT * FROM clinic.patient WHERE ID=(?)");
			posted.setString(1, Id);
			ResultSet ars = posted.executeQuery();

			if (ars.next())
				result = new String[] { "patient", ars.getString(1), ars.getString(2), ars.getString(3) };

			ars.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (result == null)
			return 0;
		else
			return 1;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * MANAGER
	 * 
	 * 
	 * 
	 * 
	 */

	public static Manager ManagerCheckLogInData(String[] data) throws ClassNotFoundException, SQLException {
		String[] result = null;
		String name = data[0];
		String pass = data[1];

		try {
			Connection conn = getConnection();
			PreparedStatement posted = conn
					.prepareStatement("SELECT * FROM clinic.manager WHERE username=(?) AND password=(?)");
			posted.setString(1, name);
			posted.setString(2, pass);
			ResultSet ars = posted.executeQuery();

			if (ars.next())
				result = new String[] { "staff", ars.getString(1), ars.getString(2), ars.getString(3), ars.getString(4),
						ars.getString(5) };
			else
				return new Manager("null", "null", "null", "null", "null");

			ars.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new Manager(result[1], result[2], result[3], result[4], result[5]);
	}

	/*
	 * Change Key Querries
	 */
	public static String[] getNewKey() throws ClassNotFoundException, SQLException {
		String[] result = null;

		try {
			Connection conn = getConnection();
			PreparedStatement posted = conn.prepareStatement("SELECT * FROM clinic.key WHERE status=(?)");
			posted.setString(1, "new");
			ResultSet ars = posted.executeQuery();

			if (ars.next())
				result = new String[] { "key", ars.getString(1), ars.getString(2), ars.getString(3) };

			ars.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static int changeNewKeyValue(String[] data) {

		String N = data[0];
		String Variance = data[1];

		try {
			Connection connection1 = ConnectionClass.getConnection();
			PreparedStatement posted = connection1
					.prepareStatement("UPDATE clinic.key set N=(?) , Variance=(?) where status=(?)");
			posted.setString(1, N);
			posted.setString(2, Variance);
			posted.setString(3, "new");
			posted.executeUpdate();
			System.out.println("Saved to DB");
		} catch (Exception e) {
			System.out.println(e);
		}

		return 1;
	}

	public static int changeOldKeyValue(String[] data) {

		String N = data[0];
		String Variance = data[1];

		try {
			Connection connection1 = ConnectionClass.getConnection();
			PreparedStatement posted = connection1
					.prepareStatement("UPDATE clinic.key set N=(?) , Variance=(?) where status=(?)");
			posted.setString(1, N);
			posted.setString(2, Variance);
			posted.setString(3, "old");
			posted.executeUpdate();
			System.out.println("Saved to DB");
		} catch (Exception e) {
			System.out.println(e);
		}

		return 1;
	}

}
