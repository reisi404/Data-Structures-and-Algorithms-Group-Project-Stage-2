
## ADTs:

1. **User**  
2. **Interest**  
3. **Activity**  
4. **SocialNetwork**  

It also includes a **custom singly linked list** (`MyLinkedList`) to meet the requirement of implementing at least one data structure from scratch


## Short Description of Each File

- **Activity.java**  
  Defines user activities (e.g. like, share, comment) with operations to delete or update status.

- **Interest.java**  
  Defines interests (e.g. Gaming, Cooking) and tracks how many users follow each interest, along with methods to add or remove followers.

- **MyLinkedList.java**  
  A minimal custom singly linked list class. Used inside `User` to store interests, activities, followers, etc.

- **SocialNetwork.java**  
  A manager class that handles creating and managing all users, interests, and activities. Also includes a simple friend recommendation method.

- **Tester.java**  
  Contains a `main()` method that hardcodes method calls to demonstrate and test the social network’s functionalities (creating users, following/unfollowing, adding interests, checking friend recommendations, etc).

- **User.java**  
  Represents a user in the network. Stores user information (username, age, location, etc.) and references to interests, activities, followers, and following lists (all via `MyLinkedList`).

## How to Run
1. **Open/Import** the folder into your IDE or compile from the command line.  
2. **Run** the `Tester.java` file.  
3. Done yo
