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
public class DepositInformation{
    @Id
    private long depositNo;
    private double amount;
    private LocalDate localDate;
    private String username;
}