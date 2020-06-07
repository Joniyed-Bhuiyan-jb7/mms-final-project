package io.swagger.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DailyActivities{
    @Id
    private long serialNo;
    private String username;
    private LocalDate date;
    @ApiModelProperty(hidden = true)
    private Double totalCost;
    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Bazar> bazar;

    @Override
    public String toString() {
        AtomicReference<String> bazarAsName= new AtomicReference<>("");
        bazar.forEach(b -> {
            bazarAsName.set(bazarAsName.get()+"\n"+(b.getName()+"-unit: "+b.getUnit()+"-unitPrice:"+b.getCostPerUnit()));
        });
        return "DailyActivities{" +
                "serialNo=" + serialNo +
                ", username='" + username + '\'' +
                ", date=" + date +
                ", totalCost=" + totalCost +
                ", bazar=" + bazarAsName.get() +
                '}';
    }
}

