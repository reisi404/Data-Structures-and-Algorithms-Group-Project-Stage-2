import java.util.ArrayList;
import java.util.List;

// class to manage the social network system (users, interests, activities)

public class SocialNetwork {

    private List<User> allUsers;
    private List<Interest> allInterests;
    private List<Activity> allActivities;

    public SocialNetwork() {
        allUsers = new ArrayList<>();
        allInterests = new ArrayList<>();
        allActivities = new ArrayList<>();
    }

    // -- user management --
    public User createUser(String username, String email, String password, int age, String location) {
        if (username == null || email == null || password == null || location == null) {
            throw new IllegalArgumentException("parameters can't be null");
        }
        if (age < 5) { // what is the minimum age for a user?
            throw new IllegalArgumentException("the age is not valid");
        }
        User newUser = new User(username, email, password, age, location);
        allUsers.add(newUser);
        return newUser;
    }

    public void removeUser(User user) {
        user.removeUser();
        allUsers.remove(user);
        // remove user from allUsers list
    }

    // -- interest management --
    public Interest createInterest(String interestID, String category, String name, String desc) {
        if (interestID == null || category == null || name == null) {
            throw new IllegalArgumentException("parameters can't be null");
        }
        for (Interest interest : allInterests) {
            if (interest.getInterestID().equals(interestID)) {
                throw new IllegalArgumentException("interestID must be unique");
            }
        }
        Interest interest = new Interest(interestID, category, name, desc);
        allInterests.add(interest);
        return interest;
    }

    // -- activity management --
    public Activity createActivity(String activityID, String userID, String type) {
        Activity activity = new Activity(activityID, userID, type);
        allActivities.add(activity);
        return activity;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }
    public List<Interest> getAllInterests() {
        return allInterests;
    }
    public List<Activity> getAllActivities() {
        return allActivities;
    }

    /**
     * recommend friends based on shared interests and age difference
     * example: if user A has interests {gaming, hiking} and user B has {gaming, cooking},
     * then user B will be recommended to user A.
     * NOTE: this is a simple recommendation system and can be improved  
     */

    public List<User> recommendFriends(User user) {
        List<User> recommendations = new ArrayList<>();
        // for each user in the system (except the user itself)
        for (User potentialFriend : allUsers) {
            if (potentialFriend == user) continue;
            if (!potentialFriend.isActive()) continue;
            if (userAlreadyFollows(user, potentialFriend)) continue;
            // check age difference
            if (Math.abs(user.getAge() - potentialFriend.getAge()) > 5) {
                continue;
            }
            // check shared interests
            if (sharedInterest(user, potentialFriend)) {
                recommendations.add(potentialFriend);
            }
        }
        return recommendations; // gimme the recommendations
    }

    /**
     * check if user u1 already follows user u2
     * example: u1 follows u2 if u2 is in u1's following list
     */
    
    private boolean userAlreadyFollows(User u1, User u2) {
        return u1.toString().equals(u2.toString());
    }  //double check this

    private boolean sharedInterest(User u1, User u2) {
        // check all interests in u1 to see if they exist in u2
        for (int i = 0; i < u1.getInterestCount(); i++) {
            Interest i1 = u1.getInterestAt(i);
            if (u2.hasInterest(i1)) {
                return true;
            }
        }
        return false;
    }
}