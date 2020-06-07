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
public class Meal{
    @Id
    private long mealNo;
    private double breakfast;
    private double dinner;
    private double lunch;
    private String username;
    private LocalDate date;
}