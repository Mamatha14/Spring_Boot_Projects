package com.mavericsystems.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

//Account class - includes account attributes, getter-setter and constructors (created using annotations)

@Entity
@Table(name = "accounts") //Using MySQL and so using @Table annotation to specify the table name
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountNo;

    private Integer customerID;
    private String ifscCode;
    private Date dateOfCreation;
    ////////////////////
    private Double balance;
  //  private enum accountType{CASH, CURRENT, INSTANT, FIXED_TERM_DEPOSIT};
}
