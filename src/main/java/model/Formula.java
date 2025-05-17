// -----------------------------------------------------
// Mini Project: 7
// Question 1 : Define a data model for mathematical and scientific formulas.
// Question 2 : Represent all attributes needed for dynamic evaluation.
// Written by: Jamal Mwaffak Zakaria (ID: 2023804002)
// -----------------------------------------------------

package model; 

import java.util.List; // Used for parameters list
import java.util.Map;  // Used for constants map

public class Formula { // This class defines a data model for a formula

    public String id;           // Unique identifier for the formula
    public String name;         // Display name ("Ohm's Law")
    public List<String> parameters; // List of input variable names (voltage, resistance)
    public Map<String, Double> constants; // Optional constants used in the formula ( pi = 3.14)
    public String formula;      // Raw formula string used for evaluation ( "V / R")
    public String category;     // Category of the formula (Physics, Math)
    public String description;  // Short description of what the formula does
    public String unit;         // Unit of the result ("A" for amps)
    public String equation;     // Display equation ("I = V / R")
}
