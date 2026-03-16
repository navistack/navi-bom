package org.navistack.smoketest.security.web.rest.vm;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Echo {
    private String content;
}
