import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class Interest {
    private String interestID;
    private String category;
    private String name;
    private String description;
    private int numberOfUsers; 
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdated;

    public enum Category {
        SPORTS, MUSIC, ART, TECHNOLOGY, GAMING, HIKING, COOKING, CODING, OTHER,
        ENTERTAINMENT, LIFESTYLE, OUTDOOR, TECH
    }
    

    public Interest(String interestID, String category, String name, String description) {
        if (interestID == null || interestID.isEmpty()) {
            throw new IllegalArgumentException("interest ID can't be null or empty");
        }
        if (category == null || !isValidCategory(category)) {
            throw new IllegalArgumentException("Invalid category. Valid categories are: " + Arrays.toString(Category.values()));
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name can't be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("description can't be null or empty");
        }

        this.interestID = interestID;
        this.category = category;
        this.name = name;
        this.description = description;
        this.numberOfUsers = 0;
        this.createdDate = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
    }

    private boolean isValidCategory(String category) {
        for (Category c : Category.values()) {
            if (c.name().equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }
    // check whether the category is valid or nah

    // ---- Getters & Setters ----
    public String getInterestID() {
        return interestID;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    // ---- ADT operations ----

    // add user to interest
    public void addUserToInterest() {
        if (numberOfUsers < 0) {
            throw new IllegalStateException("number of users can't be negative");
        }
        numberOfUsers++;
        lastUpdated = LocalDateTime.now();
    }

    // remove user from interest
    public void removeUserFromInterest() {
        if (numberOfUsers <= 0) {
            throw new IllegalStateException("number of users can't be less than zero");
        }
        numberOfUsers--;
        lastUpdated = LocalDateTime.now();
    }

    // update interest
    public void updateInterest(String newName, String newDesc) {
        if (newName != null && !newName.isEmpty()) {
            this.name = newName;
        }
        if (newDesc != null && !newDesc.isEmpty()) {
            this.description = newDesc;
        }
        this.lastUpdated = LocalDateTime.now();
    }

    // check if interest is active/valid
    public boolean isActive() {
        return numberOfUsers > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interest interest = (Interest) o;
        return interestID.equals(interest.interestID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interestID);
    }

    @Override
    public String toString() {
        return "Interest [" +
               "ID='" + interestID +
               ", category='" + category +
               ", name='" + name +
               ", #users=" + numberOfUsers +
               ']';
    } // return string presenting the interest, including ID, category, name, and number of users
}