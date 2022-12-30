package code.service;

import code.model.Lesson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class JsonServiceImpl implements JsonService{

    @Override
    public List<Lesson> getListFromJson(String jsonText) {
        return new Gson().fromJson(jsonText, new TypeToken<List<Lesson>>() {
        }.getType());
    }

    public String getJsonFromList(List<Lesson> chromosome) {
        return new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(chromosome);
    }

    public String getJsonText() {
        String string = "";
        try {
            string = Files.readString(Path.of("src/main/resources/input.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    public void saveJsonFile(String json) {
        try {
            String pathString = "src/main/resources/output.json";
            BufferedWriter writer = Files.newBufferedWriter(Path.of(pathString));
            writer.write(json);
            writer.flush();
            writer.close();
            Files.readString(Path.of(pathString));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
