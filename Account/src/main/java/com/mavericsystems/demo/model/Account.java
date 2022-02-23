package com.mavericsystems.demo.model;

import com.mavericsystems.demo.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

//Account class - includes account attributes, getter-setter and constructors (created using annotations)

@Entity
@Table(name = "account") //Using MySQL and so using @Table annotation to specify the table name
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountNo;

    private Integer customerID;
    private String ifscCode;
    private Date dateOfCreation;
    private Double balance;
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;


}
