package com.github.nachomezzadra.redistest.domain;

import java.io.Serializable;

public class CustomPojo implements Serializable {

    private static final long serialVersionUID = 5747580392766173306L;
    public static final String KEY = "CUSTOM_POJO";

    private Long id;
    private String name;
    private String description;

    public CustomPojo(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomPojo that = (CustomPojo) o;

        if (!id.equals(that.id)) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
