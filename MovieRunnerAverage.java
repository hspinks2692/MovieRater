import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {

//	public static void main(String[] args)
//	{
//		MovieRunnerAverage mRunnerAverage = new MovieRunnerAverage();
//		mRunnerAverage.printAverageRatings();
//		mRunnerAverage.getAverageRatingOneMovie();
//	}
	public void printAverageRatings()
	{
		SecondRatings sRatings = new SecondRatings("ratedmoviesfull.csv","ratings.csv");
	    System.out.println("The number of movies: " + sRatings.getMovieSize());
	    System.out.println("The number of raters: " + sRatings.getRaterSize());
	    int minnumRatings = 12;
	    ArrayList<Rating> avgRatings = sRatings.getAverageRatings(minnumRatings);
	    Collections.sort(avgRatings);
	    for (int i = 0; i < avgRatings.size(); i++)
	    {
	    	System.out.printf("%5.2f    %s", avgRatings.get(i).getValue(),
	                           sRatings.getTitle(avgRatings.get(i).getItem()));	 
	    	System.out.println();
	    }
	    System.out.println("The Number of movies that have at least " + minnumRatings + 
	    		           " rating(s) is: " + avgRatings.size());
	}
	public void getAverageRatingOneMovie()
	{
		SecondRatings sRatings = new SecondRatings("ratedmoviesfull.csv","ratings.csv");
		String title = "Vacation";
		double rating = sRatings.getAverageByTitle(title);
		System.out.println("Average rating for movie [" + title + "] is: " + rating);
	}
}
