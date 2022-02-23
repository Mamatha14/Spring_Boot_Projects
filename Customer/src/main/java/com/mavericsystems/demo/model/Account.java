package com.mavericsystems.demo.model;

import com.mavericsystems.demo.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {

    private Long accountNo;
    private Integer customerID;
    private String ifscCode;
    private Date dateOfCreation;
    private Double balance;
    private boolean isActive;
    private AccountType accountType;
}

