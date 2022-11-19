package com.example.authenticationserver.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class MemberProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;
    private String firstName;
    private String secondName;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Secret> secrets;

    public MemberProfile(String firstName, String secondName, List<Secret> secrets)
    {
        this.firstName = firstName;
        this.secondName = secondName;

        this.secrets = secrets;
    }
}
