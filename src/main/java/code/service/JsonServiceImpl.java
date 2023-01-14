package code.service;

import code.model.Lesson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class JsonServiceImpl implements JsonService {

    @Override
    public List<Lesson> getListFromJson(String jsonText) {
        return new Gson().fromJson(jsonText, new TypeToken<List<Lesson>>() {
        }.getType());
    }

    public String getJsonFromList(List<Lesson> chromosome) {
        return new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(chromosome);

    }

    public String getJsonText() {
        String text = "";
        try {
            Class<?> forName = Class.forName("code.AlgorithmRunner");
            InputStream stream = forName.getResourceAsStream("/input.json");

            if (stream != null)
                text = new BufferedReader(
                        new InputStreamReader(stream, StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining("\n"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
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
