package com.acj.aprendiendoconjuancho;

//"id": 1,
//"value": "Schwein",
//"difficulty": "LOW",
//"imagePath": "/assets/words/images/animals/cerdo.png",
//"audioPath": "/assets/words/audio/animals/cerdo.mp3",
//"category": "ANIMALS",
//"language": "DEU"

public class WordDTO {
    public int id;
    public String value;
    public Difficulty difficulty;
    public String imagePath;
    public Categories category;
    public String language;

    public WordDTO(int id, String value, Difficulty difficulty, String imagePath, Categories category, String language) {
        this.id = id;
        this.value = value;
        this.difficulty = difficulty;
        this.imagePath = imagePath;
        this.category = category;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "id=" + id + ", value=" + value + ", difficulty="
                + difficulty + ", category=" + category + ", language=" + language;
    }
}
