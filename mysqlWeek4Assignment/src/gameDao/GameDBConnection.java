package gameDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// this class handles the actual connection to the database
public class GameDBConnection {
	
	// static because it's attached to one class instead of an instance
	private final static String url = "jdbc:mysql://localhost:3306/games";
	private final static String UN = "root";
	private final static String PW = "root";
	private static Connection connection;
	private static GameDBConnection instance;
	
	// constructor for connection
	// private to not construct outside of class
	private GameDBConnection(Connection connection) {
		this.connection = connection;
	}
	
	// method to get the connection we need to use
	public static Connection getConnection() {
		// if no connection exists
		// call constructor in class
		if (instance == null) {
			try {
				connection = DriverManager.getConnection(url, UN, PW);
				instance = new GameDBConnection(connection);
				System.out.println("Connection success");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("No connection established, try again");
			}
		}
		// if connection does exist, skip if + try/catch
		return GameDBConnection.connection;
	}
}
