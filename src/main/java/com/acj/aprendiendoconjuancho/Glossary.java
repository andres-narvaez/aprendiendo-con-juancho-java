package com.acj.aprendiendoconjuancho;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import com.google.gson.Gson;


/***
 Glossary test = Glossary.getInstance();

 for (WordDTO el : test.getCategoryGlossary(Categories.FOOD)) {
  System.out.println(el);
 }
 **/

public final class Glossary {
    private static final String basePath = new File("").getAbsolutePath();
    private final HashMap<Categories, WordDTO[]> glossary = new HashMap<>();
    private static Glossary instance;

    private Glossary() throws FileNotFoundException {
        loadAllGlossary();
    }

    public static Glossary getInstance() throws FileNotFoundException {
        return instance == null ? new Glossary() : instance;
    }

    public WordDTO[] getCategoryGlossary(Categories category) {
        return this.glossary.get(category);
    }

    public WordDTO[] getCategoryGlossaryByLevel(Categories category, Difficulty difficulty) {
        WordDTO[] glossary = getCategoryGlossary(category);

        return Arrays.stream(glossary).filter(word -> word.getDifficulty() == difficulty).toArray(WordDTO[]::new);
    }

    private void loadAllGlossary() throws FileNotFoundException {
        for(Categories ctg: Categories.values()) {
            this.glossary.put(ctg, loadGlossary(ctg));
        }
    }

    private WordDTO[] loadGlossary(Categories category) throws FileNotFoundException {
        String path = getJsonPath(category);
        BufferedReader reader = new BufferedReader(new FileReader(path));

        return new Gson().fromJson(reader, WordDTO[].class);
    }

    private String getJsonPath(Categories category) {

        return switch (category) {
            case ANIMALS -> basePath + "/src/main/resources/jsondb/animals.json";
            case CLOTHES -> basePath + "/src/main/resources/jsondb/clothes.json";
            case FOOD -> basePath + "/src/main/resources/jsondb/food.json";
            case NUMBERS -> basePath + "/src/main/resources/jsondb/numbers.json";
        };
    }

}
