# A DIY Movie Recommender System with Collaborative Filtering
The project is a DIY movie recommender system by using **collaborative filtering**. It used **hashmap** to store data so as to make search more efficient. And several **filters** are designed so as to meet user's specific requirements for movies.

---
```
MovieRunnerAverage
```

Recommend movies based on the average scores from the past movie database rating data.

---
```
MovieRunnerWithFilters
```

Recommend movies based on the average scores from the past movie database rating data. Plus, the movies can be filted by given Filters. For example, Filters can be year filter (e.g. after 2001), genre filter (e.g. "Comedy"), etc. 

---
```
MovieRunnerSimilarRatings
```

Recommend movies based on weighted average scores from the past movie database rating data and rater's personal rating data. This personalized recommendation is achieved by using collaborative filtering. Also, the movies recommended can be filtered according to user's preference.
