package com.mavericsystems.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

//Customer model - includes customer attributes, getter-setter and constructors (created using annotations)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("customer") //MongoDB is noSQL DB and so the data is stored as document
public class Customer {

    @Id
    private Integer customerID;

    @NotBlank(message = "Customer name cannot be empty!!!")
    private String customerName;
    //@Column(length = 10)
  //  @Size(min = 10, max = 10)
    private Long mobileNo;
    private String mailID;

    //@DateTimeFormat()
    private Date dob;
    Address address;

    ////////////////
 //   private enum customerType{INDIVIDUAL, JOINT};
}
