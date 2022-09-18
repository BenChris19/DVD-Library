package controller;

import dao.DVDLibraryDao;
import dao.DVDLibraryDaoException;
import dto.DVD;
import ui.DVDView;

/**
 * Controller for the DVD view class. Handles user interaction.
 * @author benat
 */
public class DVDLibraryController {
	
	private DVDView view;
	private DVDLibraryDao dao;
	
	/**
	 * Dependency injection 
	 * @param view Allows the user to view and interact with the program
	 * @param dao Allows for accessing data
	 */
	public DVDLibraryController(DVDView view, DVDLibraryDao dao) {
		this.view = view;
		this.dao = dao;
	}
	
	/**
	 * Controller execution method
	 */
	public void run() {
		boolean keepGoing = true;
		int userSelection = 0;
		
		try {
		while(keepGoing) {
			
			//Get the selection from user
			userSelection = getMenuSelection();
			
			switch(userSelection) {
				
			case 1:
				createDVD();
				break;
			case 2:
				removeDVD();
				break;
			case 3:
				editDVD();
				break;
			case 4:
				displayAllDVDs();
				break;
			case 5:
				displayDVDByTitle();
				break;
			case 6:
				displayDVDTitles();
				break;
			case 7:
				keepGoing = false;
				break;
			default:
				outOfBoundsOption();
			}
		}
		exitMessage();
		}
		catch(DVDLibraryDaoException e) {
			view.displayErrorMessage(e.getMessage());
		}
	}
	
	/**
	 * Gets menu selection
	 * @return The menu for the user to see
	 */
	private int getMenuSelection() {
		return view.showMenu();
	}
	
	/**
	 * Create a new DVD instance
	 * @throws DVDLibraryDaoException
	 */
	private void createDVD() throws DVDLibraryDaoException{
		DVD newDVD = view.getNewDVDInfo();
		dao.addDVD(newDVD.getTitle(), newDVD);
		view.displayCreateDVDSuccessBanner();
	}
	
	/**
	 * Displays all of the DVDs in the library
	 * @throws DVDLibraryDaoException
	 */
	private void displayAllDVDs() throws DVDLibraryDaoException{
		view.displayAllDVDList(dao.getAllDVDs());
	}
	
	/**
	 * Finds and displays the info of a DVD if it's in the library
	 * @throws DVDLibraryDaoException
	 */
	private void displayDVDByTitle() throws DVDLibraryDaoException{
		String DVDtitle = view.DVDTitleRequest();
		DVD tempDVD = view.displayDVD(dao.getDVD(DVDtitle));
		view.displayDisplayDVDSuccessBanner(tempDVD);
	}
	
	/**
	 * Shows the user all the dvds in the library by title
	 * @throws DVDLibraryDaoException
	 */
	private void displayDVDTitles() throws DVDLibraryDaoException{
		view.displayDVDTitles(dao.getAllDVDs());
		
	}
	
	/**
	 * Remove DVD from Library
	 * @throws DVDLibraryDaoException
	 */
	private void removeDVD() throws DVDLibraryDaoException{
		String title = view.DVDTitleRequest();
		DVD dvd = dao.removeDVD(title);
		view.removeDVDSuccessBanner(dvd);
	}
	
	/**
	 * Edit the info of a DVD
	 * @throws DVDLibraryDaoException
	 */
	private void editDVD() throws DVDLibraryDaoException{
		
		String title = view.DVDTitleRequest();
		String editDVDInfo = view.editDVDInfo();
		String change = view.getChange(editDVDInfo);
		DVD dvd = dao.editDVDInformation(title, editDVDInfo, change);
		view.displayMakeChangeSuccessBanner(dvd);
	}
	
	/**
	 * Displays the Menu again, if the option chosen by the user is not in the menu
	 */
	private void outOfBoundsOption() {
		view.displayOutOfBoundsMessage();
	}
	
	/**
	 * Display the exit message
	 */
	private void exitMessage() {
		view.displayExitMessage();
	}
	
}
