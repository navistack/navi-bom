package org.navistack.framework.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeoDivision {
    private String code;
    private String name;
    private String type;
    private String parent;

    public static GeoDivision of(String code, String name, String type) {
        return of(code, name, type, null);
    }

    public static GeoDivision of(String code, String name, String type, String parent) {
        return new GeoDivision(code, name, type, parent);
    }
}
