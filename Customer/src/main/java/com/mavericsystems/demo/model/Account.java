package com.mavericsystems.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountNo;
    @Id
    private Long customerID;
    private String ifscCode;
    private Date dateOfCreation;
    private Double balance;
}
