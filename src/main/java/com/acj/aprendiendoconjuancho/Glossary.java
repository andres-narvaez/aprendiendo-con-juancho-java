package com.acj.aprendiendoconjuancho;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

/**
 * This class loads the json files that contains the words located in the resources' folder.
 * After load the files it converts each word as DTO to be used as a Java Object
 */
public final class Glossary {
    private static final String basePath = new File("").getAbsolutePath();
    private final HashMap<Categories, WordDTO[]> glossary = new HashMap<>();
    private static Glossary instance;

    /**
     *
     * @throws FileNotFoundException in case any file is not found
     */
    private Glossary() throws FileNotFoundException {
        loadAllGlossary();
    }

    /**
     * Following a singleton pattern
     * @return Glossary instance of the glossary
     * @throws FileNotFoundException in case any file is not found
     */
    public static Glossary getInstance() throws FileNotFoundException {
        return instance == null ? new Glossary() : instance;
    }

    /**
     * Glossary filtered by category
     * @param category could be ANIMALS, CLOTHES, FOOD, NUMBERS
     * @return Array of WordDTO
     */
    public WordDTO[] getCategoryGlossary(Categories category) {
        return this.glossary.get(category);
    }

    /**
     * Glossary filtered by category and difficulty
     * @param category could be ANIMALS, CLOTHES, FOOD, NUMBERS
     * @param difficulty Difficulty could be LOW, MEDIUM, HIGH
     * @return Array of WordDTO
     */
    public WordDTO[] getCategoryGlossaryByLevel(Categories category, Difficulty difficulty, int numberOfQuestions) {
        WordDTO[] glossary = getCategoryGlossary(category);
        List<WordDTO> wordList = Arrays.stream(glossary)
                .filter(word -> word.getDifficulty() == difficulty)
                .collect(Collectors.toList());
        Collections.shuffle(wordList);

        return Arrays.stream(wordList.toArray())
                .skip(0)
                .limit(numberOfQuestions)
                .toArray(WordDTO[]::new);
    }

    /**
     * @throws FileNotFoundException in case any file is not found
     */
    private void loadAllGlossary() throws FileNotFoundException {
        for(Categories ctg: Categories.values()) {
            this.glossary.put(ctg, loadGlossary(ctg));
        }
    }

    /**
     * @param category could be ANIMALS, CLOTHES, FOOD, NUMBERS
     * @return Array of WordDTO
     * @throws FileNotFoundException in case any file is not found
     */
    private WordDTO[] loadGlossary(Categories category) throws FileNotFoundException {
        String path = getJsonPath(category);
        BufferedReader reader = new BufferedReader(new FileReader(path));

        return new Gson().fromJson(reader, WordDTO[].class);
    }

    /**
     * @param category could be ANIMALS, CLOTHES, FOOD, NUMBERS
     * @return String with the absolute rute for the category json file
     */
    private String getJsonPath(Categories category) {

        return switch (category) {
            case ANIMALS -> basePath + "/src/main/resources/jsondb/animals.json";
            case CLOTHES -> basePath + "/src/main/resources/jsondb/clothes.json";
            case FOOD -> basePath + "/src/main/resources/jsondb/food.json";
            case NUMBERS -> basePath + "/src/main/resources/jsondb/numbers.json";
        };
    }

}
