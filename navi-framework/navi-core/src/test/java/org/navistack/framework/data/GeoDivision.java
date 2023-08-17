package org.navistack.framework.data;

import lombok.Data;

@Data
public class GeoDivision {
    private String id;
    private String name;
    private String type;
    private String parentId;

    public GeoDivision(String id, String name, String type, String parentId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.parentId = parentId;
    }

    public GeoDivision(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public static GeoDivision of(String id, String name, String type) {
        return new GeoDivision(id, name, type);
    }

    public static GeoDivision of(String id, String name, String type, String parentId) {
        return new GeoDivision(id, name, type, parentId);
    }
}
