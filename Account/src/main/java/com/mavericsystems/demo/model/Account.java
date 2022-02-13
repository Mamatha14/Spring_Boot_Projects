package com.mavericsystems.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long accountNo;
    private String customerID;
    private String ifscCode;
    private Date dateOfCreation;
}
