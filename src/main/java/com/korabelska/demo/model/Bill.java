package com.korabelska.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Bill {

    @Id
    private Long id;
    private Long amount;
    private String patientName;
    private String doctorName;

}
