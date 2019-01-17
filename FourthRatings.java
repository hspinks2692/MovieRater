import java.util.ArrayList;
import java.util.Collections;


public class FourthRatings {

	private double getAverageByID(String id, int minimalRaters)
	{
		ArrayList<Rater> myRaters = RaterDatabase.getRaters();
		double average = 0;
		double sum = 0;
		int count = 0;
		for (int i = 0; i < myRaters.size(); i++)
		{
			double rating = myRaters.get(i).getRating(id);
			if (rating != -1)
			{
				sum += rating;
				count += 1;
			}			
		}
		if (count >= minimalRaters)
		{
			average = sum / count;
		}				
		return average;
	}

	public double getAverageByTitle(String title)
	{
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		String id = null;
		for (String movieid : movies)
		{
			if (MovieDatabase.getTitle(movieid).equals(title))
			{
				id = movieid;
				break;
			}
		}
		double avg = getAverageByID(id, 0);
		return avg;
	}
	
	public ArrayList<Rating> getAverageRatings(int minimalRaters)
	{
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		for (int i = 0; i < movies.size(); i++)
		{
			double avgrating = getAverageByID(movies.get(i), minimalRaters);
			if (avgrating != 0)
			{
				Rating temp = new Rating(movies.get(i), avgrating);
				ratings.add(temp);
			}
		}		
		return ratings;		
	}
	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter fitlerCriteria)
	{
		ArrayList<String> movies = MovieDatabase.filterBy(fitlerCriteria);
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		for (int i = 0; i < movies.size(); i++)
		{
			double avgrating = getAverageByID(movies.get(i), minimalRaters);
			if (avgrating != 0)
			{
				Rating temp = new Rating(movies.get(i), avgrating);
				ratings.add(temp);
			}
		}		
		return ratings;			
	}
	
	private double dotProduct(Rater me, Rater r)
	{
		
		double product = 0.0;
		ArrayList<String> myMovies = me.getItemsRated();
		for (String movie: myMovies)
		{
			if (r.hasRating(movie))
			{
				// Given ratings should be between 0 to 10
				double myRating = me.getRating(movie);
				double rRating = r.getRating(movie);
				myRating -= 5;
				rRating -= 5;
				product += myRating * rRating;
			}
		}
		return product;
	}
	private ArrayList<Rating> getSimilarities(String id)
	{
		ArrayList<Rater> raters = RaterDatabase.getRaters();
		ArrayList<Rating> similarRatings = new ArrayList<Rating>();
		for (Rater rater : raters)
		{
			if (!rater.getID().equals(id))
			{
				double product = dotProduct(RaterDatabase.getRater(id), rater);
				if (product > 0)
				{
					Rating temp = new Rating(rater.getID(), product);
					similarRatings.add(temp);	
				}							
			}
		}
		Collections.sort(similarRatings,Collections.reverseOrder());
		return similarRatings;
	}
	public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters)
	{
		ArrayList<Rating> similarRatings = getSimilarities(id);
		ArrayList<Rating> recomRatings = new ArrayList<Rating>();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		
	    for (String movie : movies)
	    {
	    	int count = 0;
	    	double weightedRating = 0;
	    	for (int k = 0; k < numSimilarRaters; k++)
	    	{
	    		Rating rating = similarRatings.get(k);
	    		String raterID = rating.getItem();
	    		
	    		double similarity = rating.getValue();
	    		Rater rater = RaterDatabase.getRater(raterID);
	    		if (rater.getItemsRated().contains(movie))
	    		{
	    			count++;
	    			weightedRating += similarity * rater.getRating(movie);
	    		}
	    		
	    	}
	    	if (count >= minimalRaters)
			{
				weightedRating /= count;
				recomRatings.add(new Rating(movie, weightedRating));
			}			
	    }
		Collections.sort(recomRatings, Collections.reverseOrder());
		return recomRatings;		
	}
	
	public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria)
	{
		ArrayList<Rating> similarRatings = getSimilarities(id);
		ArrayList<Rating> recomRatings = new ArrayList<Rating>();
		ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
		
	    for (String movie : movies)
	    {
	    	int count = 0;
	    	double weightedRating = 0;
	    	for (int k = 0; k < numSimilarRaters; k++)
	    	{
	    		Rating rating = similarRatings.get(k);
	    		String raterID = rating.getItem();
	    		
	    		double similarity = rating.getValue();
	    		Rater rater = RaterDatabase.getRater(raterID);
	    		if (rater.getItemsRated().contains(movie))
	    		{
	    			count++;
	    			weightedRating += similarity * rater.getRating(movie);
	    		}
	    		
	    	}
	    	if (count >= minimalRaters)
			{
				weightedRating /= count;
				recomRatings.add(new Rating(movie, weightedRating));
			}			
	    }
		Collections.sort(recomRatings, Collections.reverseOrder());
		return recomRatings;
	}
}
