package com.dash.models;

import java.util.Date;
import lombok.Data;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String password;
    private String contact;
    private String gender;
    private Date dateOfBirth;
    private String pdfFile;
    private boolean admin;  
    private String login;

}
