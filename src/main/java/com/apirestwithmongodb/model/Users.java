package com.apirestwithmongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@Builder
public class Users {

    @Id
    @JsonIgnore
    private String id;

    @JsonIgnore
    private Date createdDate;

    @JsonIgnore
    private Date editedDate;

    @ApiModelProperty(notes = "Name is required!", required = true)
    private String name;

    @ApiModelProperty(notes = "Mail is required: Ex. example@example.com", required = true)
    private String mail;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String status;

}
