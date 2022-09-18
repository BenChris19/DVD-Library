package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dto.DVD;
import dto.MPAA;

/**
 * Handles user interaction with the program
 * @author benat
 */
public class DVDView {

	private UserIO io;
	
	/**
	 * Dependency injection into constructor
	 * @param io
	 */
	public DVDView(UserIO io) {
		this.io = io;
	}
	
	/**
	 * Display menu to the user
	 * @return read integer option from user
	 */
	public int showMenu() {
		
		io.print("Please select from the following choices:");
		io.print("1. Add a DVD to the collection");
		io.print("2. Remove a DVD from the collection");
		io.print("3. Edit information for existing DVD");
		io.print("4. List all DVDs in the collection");
		io.print("5. Display information for a particular DVD");
		io.print("6. Find DVD by titles in library");
		io.print("7. Exit");
		io.print("");
		
		io.print("Please enter a number between 1 and 7");
		return io.readInteger();
	}
	
	/**
	 * Allows user to enter information about a DVD
	 * @return
	 */
	public DVD getNewDVDInfo() {
		io.print("*************=== Create DVD ===*************");
		
		io.print("Please enter the DVD's title");
		String title = io.readStringWithNum();
		
		io.print("Please enter the release date of the DVD in the format YYYY-MM-DD");
		LocalDate releaseDate = io.readLocalDate();
		
		io.print("Please enter the MPAARating of the DVD (G, PG, PG-13, R, N-17)");
		MPAA mPAARating = io.readMPA();
		
		io.print("Please enter the director's name");
		String directorName = io.readString();
		
		io.print("Please enter the studio's name");
		String studio = io.readStringWithNum();

		io.print("Please enter DVD rating, from (0.0-10.0)");
		double rating = io.readDouble();
		
		io.print("Please enter any additional information about the DVD, if there is nothing to say just write \"No answer\"");
		String note = io.readStringWithNum();
		
		DVD currentDVD = new DVD();
		currentDVD.setTitle(title);
		currentDVD.setReleaseDate(releaseDate);
		currentDVD.setMpaRating(mPAARating);
		currentDVD.setDirectorName(directorName);
		currentDVD.setStudio(studio);
		currentDVD.setRating(rating);
		currentDVD.setNote(note);
		
		return currentDVD;
	}
	
	/**
	 * Display DVD has been successfully created
	 */
	public void displayCreateDVDSuccessBanner() {
	    io.print("DVD has been successfully created, please press enter to continue");
	    io.print("*************==================*************");
	    io.readStringWithNum();
	}
	
	/**
	 * Display a list of all DVDs that have been created
	 * @param DVDList
	 */
	public void displayAllDVDList(List<DVD> DVDList) {
		
		io.print("*************=== All DVDs in the library ===*************");
		if(DVDList.isEmpty()) {
			io.print("The library is empty!");
		}
		else {
			for(DVD dvd : DVDList) {
				io.print("Title: " + dvd.getTitle() 
				+ "\nRelease Date: " + dvd.getReleaseDate()
				+ "\nMPAA Rating: " + dvd.getMpaRating()
				+ "\nDirector Name: " + dvd.getDirectorName()
				+ "\nStudio: " + dvd.getStudio()
				+ "\nRating: "+ dvd.getRating()
				+ "\nNotes: " + dvd.getNote()
				+"\n");
			}
		}
		io.print("All DVDs were displayed successfully");
		io.print("*************==================*************");
		
		io.print("Please type \"continue\" to continue");
		io.readStringWithNum();
	}
	
	/**
	 * Gets DVD title from the user 
	 * @return title of DVD
	 */
	public String DVDTitleRequest() {
		io.print("Please write the DVD's title");
		String title = io.readStringWithNum();
		return title;
	}
	
	/**
	 * Display info from a specific DVD
	 * @param dvd DVD object, contains info like title, release date etc.
	 * @return
	 */
	public DVD displayDVD(DVD dvd) {
		io.print("******==DVD info==*******");
		if(dvd != null) {
			String dvdInfo = "Title: " + dvd.getTitle() 
			+ "\nRelease Date: " + dvd.getReleaseDate()
			+ "\nMPAA Rating: " + dvd.getMpaRating()
			+ "\nDirector Name: " + dvd.getDirectorName()
			+ "\nStudio: " + dvd.getStudio()
			+ "\nNotes: " + dvd.getNote();
			
			io.print(dvdInfo);
		}
		io.print("Please type \"continue\" to continue");
		io.readStringWithNum();
		
		return dvd;
	}
	
	/**
	 * Returns if the DVD has been succesfully displayed or not
	 * @param dvd
	 */
	public void displayDisplayDVDSuccessBanner(DVD dvd) {
		
		if(dvd != null) {
			io.print("DVD was successfully displayed");
		}
		else {
			io.print("No such DVD found\n");
		}
	}
	
	/**
	 * Display all DVD titles
	 * @param DVDList the list of DVDs in the library
	 */
	public void displayDVDTitles(List<DVD> DVDList) {
		io.print("*************=== All DVDs in the library by title ===*************");
		if(DVDList.isEmpty()) {
			io.print("The library is empty!");
		}
		else {
			for(DVD dvd : DVDList) {
				io.print(dvd.getTitle());
			}
		}
		io.print("\nAll DVD titles were displayed successfully");
		io.print("*************==================*************");
		
		io.print("Please type \"continue\" to continue");
		io.readStringWithNum();
	}
	
	/**
	 * Tell user if DVD has been removed or not
	 * @param dvd the DVD to check
	 */
	public void removeDVDSuccessBanner(DVD dvd) {
		
		if(dvd != null) {
			io.print("DVD was removed successfully");
		}
		else {
			io.print("No such DVD found\n");
		}
	}
	
	/**
	 * Displays error if user has selected an option not in the menu.
	 */
	public void displayOutOfBoundsMessage() {
		io.print("Option entered is not in the menu, please enter a number between 1 and 7\n");
	}
	
	/**
	 * Displays exit message
	 */
	public void displayExitMessage() {
		io.print("Program has exited. End of Program");
	}
	
	/**
	 * Display error message if DVDLibraryDAOEXception is thrown
	 * @param errorMsg
	 */
	public void displayErrorMessage(String errorMsg) {
		io.print("== Error == ");
		io.print(errorMsg);
	}
	
	/**
	 * Asks the user what field they want to select in order to make a change
	 * @return
	 */
	public String editDVDInfo(){
		io.print("Please enter a piece of information related to the DVD");
		String userInput = null;
		io.print("You can enter:\n"
				+ "Title\n"
				+ "Release date\n"
				+ "MPAA rating\n"
				+ "Director's name\n"
				+ "Studio\n"
				+ "User rating\n"
				+ "Note\n");
		userInput = io.readOptions();
		return userInput;
	}
	
	/**
	 * Get the change from the field that the user wants to edit.
	 * @param editInfo the type of field the user wants to change
	 * @return
	 */
	public String getChange(String editInfo) {
		String userInput = null;
		io.print("Please enter your new change that you'd like to make");
		switch(editInfo.toLowerCase()) {
			case "release date":
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
				userInput = io.readLocalDate().format(formatter);
				break;
			case "mpaa rating":
				userInput = io.readMPA().toString();
				break;
			case "director name":
				userInput = io.readString();
				break;
			case "rating":
				userInput = Double.toString(io.readDouble());
				break;
			default:
				userInput = io.readStringWithNum();	
			}
		return userInput;
	}
	
	/**
	 * Returns if the DVD info has been changed successfully or unsuccessfully according to user inputs
	 * @param dvd
	 */
	public void displayMakeChangeSuccessBanner(DVD dvd) {
		if(dvd != null) {
			io.print("DVD information was changed successfully");
		}
		else {
			io.print("DVD change was unsuccessful, please make sure that you enter correct DVD "
					+ "title and correct piece of information");
		}
	}
}
