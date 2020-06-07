package io.swagger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MemberManager {
  @Id
  @Column(name = "username",length = 50)
  private String username;
  private Name name;
  private Address address;
  private Phone phone;
  @Column(name = "email",length = 50,nullable = false)
  private String email;
  @Column(name = "localDate",nullable = false)
  private LocalDate localDate;
}

