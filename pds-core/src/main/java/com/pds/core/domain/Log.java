package com.pds.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "log")
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    @Id
    @Column(name = "log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "log_data")
    private String logData;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

}
