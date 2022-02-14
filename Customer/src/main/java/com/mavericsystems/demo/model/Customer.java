package com.mavericsystems.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

//Customer model - includes customer attributes, getter-setter and constructors (created using annotations)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("customer") //MongoDB is noSQL DB and so the data is stored as document
public class Customer {

    @Id
    private String customerID;
    private String customerName;
    private Long mobileNo;
    private String mailID;
    private Date dob;
}
