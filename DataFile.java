import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataFile {
    public static void main(String[] args) {
        try {
            File file = new File("social_data.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



    public static void generateRandomUsers() {
        try {
            FileWriter writer = new FileWriter("social_data.txt");
            String[] countries = {"USA", "Canada", "UK", "Australia"};
            String[] interests = {"Gaming", "Cooking", "Hiking", "Reading", "Music", "Travel", "Sports", "Art", "Technology", "Photography"};

            for (int i = 0; i < 20000; i++) {
                String name = "txt" + i;
                String email = "txt" + i + "@txt.com";
                String password = "pass" + i;
                int age = 18 + (int)(Math.random() * 50);
                String country = countries[(int)(Math.random() * countries.length)];
                String interest1 = interests[(int)(Math.random() * interests.length)];
                String interest2 = interests[(int)(Math.random() * interests.length)];
                
                // Format: name,email,password,age,country,interest1,interest2
                writer.write(String.format("%s,%s,%s,%d,%s,%s,%s\n",
                    name, email, password, age, country, interest1, interest2));
            }
            writer.close();
            System.out.println("Successfully generated 20000 random users");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

public static void searchUsers() {
        String filePath = "social_data.txt";
        int minAge = 25;
        int maxAge = 35;
        String targetCountry = "USA";
        String targetInterest = "Gaming";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            long startTime = System.currentTimeMillis();

            System.out.println("\nSearching for users:");
            System.out.println("Age: " + minAge + "-" + maxAge);
            System.out.println("Country: " + targetCountry);
            System.out.println("Interest: " + targetInterest);
            System.out.println("\nResults:");

            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                int age = Integer.parseInt(userData[3]);
                String country = userData[4];
                String interest1 = userData[5];
                String interest2 = userData[6];

                if (age >= minAge && age <= maxAge 
                    && country.equals(targetCountry) 
                    && (interest1.equals(targetInterest) || interest2.equals(targetInterest))) {
                    System.out.println(userData[0] + ", " + age + ", " + country + ", " + interest1 + ", " + interest2);
                    count++;
                }
            }

            long endTime = System.currentTimeMillis();
            System.out.println("\nFound " + count + " matching users");
            System.out.println("Search completed in " + (endTime - startTime) + " ms");
        } catch (IOException e) {
            System.out.println("An error occurred while searching.");
            e.printStackTrace();
        }
    }

}
