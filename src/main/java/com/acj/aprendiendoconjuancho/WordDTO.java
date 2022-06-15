package com.acj.aprendiendoconjuancho;

/**
 * Converts json object in DTO for Java
 *
 * {
 *     "value": "Schwein",
 *     "difficulty": "LOW",
 *     "imagePath": "/assets/words/images/animals/cerdo.png",
 *     "audioPath": "/assets/words/audio/animals/cerdo.mp3",
 *     "category": "ANIMALS",
 *     "language": "DEU"
 * }
 */
public class WordDTO {
    public int id;
    public String value;
    public Difficulty difficulty;
    public String imagePath;
    public String audioPath;
    public Categories category;
    public String language;

    /**
     * @param id int identifier of the word
     * @param value string is the word itself
     * @param difficulty could be LOW, MEDIUM, HIGH
     * @param imagePath string with the path of the resource
     * @param audioPath string with the path of the resource
     * @param category could be ANIMALS, CLOTHES, FOOD, NUMBERS
     * @param language string with the code of the language
     */
    public WordDTO(int id, String value, Difficulty difficulty, String imagePath, String audioPath, Categories category, String language) {
        this.id = id;
        this.value = value;
        this.difficulty = difficulty;
        this.imagePath = imagePath;
        this.audioPath = audioPath;
        this.category = category;
        this.language = language;
    }

    /**
     * @return int identifier of the word
     */
    public int getId() {
        return id;
    }

    /**
     * @param id set identifier of the word
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return string is the word itself
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value string set the word value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return could be LOW, MEDIUM, HIGH
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * @param difficulty set the difficulty could be LOW, MEDIUM, HIGH
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return string the path of the resource
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath string set the path of the resource
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return could be ANIMALS, CLOTHES, FOOD, NUMBERS
     */
    public Categories getCategory() {
        return category;
    }

    /**
     * @param category set the category could be ANIMALS, CLOTHES, FOOD, NUMBERS
     */
    public void setCategory(Categories category) {
        this.category = category;
    }

    /**
     * @return string with the code of the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language string set the code of the language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return string parsed to be safety stored in a Json file
     */
    @Override
    public String toString() {
        return "id=" + id + ", value=" + value + ", difficulty="
                + difficulty + ", category=" + category + ", language=" + language;
    }
}
