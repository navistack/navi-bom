package org.navistack.framework.sms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class SimpleShortMessageTemplate implements ShortMessageTemplate {
    private String code;
    private String message;
}
