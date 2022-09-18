package dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/**
 * The DVD Data Transfer Object. The DVD class includes basic info about the DVD and getter and setter methods.
 * We're using enums for MPA ratings
 * 
 * @author benat
 */
public class DVD {

	private String title;
	private LocalDate releaseDate;
	private MPAA mpaRating;
	private String directorName;
	private String studio;
	private double rating;
	private String note;
}
