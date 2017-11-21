# Database Design :books:

## Overview of Datatypes:

| Generic Type        | Details          | Yelp-Specific Subtype | Details
| ------------- |-------------| ----- | --- |
| MP5Db      |<ul><li>the given interface</li><li>will add additional database functions</li></ul> | YelpDB | <ul><li>includes a constructor which takes 3 file names containing users, restaurants, and reviews </li><li>creates objects of types YelpUser, Restaurant and YelpReview from the files</li><li>has 3 private fields which are hashmaps (the first one maps user IDs to YelpUsers, the second maps restaurant IDs to Restaurants, and the third maps review IDs to YelpReviews</li></ul>   |
| User     |  <ul><li>the given interface</li></ul>    |   YelpUser | <ul><li>the given interface</li><li>will add additional database functions</li></ul>  |
| Business |   <ul><li>the given interface</li></ul>  |    Restaurant |  <ul><li>the given interface</li><li>will add additional database functions</li></ul>    |
| Review | <ul><li>the given interface</li></ul> | YelpReview| <ul><li>the given interface</li><li>will add additional database functions</li></ul>|


## Potential Additional Database Functions:
- Adding/removing individual objects from the database
- Returning all the objects of the same generic type (e.g. a list of all the users)
- Sorting objects based on one of their parameters (e.g. a list of restaurants from highest to lowest average ratings)


#### Thanks for reading over our proposed design! :octocat:
