package code.service;

import code.model.Lesson;

import java.util.List;

public abstract class JsonService {

    public abstract String getJsonFromList(List<Lesson> chromosome);

    public abstract String getJsonText();

    public abstract List<Lesson> getListFromJson(String jsonText);

    public abstract void saveJsonFile(String json);

}
