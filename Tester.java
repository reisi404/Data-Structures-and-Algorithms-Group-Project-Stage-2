import java.util.List;

// tester class to test the Interest Based Social Network with some sample data

public class Tester {
    public static void main(String[] args) {
        System.out.println(
                " _____      _                     _          _                        _   _____            _       _   _    _       _                       _");
        System.out.println(
                "|_   _|    | |                   | |        | |                      | | /  ___|          (_)     | | | \\ | |    | |                    | |   ");
        System.out.println(
                "  | | _ __ | |_ ___ _ __ ___  ___| |_ ______| |__   __ _ ___  ___  __| | \\ `--.  ___   ___ _  __ _| | |  \\| | ___| |___      _____  _ __| | __");
        System.out.println(
                "  | || '_ \\| __/ _ \\ '__/ _ \\/ __| __|______| '_ \\ / _` / __|/ _ \\/ _` |  `--. \\/ _ \\ / __| |/ _` | | | . ` |/ _ \\ __\\ \\ /\\ / / _ \\| '__| |/ /");
        System.out.println(
                " _| || | | | ||  __/ | |  __/\\__ \\ |_       | |_) | (_| \\__ \\  __/ (_| | /\\__/ / (_) | (__| | (_| | | | |\\  |  __/ |_ \\ V  V / (_) | |  |   < ");
        System.out.println(
                " \\___/_| |_|\\__\\___|_|  \\___||___/\\__|      |_.__/ \\__,_|___/\\___|\\__,_| \\____/ \\___/ \\___|_|\\__,_|_| \\_| \\_/\\___|\\__| \\_/\\_/ \\___/|_|  |_|\\_\\");

        System.out.println("=====================================");

        System.out.println("=== Interest-Based Social Network Tester ===\n");

        System.out.println("=== Creating a Social Network ===");

        // 1) create a Social Network
        SocialNetwork network = new SocialNetwork();

        // 2) create some Interests
        Interest gaming = network.createInterest("I001", "GAMING", "Gaming", "All about video games.");
        Interest cooking = network.createInterest("I002", "COOKING", "Cooking", "All about cooking recipes.");
        Interest hiking = network.createInterest("I003", "HIKING", "Hiking", "Mountain hiking fans.");
        Interest coding = network.createInterest("I004", "CODING", "Coding", "Programming and coding.");

        // 3) create some Users
        User george = network.createUser("George", "george@mail.com", "pass123", 20, "USA");
        User reis = network.createUser("Reis", "reis@mail.com", "123pass", 24, "Italy");
        User jim = network.createUser("Jim", "jim@mail.com", "thisisnotmypass", 48, "Australia");
        User dave = network.createUser("Dave", "dave@mail.com", "davepass", 30, "Canada");

        // 4) add Interests to these users
        george.addInterest(gaming);
        george.addInterest(hiking);

        reis.addInterest(gaming);
        reis.addInterest(cooking);

        jim.addInterest(cooking);
        jim.addInterest(hiking);

        dave.addInterest(coding);
        dave.addInterest(hiking);

        // 5) display user profiles
        System.out.println("\n--- User profiles after interest addition ---");
        george.displayProfile();
        reis.displayProfile();
        jim.displayProfile();
        dave.displayProfile();

        // 6) follow/unfollow test
        System.out.println("\n--- George follows Reis ---");
        reis.followUser(george);
        reis.displayProfile(); // see "following" for Reis and "followers" for George
        george.displayProfile();

        System.out.println("\n--- George unfollows Reis ---");
        reis.unfollowUser(george);
        reis.displayProfile();
        george.displayProfile();

        // 7) create Activities for George
        Activity a1 = network.createActivity("A001", george.getUserID(), "like");
        Activity a2 = network.createActivity("A002", george.getUserID(), "share");

        // add these activities to George
        george.addActivity(a1);
        george.addActivity(a2);

        System.out.println("\n--- Display George profile after adding Activities ---");
        george.displayProfile();

        // 8) remove a user (Jim) from the network
        System.out.println("\n--- Removing Jim from the network :( ---");
        network.removeUser(jim);
        jim.displayProfile(); // will show isActive=false + empty lists

        // 9) friend recommendation for George
        System.out.println("\n--- Friend Recommendations for George ---");
        List<User> friendRecommendationsForGeorge = network.recommendFriends(george);
        System.out.println("Recommended: " + friendRecommendationsForGeorge);

        // the system will recommend Reis to George since they share an interest
        // (gaming) and have age difference <= 5

        // 10) test performance using 500000 users

        System.out.println("\n--- Large Insert of Users - 500000 ---");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            network.createUser("User" + i, "user" + i + "@testtime.com", "pass123" + i, 20 + (i % 10), "somewhere");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Inserted 500000 users in: " + (endTime - startTime) + " ms");

        // 11) performance testing for user search
        System.out.println("\n--- User Search - 100000 ---");
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            List<User> users = network.getAllUsers();
            users.get(i % users.size()); // directly get user without storing
        }
        endTime = System.currentTimeMillis();
        System.out.println("Performed 100000 user searches in: " + (endTime - startTime) + " ms");

        // 12) performance testing for friend recommendations
        System.out.println("\n--- Friend Recommendations ---");
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            List<User> users = network.getAllUsers();
            User randomUser = users.get(i % users.size());
            network.recommendFriends(randomUser);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Generated recommendations for 100 users in: " + (endTime - startTime) + " ms");

        // 13) performance testing for interest operations
        System.out.println("\n--- Interest Operations ---");
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 500; i++) {
            Interest interest = network.createInterest("SOMETHING" + i, "OTHER", "Interest" + i, "Description");
            User randomUser = network.getAllUsers().get(i % network.getAllUsers().size());
            randomUser.addInterest(interest);
        }
        endTime = System.currentTimeMillis();

        System.out.println("Created and assigned 500 interests in: " + (endTime - startTime) + " ms");

        // 14) test duplicate interest addition
        System.out.println("\n--- Duplicate Interest Addition ---");
        george.addInterest(gaming); // trying to add gaming again
        george.displayProfile(); // should show gaming only once

        // 15) test complete user removal
        System.out.println("\n--- Complete User Removal and Cleanup ---");
        User tempUser = network.createUser("cleanup", "cleanup@mail.com", "cleanup1", 25, "GR");
        tempUser.addInterest(coding);
        tempUser.followUser(george);
        System.out.println("Before removal:");
        tempUser.displayProfile();
        george.displayProfile();
        network.removeUser(tempUser);
        System.out.println("After removal:");
        george.displayProfile();
        tempUser.displayProfile(); // should show isActive=false

        // 16) friend recommendations with shared interests
        System.out.println("\n--- Friend Recommendations with Shared Interests ---");
        User testUser = network.createUser("TestUser", "test@mail.com", "testpass", 22, "USA");
        testUser.addInterest(gaming);
        testUser.addInterest(hiking);
        testUser.addInterest(cooking);
        testUser.addInterest(gaming); // duplicate interest for test
        List<User> recommendations = network.recommendFriends(testUser);
        System.out.println("Recommendations for test user: " + testUser + ": " + recommendations.size() + " users ->");
        System.out.println(recommendations);

        System.out.println("\n=====================================");
        System.out.println(" ---+== TXT FILE TESTS ==+--- ");
        System.out.println("=====================================");

        // 17) creat social_data.txt file and generating random users
        System.out.println("\n--- Creating social_data.txt file and generating random users ---");
        startTime = System.currentTimeMillis();
        // make the file
        DataFile.main(null);
        // generate random users
        DataFile.generateRandomUsers();
        endTime = System.currentTimeMillis();

        System.out.println("Generated random users in: " + (endTime - startTime) + " ms");

        // 18) search user from 25 to 35
        System.out.println("\n--- Searching users from 25 to 35 years old in USA interested in Gaming ---");
        DataFile.searchUsers();
        




        System.out.println("=== End of Tester ===");
        System.out.println("Thank you for using our Social Network!");
        System.out.println("=====================================");
    }
}
