package gameApplication;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import gameDao.VideoGameDao;
import gameEntity.VideoGame;

public class Menu {
	
	private VideoGameDao videoGameDao = new VideoGameDao();
	private Scanner sc = new Scanner(System.in);
	private List<String> allChoices = Arrays.asList(
									"Show All Games",
									"Show Game",
									"Create Game",
									"Edit Game",
									"Delete Game");
	
	public void start() {
		// need user to be able to select an option
		String option = "";
		// loop menu without restarting
		
		do {
			printMenu();
			option = sc.nextLine();
			
			// CRUDing with try catch
			try {
				// READ ALL
				if (option.equals("1")) {
					displayGames();
				// READ ONE
				} else if (option.equals("2")) {
					displayGame();
				// CREATE
				} else if (option.equals("3")) {
					createGame();
				// UPDATE
				} else if (option.equals("4")) {
					editGame();
				// DELETE
				} else if (option.equals("5")) {
					deleteGame();
				}
			} catch (SQLException e) {
				// print error block
				e.printStackTrace();
			}
			
			// hold on each option
			System.out.println("Press any key to continue...");
			sc.nextLine();
			
		} while (
			// keeps menu open
			!option.equals(-1)
		);		
	}
	
	// printMenu method, loop to print each option
	private void printMenu() {
		System.out.println("Select an option: \n---------------");
		for (int i = 0; i < allChoices.size(); i++) {
			System.out.println(i + 1 + " -- " + allChoices.get(i));
		}
	}
	
	private void displayGames() throws SQLException {
		// retrieving the data from the table
		List<VideoGame> videogames = videoGameDao.getVideoGames();
		for (VideoGame videogame : videogames) {
			System.out.println(videogame.getVideoGameId() + ": " + videogame.getName());
		}
	}
	
	private void displayGame() throws SQLException {
		System.out.print("Enter game id: ");
		// have to use parse to get int in correct format
		int id = Integer.parseInt(sc.nextLine());
		VideoGame videogame = videoGameDao.getVideoGameById(id);
		System.out.println(videogame.getVideoGameId() + " - " + videogame.getName() + " (" + videogame.getType() + ")");
	}
	
	private void createGame() throws SQLException {
		System.out.print("Enter new game name: ");
		String videoGameName = sc.nextLine();
		System.out.print("Enter new game type: ");
		String videoGameType = sc.nextLine();
		videoGameDao.createNewVideoGame(videoGameName, videoGameType);
	}
	
	private void editGame() throws SQLException {
		System.out.print("Enter game id to edit: ");
		int id = Integer.parseInt(sc.nextLine());
		// print out current name/type
		VideoGame videogame = videoGameDao.getVideoGameById(id);
		String currentName = videogame.getName();
		String currentType = videogame.getType();
		System.out.println("Current game name: " + currentName);
		System.out.println("Current game type: " + currentType);
		
		// use input to update
		System.out.print("Enter new game name: ");
		String videoGameName = sc.nextLine();
		System.out.print("Enter new game type: ");
		String videoGameType = sc.nextLine();
		videoGameDao.editVideoGame(id, videoGameName, videoGameType);
	}
	
	private void deleteGame() throws SQLException {
		System.out.print("Enter game id to delete: ");
		int id = Integer.parseInt(sc.nextLine());
		videoGameDao.deleteVideoGameById(id);
	}
}
