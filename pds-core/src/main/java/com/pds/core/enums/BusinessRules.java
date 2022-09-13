package com.pds.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessRules {

    /**
     * 1000-1999 Internal
     * 2000-2999 External
     * 3000-3999 Validation
     * 9999 Unknown
     */
    BR_3000("is mandatory"),
    BR_3001("Invalid format; must be numeric in format xxxxxx-xxxxx"),
    BR_3002("Must be less than current date"),
    BR_3003("Invalid format; must be yyyy-mm-dd"),
    BR_3004("Already exists"),

    BR_9999("Unknown");

    private String message;

}
