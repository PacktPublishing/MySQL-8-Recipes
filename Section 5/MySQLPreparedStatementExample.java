package ch.krenger.packt.mysql8.javaexample;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Packt MySQL 8 Recipes Java Example
 *
 * @author Simon Krenger <simon@krenger.ch>
 *
 */
public class MySQLPreparedStatementExample {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/hr";
	private static final String DB_USER = "hr_user";
	private static final String DB_PASSWORD = "secretpassword";

	public static void main(String[] argv) {

		Connection dbConnection = null;

		PreparedStatement preparedStatement = null;

		try {
			// Load class
			Class.forName(DB_DRIVER);

			// Get a new database connection
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

			// Prepared statement, using ? as the placeholder
			String selectStatement = "SELECT first_name, last_name FROM employees WHERE employee_id > ?";
			preparedStatement = dbConnection.prepareStatement(selectStatement);

			// Bind variable
			preparedStatement.setInt(1, 99);

			// Execute statement
			ResultSet rs = preparedStatement.executeQuery();

			// Iterate over rows in result
			while (rs.next()) {

				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");

				System.out.println("first_name: " + firstName + ", last_name: " + lastName);
			}
		} catch (SQLException | ClassNotFoundException e) {
			// Error handling
			System.out.println(e.getMessage());
		} finally {
			// After execution, always close resources
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (SQLException e) {
				// Error handling
				e.printStackTrace();
			}
		}

	}
}
