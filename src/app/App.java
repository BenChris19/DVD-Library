package app;

import controller.DVDLibraryController;
import dao.DVDLibraryDao;
import dao.DVDLibraryDaoImpl;
import ui.DVDView;
import ui.UserIO;
import ui.UserIOImpl;

/**
 * Instantiates the DVD Library Controller and executes it. Starting point of the program.
 * The App class acts as the application assembler, it chooses the implementations of the dependencies and 
 * wires them together.
 * 
 * @author benat
 */
public class App {

	/**
	 * Main method. Use dependency injection and wire the entire application.
	 */
	public static void main(String[] args) {
		UserIO io = new UserIOImpl();
		DVDLibraryDao dao = new DVDLibraryDaoImpl();
		DVDView view = new DVDView(io);
		DVDLibraryController Controller = new DVDLibraryController(view, dao);
		Controller.run();	
	}
}
