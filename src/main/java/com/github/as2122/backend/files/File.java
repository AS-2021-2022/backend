package com.github.as2122.backend.files;

public class File {
    private final String name;
    private final String id;

    public File(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
