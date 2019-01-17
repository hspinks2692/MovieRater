import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {

	public static void main(String[] args) {
		String ratingfile = "ratings.csv";
		String moviefile = "ratedmoviesfull.csv";
//		String ratingfile = "ratings_short.csv";
//		String moviefile = "ratedmovies_short.csv";
		int miniRatings = 5;
		String raterID = "314";
		int numTopSim = 10;
		MovieRunnerSimilarRatings mRunnerSimilarRatings = new MovieRunnerSimilarRatings();
//		mRunnerSimilarRatings.printAverageRatings(ratingfile, moviefile, miniRatings);
//		mRunnerSimilarRatings.printAverageRatingsByYearAfterAndGenre(ratingfile, moviefile, miniRatings,1990, "Drama");
//		mRunnerSimilarRatings.printSimilarRatings(ratingfile, moviefile, miniRatings, raterID, numTopSim);
//		mRunnerSimilarRatings.printSimilarRatingsByGenre(ratingfile, moviefile, miniRatings,raterID, numTopSim,"Mystery");
//		mRunnerSimilarRatings.printSimilarRatingsByDirector(ratingfile, moviefile, miniRatings, raterID,
//				                                            numTopSim, "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
//		mRunnerSimilarRatings.printSimilarRatingsByGenreAndMinutes(ratingfile, moviefile, miniRatings, raterID, numTopSim, "Drama", 80, 160);
		mRunnerSimilarRatings.printSimilarRatingsByYearAfterAndMinutes(ratingfile, moviefile, miniRatings, raterID, numTopSim, 1975, 70, 200);
	}
	public void printSimilarRatings(String ratingfile, String moviefile, int minimumRatings, String raterID, int numOfTopSimilar)
	{
		MovieDatabase.initialize(moviefile);
		RaterDatabase.initialize(ratingfile);
		FourthRatings fourthRatings = new FourthRatings();
		ArrayList<Rating> ratings = fourthRatings.getSimilarRatings(raterID, numOfTopSimilar, minimumRatings);
		int count = 0;
		System.out.println("Recommended Movies and Similarity Ratings: ");
		for (Rating rating : ratings)
		{
			count++;
			if (count < 20)
			{
				
				System.out.println("    " + MovieDatabase.getTitle(rating.getItem()) + "  " + rating.getValue());
			}
			else
			{
				break;
			}
			
		}
		System.out.println("The total number of recommended movies: " + ratings.size());
		if (ratings.size() > 0)
		{
			System.out.println("Top Choice: " + MovieDatabase.getTitle(ratings.get(0).getItem()));
		}	
		
	}
	public void printSimilarRatingsByGenre(String ratingfile, String moviefile, int minimumRatings, String raterID, int numOfTopSimilar, String genre)
	{
		MovieDatabase.initialize(moviefile);
		RaterDatabase.initialize(ratingfile);
		FourthRatings fourthRatings = new FourthRatings();
		Filter filterCriteria = new GenreFilter(genre);
		ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(raterID, numOfTopSimilar, minimumRatings, filterCriteria);
		int count = 0;
		System.out.println("Recommended Movies and Similarity Ratings: ");
		for (Rating rating : ratings)
		{
			count++;
			if (count < 20)
			{
				
				System.out.println("    " + MovieDatabase.getTitle(rating.getItem()) + "  " + rating.getValue());
				System.out.println("        Genre: " + MovieDatabase.getGenres(rating.getItem()));
			}
			else
			{
				break;
			}
			
		}
		System.out.println("The total number of recommended movies: " + ratings.size());
		if (ratings.size() > 0)
		{
			System.out.println("Top Choice: " + MovieDatabase.getTitle(ratings.get(0).getItem()));
		}		
	}
	public void printSimilarRatingsByDirector(String ratingfile, String moviefile, int minimumRatings, String raterID, int numOfTopSimilar, String director)
	{
		MovieDatabase.initialize(moviefile);
		RaterDatabase.initialize(ratingfile);
		FourthRatings fourthRatings = new FourthRatings();
		Filter filterCriteria = new DirectorsFilter(director);
		ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(raterID, numOfTopSimilar, minimumRatings, filterCriteria);
		int count = 0;
		System.out.println("Recommended Movies and Similarity Ratings: ");
		for (Rating rating : ratings)
		{
			count++;
			if (count < 20)
			{
				
				System.out.println("    " + MovieDatabase.getTitle(rating.getItem()) + "  " + rating.getValue());
				System.out.println("        Director: " + MovieDatabase.getDirector(rating.getItem()));
			}
			else
			{
				break;
			}
			
		}
		System.out.println("The total number of recommended movies: " + ratings.size());
		if (ratings.size() > 0)
		{
			System.out.println("Top Choice: " + MovieDatabase.getTitle(ratings.get(0).getItem()));
		}	
		
	}
	
	public void printSimilarRatingsByGenreAndMinutes(String ratingfile, String moviefile, int minimumRatings, String raterID, int numOfTopSimilar, String genre, int min, int max)
	{
		MovieDatabase.initialize(moviefile);
		RaterDatabase.initialize(ratingfile);
		FourthRatings fourthRatings = new FourthRatings();
		AllFilters filterCriteria = new AllFilters();
		filterCriteria.addFilter(new GenreFilter(genre));
		filterCriteria.addFilter(new MinutesFilter(min, max));
		ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(raterID, numOfTopSimilar, minimumRatings, filterCriteria);
		int count = 0;
		System.out.println("Recommended Movies and Similarity Ratings: ");
		for (Rating rating : ratings)
		{
			count++;
			if (count < 20)
			{
				
				System.out.println("    " + MovieDatabase.getTitle(rating.getItem()) + "  " 
				                   + MovieDatabase.getMinutes(rating.getItem()) + "mins  " + rating.getValue());
				System.out.println("        Genre: " + MovieDatabase.getGenres(rating.getItem()));
			}
			else
			{
				break;
			}
			
		}
		System.out.println("The total number of recommended movies: " + ratings.size());
		if (ratings.size() > 0)
		{
			System.out.println("Top Choice: " + MovieDatabase.getTitle(ratings.get(0).getItem()));
		}		
	}
	public void printSimilarRatingsByYearAfterAndMinutes(String ratingfile, String moviefile, int minimumRatings, String raterID, int numOfTopSimilar, int year, int min, int max)
	{
		MovieDatabase.initialize(moviefile);
		RaterDatabase.initialize(ratingfile);
		FourthRatings fourthRatings = new FourthRatings();
		AllFilters filterCriteria = new AllFilters();
		filterCriteria.addFilter(new YearAfterFilter(year));
		filterCriteria.addFilter(new MinutesFilter(min, max));
		ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(raterID, numOfTopSimilar, minimumRatings, filterCriteria);
		int count = 0;
		System.out.println("Recommended Movies and Similarity Ratings: ");
		for (Rating rating : ratings)
		{
			count++;
			if (count < 20)
			{
				
				System.out.println("    " + MovieDatabase.getTitle(rating.getItem()) + "  " 
						           + "  Year: " + MovieDatabase.getYear(rating.getItem()) 
				                   + MovieDatabase.getMinutes(rating.getItem()) + "mins  " + rating.getValue());
			}
			else
			{
				break;
			}
			
		}
		System.out.println("The total number of recommended movies: " + ratings.size());
		if (ratings.size() > 0)
		{
			System.out.println("Top Choice: " + MovieDatabase.getTitle(ratings.get(0).getItem()));
		}		
	}
	public void printAverageRatings(String ratingfile, String moviefile, int minimumRatings)
	{
		MovieDatabase.initialize(moviefile);
		RaterDatabase.initialize(ratingfile);
		FourthRatings tRatings = new FourthRatings();
	    System.out.println("The number of movies: " + MovieDatabase.size());
	    System.out.println("The number of raters: " + RaterDatabase.size());
	    ArrayList<Rating> avgRatings = tRatings.getAverageRatings(minimumRatings);
	    Collections.sort(avgRatings);
	    for (int i = 0; i < avgRatings.size(); i++)
	    {
	    	System.out.printf("%5.2f    %s", avgRatings.get(i).getValue(),
	                           MovieDatabase.getTitle(avgRatings.get(i).getItem()));	 
	    	System.out.println();
	    }
	    System.out.println("The Number of movies that have at least " + minimumRatings + 
	    		           " ratings is: " + avgRatings.size());
	}
	
	public void printAverageRatingsByYearAfterAndGenre(String ratingfile, String moviefile, int minimumRatings, int year, String genre)
	{
		MovieDatabase.initialize(moviefile);
		RaterDatabase.initialize(ratingfile);
		FourthRatings tRatings = new FourthRatings();
	    System.out.println("The number of movies: " + MovieDatabase.size());
	    System.out.println("The number of raters: " + RaterDatabase.size());
	    AllFilters filterCriteria = new AllFilters();
	    filterCriteria.addFilter(new YearAfterFilter(year));
	    filterCriteria.addFilter(new GenreFilter(genre));
	    ArrayList<Rating> avgRatings = tRatings.getAverageRatingsByFilter(minimumRatings, filterCriteria);
	    Collections.sort(avgRatings);
	    for (int i = 0; i < avgRatings.size(); i++)
	    {
	    	System.out.printf("%5.2f  %d  %s", avgRatings.get(i).getValue(),MovieDatabase.getYear(avgRatings.get(i).getItem())
	                           ,MovieDatabase.getTitle(avgRatings.get(i).getItem()));
	    	System.out.println();
	    	System.out.println("             " + MovieDatabase.getGenres(avgRatings.get(i).getItem()));
	    }
	    System.out.println("The Number of movies that filted by Year and Genre is: " + avgRatings.size());
	}
}
