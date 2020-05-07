package com.book.domain;

public class Authority {
    private String name;
    private int level;
    private int lend_max;

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLend_max(int lend_max) {
        this.lend_max = lend_max;
    }
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getLend_max() {
        return lend_max;
    }


}
