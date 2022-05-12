package com.challenge.sebastian.orellana.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //auto incremental
    private Long id;
    private String name;
    private String email;
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    private boolean isActive = true;
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createAt=" + createAt +
                ", modified=" + modified +
                ", lastLogin=" + lastLogin +
                ", isActive=" + isActive +
                '}';
    }
}
