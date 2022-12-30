package code.service;

import code.model.Lesson;

import java.util.List;

public interface JsonService {

    String getJsonFromList(List<Lesson> chromosome);

    String getJsonText();

    List<Lesson> getListFromJson(String jsonText);

    void saveJsonFile(String json);

}
