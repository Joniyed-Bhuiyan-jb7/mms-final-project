package io.swagger.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Evaluation {
    @Id
    private long evaluationNo;
    private double ablity;
    private double foodQuality;
    private double relationWithMember;
    private String username;
    private String managerUserName;
    private LocalDate date;
}

