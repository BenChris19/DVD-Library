package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dto.DVD;
import dto.MPAA;

/**
 * Implements methods from the DVDLibraryImpl interface.
 * @author benat
 */
public class DVDLibraryDaoImpl implements DVDLibraryDao {
	
	private Map<String, DVD> DVDs = new HashMap<>();
	
	public static final String DVD_File = "DVD.txt";
	public static final String DELIMITER = "::";
	
	/**
	 * Add DVD to the text file
	 */
	@Override
	public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException{
		loadDVD();
		DVD newDVD = DVDs.put(title, dvd);
		writeDVD();
		return newDVD;
	}
	
	/**
	 * Remove a DVD from the text file
	 */
	@Override
	public DVD removeDVD(String title) throws DVDLibraryDaoException {
		loadDVD();
		DVD removedDVD = DVDs.remove(title);
		writeDVD();
		return removedDVD;
	}
	
	/**
	 * Get all DVD from the library
	 */
	@Override
	public List<DVD> getAllDVDs() throws DVDLibraryDaoException{
		loadDVD();
		return new ArrayList<DVD>(DVDs.values());
	}
	
	/**
	 * Get a specific DVD by title
	 */
	@Override
	public DVD getDVD(String title) throws DVDLibraryDaoException{
		loadDVD();
		return DVDs.get(title);
	}
	
	/**
	 * Edit a specific field from a DVD searched by its title
	 */
	@Override
	public DVD editDVDInformation(String title, String editField, String change) throws DVDLibraryDaoException{
		loadDVD();
		DVD dvd = DVDs.get(title);
		
		if(dvd != null) {
			switch(editField.toLowerCase()) {
			case "title":
				dvd.setTitle(change);
				break;
			case "release date":
				dvd.setReleaseDate(LocalDate.parse(change));
				break;
			case "mpaa rating":
				dvd.setMpaRating(MPAA.valueOf(change));
				break;
			case "director name":
				dvd.setDirectorName(change);
				break;
			case "studio":
				dvd.setStudio(change);
				break;
			case "rating":
				dvd.setRating(Double.parseDouble(change));
				break;
			case "note":
				dvd.setNote(change);
				break;
			default:
				dvd=null;		
			}
		}
		if(dvd != null) DVDs.replace(dvd.getTitle(), dvd);
		writeDVD();
		return dvd;
		
	}
	
	/**
	 * Translate data from file to an object in memory
	 * 
	 * @param DVDAsText DVD object as text
	 * @return
	 */
	private DVD unmarshallDVD(String DVDAsText) {
		
		String[] DVDAsElements = DVDAsText.split(DELIMITER);
		String title = DVDAsElements[0];
		DVD dvdFromFile = new DVD();
		dvdFromFile.setTitle(title);
		dvdFromFile.setReleaseDate(LocalDate.parse(DVDAsElements[1]));
		dvdFromFile.setMpaRating(MPAA.valueOf(DVDAsElements[2]));
		dvdFromFile.setDirectorName(DVDAsElements[3]);
		dvdFromFile.setStudio(DVDAsElements[4]);
		dvdFromFile.setRating(Double.parseDouble(DVDAsElements[5]));
		dvdFromFile.setNote(DVDAsElements[6]);
		
		return dvdFromFile;
	}
	
	/**
	 * Put all unmarshalled data into the DVD collection
	 * @throws DVDLibraryDaoException
	 */
	private void loadDVD() throws DVDLibraryDaoException{
		
		Scanner scanner;
		
		try {
			scanner = new Scanner(new BufferedReader(new FileReader(DVD_File)));
		}
		catch(FileNotFoundException e) {
			throw new DVDLibraryDaoException("Could not locate the file", e);
		}
		
		String currentLine;
		DVD currentDVD;
		
		while(scanner.hasNextLine()) {
			currentLine = scanner.nextLine();
			currentDVD = unmarshallDVD(currentLine);
			DVDs.put(currentDVD.getTitle(), currentDVD);
		}
		scanner.close();
	}
	
	/**
	 * Translate data from object in memory into a text file.
	 * @param dvd
	 * @return
	 */
	private String marshallDVD(DVD dvd) {
		
		String DVDAsText = dvd.getTitle() + DELIMITER;
		DVDAsText += dvd.getReleaseDate() + DELIMITER;
		DVDAsText += dvd.getMpaRating() + DELIMITER;
		DVDAsText += dvd.getDirectorName() + DELIMITER;
		DVDAsText += dvd.getStudio() + DELIMITER;
		DVDAsText += dvd.getRating() + DELIMITER;
		DVDAsText += dvd.getNote() + DELIMITER;
		
		return DVDAsText;
	}
	
	/**
	 * Write marshalled data into the text file.
	 * @throws DVDLibraryDaoException
	 */
	private void writeDVD() throws DVDLibraryDaoException{
		
		PrintWriter out;
		
		try {
			out = new PrintWriter(new FileWriter(DVD_File));
		}
		catch(Exception e) {
			throw new DVDLibraryDaoException("Could not save DVD data", e);
		}
		
		String DVDAsText;
		List<DVD> DVDList = this.getAllDVDs();
		for(DVD currentDVD : DVDList) {
			DVDAsText = marshallDVD(currentDVD);
			out.println(DVDAsText);
			out.flush();
		}
		out.close();
	}
}
