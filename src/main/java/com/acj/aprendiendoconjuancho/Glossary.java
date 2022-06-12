package com.acj.aprendiendoconjuancho;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import com.google.gson.Gson;


/***
 Glossary test = new Glossary();

 WordDTO[] gl = test.getGlossary(Categories.CLOTHES);

 for (WordDTO el: gl ) {
   System.out.println(el);
 }
 **/

public class Glossary {
    private static final String basePath = new File("").getAbsolutePath();
    private final HashMap<Categories, WordDTO[]> glossary = new HashMap<>();

    public Glossary() throws FileNotFoundException {
        loadAllGlossary();
    }

    public WordDTO[] getGlossary(Categories category) {
        return this.glossary.get(category);
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
