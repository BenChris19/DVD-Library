package dao;

import java.util.List;

import dto.DVD;

/**
 * Interface which defines the methods that must be implemented by any
 * class that want to interact with the DAO.
 * 
 * @author benat
 *
 */
public interface DVDLibraryDao {

	DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException;
	
	DVD removeDVD(String title) throws DVDLibraryDaoException;
	
	List<DVD> getAllDVDs() throws DVDLibraryDaoException;
	
	DVD getDVD(String title) throws DVDLibraryDaoException;
	
	DVD editDVDInformation(String title, String pieceOfInformation, String change) throws DVDLibraryDaoException;
	
	
}
