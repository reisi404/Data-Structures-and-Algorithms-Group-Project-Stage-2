import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Activity {
    private String activityID;
    private String userID; 
    private String type; // e.g. comment, like, share, custom
    private LocalDateTime timestamp;
    private String status; // e.g. Active, Archived
    private List<String> relatedActivities;
    private int interactionCount;
    private String visibility;

    public Activity(String activityID, String userID, String type) {
        if (activityID == null || activityID.isEmpty()) {
            throw new IllegalArgumentException("activityID can't be null or empty");
        }
        if (userID == null || userID.isEmpty()) {
            throw new IllegalArgumentException("userID can't be null or empty");
        }
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type can't be null or empty");
        }

        this.activityID = activityID;
        this.userID = userID;
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.status = "Active";
        this.relatedActivities = new ArrayList<>();
        this.interactionCount = 0;
        this.visibility = "Public";
    }

    private static final String STATUS_ACTIVE = "Active";
    private static final String STATUS_ARCHIVED = "Archived";
    private static final String VISIBILITY_PUBLIC = "Public";
    private static final String VISIBILITY_PRIVATE = "Private";

    public void addRelatedActivity(String activityID) {
        if (activityID == null || activityID.isEmpty()) {
            throw new IllegalArgumentException("activityID can't be null or empty");
        }
        if (!this.relatedActivities.contains(activityID)) {
            relatedActivities.add(activityID);
        }
    }

    public void removeRelatedActivity(String activityID) {
        if (activityID == null || activityID.isEmpty()) {
            throw new IllegalArgumentException("activityID can't be null or empty");
        }
        if (this.relatedActivities.contains(activityID)) {
            relatedActivities.remove(activityID);
        }
    }

    public void setVisibility(String visibility) {
        if (visibility.equals("Public") || visibility.equals("Private")) {
            this.visibility = visibility;
        } else {
            throw new IllegalArgumentException("visibility must be either 'Public' or 'Private'");
        }
    }

    public List<String> getRelatedActivities() {
        return new ArrayList<>(relatedActivities);
    }

    public int getInteractionCount() {
        return interactionCount;
    }

    public String getVisibility() {
        return visibility;
    }

    public void incrementInteractionCount() {
        this.interactionCount++;
    }

    // ----- Getters & Setters ----
    public String getActivityID() {
        return activityID;
    }

    public String getUserID() {
        return userID;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

    // ----- ADT Operations ----

    public void deleteActivity() {
        this.status = "Archived";
    }

    public void updateActivityStatus(String newStatus) {
        if (newStatus.equals("Active") || newStatus.equals("Archived")) {
            this.status = newStatus;
        } else {
            throw new IllegalArgumentException("status must be either 'Active' or 'Archived'");
        }
    }

    @Override
    public String toString() {
        return "\nActivity [" +
           "activityID='" + activityID +
           ", userID='" + userID +
           ", type='" + type +
           ", status='" + status +
           ", timestamp=" + timestamp +
           ", visibility='" + visibility +
           ", interactionCount=" + interactionCount +
           ", relatedActivities=" + relatedActivities +
           ']';
    }
}