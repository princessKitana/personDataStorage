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
    BR_3002("Invalid person number"),
    BR_3003("Invalid birth date"),
    BR_3004("Invalid sorting field"),

    BR_9999("Unknown");

    private String message;

}
