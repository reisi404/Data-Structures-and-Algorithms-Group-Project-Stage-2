import java.util.UUID;


public class User {
    private String userID;
    private String username;
    private String email;
    private String password;
    private int age;
    private String location;

    // custom linked list to store user's interests, activities, followers, et
    private MyLinkedList<Interest> interestList; 
    private MyLinkedList<Activity> activityList;
    private MyLinkedList<User> followingList;  // whom I follow
    private MyLinkedList<User> followerList;   // who follows me

    // 
    private boolean isActive;

    public User(String username, String email, String password, int age, String location) {
        this.userID    = UUID.randomUUID().toString(); // pseudo randomly generated
        this.username  = username;
        this.email     = email;
        this.password  = password;
        this.age       = age;
        this.location  = location;
        this.isActive  = true;

        this.interestList  = new MyLinkedList<>();
        this.activityList  = new MyLinkedList<>();
        this.followingList = new MyLinkedList<>();
        this.followerList  = new MyLinkedList<>();
    }

    // ---- getters ----
    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public String getLocation() {
        return location;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return isActive;
    }

    // ---- ADT operations ----

    /**
     * removeUser -> sets user as inactive & clears lists
     */
    public void removeUser() {
        this.isActive = false;
        this.followingList = new MyLinkedList<>();
        this.followerList = new MyLinkedList<>();
        this.interestList = new MyLinkedList<>();
        this.activityList = new MyLinkedList<>();
    }

    
    // updateProfileInfo -> example of changing location/age
    public void updateProfileInfo(int newAge, String newLocation) {
        this.age = newAge;
        this.location = newLocation;
    }

    
    // add interest to user's interest list
    public void addInterest(Interest interest) {
        // In a real system: check if interest is valid, etc.
        if (!interestList.contains(interest)) {
            interestList.add(interest);
            // Also increment the interest's user count
            interest.addUserToInterest();
        }
    }

    // remove interest from user's interest list 
    public void removeInterest(Interest interest) {
        if (interestList.remove(interest)) {
            // Decrement interest's user count
            interest.removeUserFromInterest();
        }
    }

     // follow another user
    public void followUser(User userToFollow) {
        // Make sure not already following
        if (!this.followingList.contains(userToFollow)) {
            this.followingList.add(userToFollow);
            // We might also update the "followerList" on the other side
            userToFollow.addFollower(this);
        }
    }

    
     // unfollow another user
    public void unfollowUser(User userToUnfollow) {
        if (this.followingList.remove(userToUnfollow)) {
            userToUnfollow.removeFollower(this);
        }
    }

    
     // add an activity to user's activityList
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    
     // remove an activity from user's activityList
    
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    // keep followerList good. in sync with followingList
    private void addFollower(User user) {
        if (!this.followerList.contains(user)) {
            this.followerList.add(user);
        }
    }

    private void removeFollower(User user) {
        this.followerList.remove(user);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ---- interest operations ----
public boolean hasInterest(Interest interest) {
    return interestList.contains(interest);
}

public int getInterestCount() {
    return interestList.size();
}

public Interest getInterestAt(int index) {
    return interestList.get(index);
}


    // ---- display ----
    public void displayProfile() {
        System.out.println("User Profile:");
        System.out.println("UserID: " + userID);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Age: " + age);
        System.out.println("Location: " + location);
        System.out.println("Active: " + isActive);
        System.out.print("Interests: ");
        interestList.printAll();
        System.out.print("Following: ");
        followingList.printAll();
        System.out.print("Followers: ");
        followerList.printAll();
        System.out.print("Activities: ");
        activityList.printAll();
        System.out.println("----");
    }

    @Override
    public String toString() {
        return username + " (" + userID + ")";
    }
}
