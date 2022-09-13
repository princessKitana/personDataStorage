package com.pds.core.service.log;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LogRequest {

    private String logData;

    private LocalDateTime timestamp;

    private String name;
}
