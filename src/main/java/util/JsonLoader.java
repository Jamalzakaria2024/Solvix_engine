// -----------------------------------------------------
// Mini Project: 5
// Question 1 : Load formulas from a JSON file in the resources folder.
// Question 2 : Convert JSON data into List<Formula> using Gson library.
// Question 3 : Handle file reading errors gracefully using try-catch.
// Written by: Jamal Mwaffak Zakaria (ID: 2023804002)
// Written by: Wesam Al-Omari (ID: 2023801020)
// -----------------------------------------------------


package util; 

import com.google.gson.Gson; // import Gson for JSON parsing
import com.google.gson.reflect.TypeToken; //  to define complex generic types like List<Formula>
import model.Formula; // import the Formula class that represents a formulas structure
import java.io.InputStreamReader; // Reader for reading the JSON file from resources
import java.util.List; // Import list to store a list of Formula objects

public class JsonLoader { 

    public static List<Formula> loadFormulas() { // Static method to be used anywhere without creating an object
        try {
            // Read the JSON file (formulas.json) from the resources directory
            InputStreamReader reader = new InputStreamReader(
                    JsonLoader.class.getResourceAsStream("/formulas.json")
            );
            
            // Use Gson to convert JSON data into a List of Formula objects
            return new Gson().fromJson(reader, new TypeToken<List<Formula>>() {}.getType());

        } catch (Exception e) {
            // If any error occurs file not found, parsing error, print the error message
            System.out.println("Error loading formulas: " + e.getMessage());

            // Return an empty list to avoid crashing the app
            return List.of();
        }
    }
}
