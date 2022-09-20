package ui;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dto.MPAA;

/**
 * Performs the methods specified in the UserIO interface
 * @author benat
 *
 */
public class UserIOImpl implements UserIO{
	private final Scanner sc = new Scanner(System.in);
	
	/**
	 * Prints a message
	 */
	@Override
	public void print(String msg) {
		System.out.println(msg);
	}
	
	/**
	 * Reads an integer
	 */
	@Override
	public int readInteger() {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                String stringValue = sc.nextLine();
                // Get the input line, and try and parse
                num = Integer.parseInt(stringValue); 
                invalidInput = false; 
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
        return num;
	}
	/**
	 * Read string from scanner. Only reads words no integer
	 */
	@Override
	public String readString() {	
        String string = null;
        boolean validInput = true;
        do {
            String stringInput= sc.nextLine();
            Pattern p = Pattern.compile("[a-zA-Z\\s]+"); //Regex
            Matcher m = p.matcher(stringInput);
            if(m.matches() && !stringInput.isBlank()) {
            	string=stringInput;
            	validInput = true;
            }
            else {
            	this.print("Please only enter words");
            	validInput = false;
            }
        } while(!validInput);
        return string;
	}
	
	/**
	 * Reads double from scanner. Similar to read int, but accepts decimal numbers.
	 */
	@Override
	public double readDouble() {
        boolean invalidInput = true;
        double num = 0;
        while (invalidInput) {
            try {
            	String stringValue = sc.nextLine();
            	num = Double.parseDouble(stringValue);
            	if(num>=0 && num<=10) {
            		invalidInput = false; 
            	}
            	else {
            		this.print("Please enter a number in the range of 0.0 and 10.0");
            	}
                
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
        return num;
	}
	
	/**
	 * Read date from scanners
	 */
	@Override
	public LocalDate readLocalDate() {
        LocalDate date = null;
        boolean validInput = true;
        do {
            try {
                String stringInput= sc.nextLine();
                date = LocalDate.parse(stringInput); 
                validInput = true;
            } catch (DateTimeException e) {
                this.print("Input error. Date is not in the correct format");
                validInput = false;
            }
        } while(!validInput);
        return date;
    }

	/**
	 * Read string or integers or string with integers from a scanner
	 */
	@Override
	public String readStringWithNum() {
        String string = null;
        boolean validInput = true;
        do {
            String stringInput= sc.nextLine();
            Pattern p = Pattern.compile("[\\t\\s]+"); //Regex
            Matcher m = p.matcher(stringInput);
            if(m.matches() || stringInput.isBlank()) {
            	this.print("Please enter anything but blank spaces");
            	validInput = false;
            }
            else {
            	string=stringInput;
            	validInput = true;
            }
        } while(!validInput);
        return string;
	}

	/**
	 * Read string from scanner, returns error if it is not an MPAA rating enum.
	 */
	@Override
	public MPAA readMPA() {
        MPAA mpa = null;
        boolean validInput = true;
        do {
        	String stringInput = sc.nextLine();
                mpa = switch(stringInput.toUpperCase()) {
                	case "G"->MPAA.G;
                	case "PG"->MPAA.PG;
                	case "PG-13"->MPAA.PG13;
                	case "R"->MPAA.R;
                	case "N-17"->MPAA.NC17;
                	default-> null;
                };
           if(mpa != null) {
               validInput = true;
           }
           else {
               this.print(stringInput+" MPAA rating does not exist!");
               validInput = false;
           }
        }
         while(!validInput);
        return mpa;
	}

    /**
	* Read the user's choice of which field they desire to edit
	*/	
	@Override
	public String readOptions() {
        String options = null;
        boolean validInput = true;
        do {
        	String stringInput = sc.nextLine();
                options = switch(stringInput.toUpperCase()) {
                	case "TITLE"->"TITLE";
                	case "RELEASE DATE"->"RELEASE DATE";
                	case "MPAA RATING"->"MPAA RATING";
                	case "DIRECTOR'S NAME"->"DIRECTOR'S NAME";
                	case "STUDIO"->"STUDIO";
                	case "USER RATING"->"USER RATING";
                	case "NOTE"->"NOTE";
                	default-> null;
                };
           if(options != null) {
               validInput = true;
           }
           else {
               this.print("Please enter one of the choices shown on the list");
               validInput = false;
           }
        }
         while(!validInput);
        return options;
	}

    /**
	* Read if the user has entered any blank spaces
	*/
	@Override
	public String readAnything() {
        String string = null;
        boolean validInput = true;
        do {
            String stringInput= sc.nextLine();
            if(stringInput.isBlank()) {
            	string="*EMPTY*";	//In the case of adding optional note, add *EMPTY* token to text to avoid errors
            }
            else {
            	string=stringInput;
            	validInput = true;
            }
        } while(!validInput);
        return string;
	}
}
