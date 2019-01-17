import java.util.ArrayList;

public class SecondRatings {
	private ArrayList<Rater> myRaters;
	private ArrayList<Movie> myMovies;
//	public static void main(String[] args) {
//	
//	
// 
//	}
	public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
	public SecondRatings(String moviefile, String ratingsfile){
		FirstRatings fRatings = new FirstRatings();
		myMovies = fRatings.loadMovies(moviefile);
		myRaters = fRatings.loadRaters(ratingsfile);		
	}
	
	private double getAverageByID(String id, int minimalRaters)
	{
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
		String id = getID(title);
		double avg = getAverageByID(id, 0);
		return avg;
	}
	
	public ArrayList<Rating> getAverageRatings(int minimalRaters)
	{
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		for (int i = 0; i < myMovies.size(); i++)
		{
			double avgrating = getAverageByID(myMovies.get(i).getID(), minimalRaters);
			if (avgrating != 0)
			{
				Rating temp = new Rating(myMovies.get(i).getID(), avgrating);
				ratings.add(temp);
			}
		}		
		return ratings;
		
	}
	public String getID(String title)
	{
		String id = "No such title as " + title;
		for (int i = 0; i < myMovies.size(); i++)
		{
			if (myMovies.get(i).getTitle().equals(title))
			{
				id = myMovies.get(i).getID();
				break;
			}
		}
		return id;
	}
	public String getTitle(String id)
	{
		String title = "Movie with id [" + id + "] is not Found";
		for (int i = 0; i < myMovies.size(); i++)
		{
			if (myMovies.get(i).getID().equals(id))
			{
				title = myMovies.get(i).getTitle();
				break;
			}
		}
		return title;
	}
	
	public int getMovieSize()
	{
		return myMovies.size();
	}
	public int getRaterSize()
	{
		return myRaters.size();
	}

}
