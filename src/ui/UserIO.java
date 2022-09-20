package ui;

import java.time.LocalDate;

import dto.MPAA;

/**
 * Interface which defines the methods that must be implemented by any
 * class that want to interact with the UI.
 * 
 * @author benat
 *
 */
public interface UserIO {

	public void print(String msg);
	
	public int readInteger();
	
	public String readString();
	
	public String readStringWithNum();
	
	public double readDouble();
	
	public LocalDate readLocalDate();
	
	public String readOptions();
	
	public MPAA readMPA();
	
	public String readAnything();
}
