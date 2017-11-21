# Database Design :books:

## Overview of Datatypes:

| Generic Type        | Details          | Yelp-Specific Subtype | Details
| ------------- |-------------| ----- | --- |
| MP5Db      |<ul><li>the given interface</li><li>will add additional database functions</li></ul> | YelpDB | <ul><li>includes a constructor which takes 3 file names containing users, restaurants, and reviews </li><li>creates objects of types YelpUser, Restaurant and YelpReview from the files</li><li>has 3 private fields which are hashmaps (the first one maps user IDs to YelpUsers, the second maps restaurant IDs to Restaurants, and the third maps review IDs to YelpReviews) </li></ul>   |
| User     |  <ul><li>an abstract class</li><li> contains the following fields: String url, int reviewCount, String id, protected double averageStars, reviewList</li></ul>  | YelpUser|  <ul><li>constructor uses a json parser</li><ul>|
| Business |   <ul><li>an abstract class</li><li> contains the following fields: String url, String id, String name, String[] categories, double stars, int numberOfReviews, String photoUrl, double price </li> </ul> |    Restaurant |  <ul><li>constructor uses a json parser</li><ul>    |
| Review | <ul><li>an abstract class</li><li> contains the following fields: Product product, String text, double stars, User user, Date date</li> </ul> | YelpReview| <ul><li>bloop</li></ul>|


## Potential Additional Database Functions:
- Adding/removing individual objects from the database
- Returning all the objects of the same generic type (e.g. a list of all the users)
- Sorting objects based on one of their parameters (e.g. a list of restaurants from highest to lowest average ratings)

## Remaining Questions:
- For MP5Db, what exactly does the T represent - should we have a generic database object that users, restaurants, reviews all relate to in some way?
- For the function, is the T still the same T as the parameter for MP5Db? Since only restaurants in our Yelp database make sense as this T.


#### Thanks for reading over our proposed design! :octocat:
