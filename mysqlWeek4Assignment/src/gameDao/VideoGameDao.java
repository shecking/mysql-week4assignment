package gameDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gameEntity.VideoGame;

// need to program DAO to access the data
public class VideoGameDao {
	
	private Connection connection;
	// DO NOT CONCATENATE IN QUERIES, RISK OF SQL INJECTION
	private final String GET_VIDEO_GAMES_QUERY = "SELECT * FROM videogames";
	private final String GET_VIDEO_GAME_BY_ID_QUERY = "SELECT * FROM videogames WHERE id = ?";
	private final String CREATE_NEW_VIDEO_GAME_QUERY = "INSERT INTO videogames(name, type) VALUES(?, ?)";
	private final String EDIT_VIDEO_GAME_BY_ID_QUERY = "UPDATE videogames SET name = ?, type = ? WHERE id = ?";
	private final String DELETE_VIDEO_GAME_BY_ID_QUERY = "DELETE FROM videogames WHERE id = ?";
	
	// constructor
	public VideoGameDao() {
		// set up in GameDbConnection class
		connection = GameDBConnection.getConnection();
	}
	
	// READ ALL
	public List<VideoGame> getVideoGames() throws SQLException {
		// pointing to row of a table
		ResultSet rs = connection.prepareStatement(GET_VIDEO_GAMES_QUERY).executeQuery();
		// transforming table into an array list
		List<VideoGame> videogames = new ArrayList<VideoGame>();
		
		// while there are still items
		while(rs.next()) {
			// populate new entry of videogames with fields
			videogames.add(populateGame(rs.getInt(1), rs.getString(2), rs.getString(3)));
		}
		// return the list
		return videogames;
	}
	
	// READ ONE
	public VideoGame getVideoGameById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_VIDEO_GAME_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		// get to "first" row instead of row 0
		// validation?
		rs.next();
		return populateGame(rs.getInt(1), rs.getString(2), rs.getString(3));
	}
	
	// CREATE
	public void createNewVideoGame(String videoGameName, String videoGameType) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_VIDEO_GAME_QUERY);
		ps.setString(1, videoGameName);
		ps.setString(2, videoGameType);
		ps.executeUpdate();
	}
	// UPDATE
	public void editVideoGame(int id, String videoGameName, String videoGameType) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(EDIT_VIDEO_GAME_BY_ID_QUERY);
		ps.setString(1, videoGameName);
		ps.setString(2, videoGameType);
		ps.setInt(3, id);
		ps.executeUpdate();
	}
	
	// DELETE
	public void deleteVideoGameById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_VIDEO_GAME_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
		// validation?
	}
	
	private VideoGame populateGame(int id, String name, String type) {
		return new VideoGame(id, name, type);
	}
	
}
