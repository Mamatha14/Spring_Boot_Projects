package com.mavericsystems.demo.model;

import com.mavericsystems.demo.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "Customer mobile number cannot be empty!!!")
    @Min(10)
    private Long mobileNo;

    private String mailID;
    private Date dob;
    private boolean isActive;

    @NotNull(message = "Customer address cannot be empty!!!")
    private Address address;

    @NotNull(message = "Customer address cannot be empty!!!")
    private CustomerType customerType;
}
