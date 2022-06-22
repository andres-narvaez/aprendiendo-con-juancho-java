package com.acj.aprendiendoconjuancho;

public final class Category {
    private static Categories category;

    public Categories getCategory() {
        return category;
    }

    public static void setCategory(Categories cat) {
        category = cat;
    }
}
