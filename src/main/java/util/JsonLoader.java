package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Formula;
import java.io.InputStreamReader;
import java.util.List;

public class JsonLoader {
    public static List<Formula> loadFormulas() {
        InputStreamReader reader = new InputStreamReader(JsonLoader.class.getResourceAsStream("/formulas.json"));
        return new Gson().fromJson(reader, new TypeToken<List<Formula>>() {}.getType());
    }
}
