package com.example.authenticationserver.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class AppUser {
    @Id

    @Column(unique = true,nullable = false)
    private String email;
    @Column(nullable = false)
    private String pwd;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Role role;
    private boolean verified;

    @OneToOne(cascade = CascadeType.ALL)
    MemberProfile profile;

    public AppUser(String email, String pwd, Role role, boolean verified) {
        this.email = email;
        this.pwd = pwd;
        this.role = role;
        this.verified = verified;
    }
}
