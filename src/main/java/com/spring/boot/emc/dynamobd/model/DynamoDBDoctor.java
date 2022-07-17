package com.spring.boot.emc.dynamobd.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Profile({"dynamodb"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "doctor")
public class DynamoDBDoctor {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private Integer doctorId;

    @DynamoDBAttribute
    private String firstName;

    @DynamoDBAttribute
    private String lastName;

    @DynamoDBAttribute
    private String sex;

    @DynamoDBAttribute
    private String address;

    @DynamoDBAttribute
    private String phoneNumber;

    @DynamoDBAttribute
    private String speciality;

}
