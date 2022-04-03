package hw2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "USERNAME")
    private String username;

    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;

//    @JsonIgnore
//    @Column(name = "FIRST_NAME")
//    private String firstName;
//
//    @JsonIgnore
//    @Column(name = "LAST_NAME")
//    private String lastName;
//
//    @JsonIgnore
//    @Column(name = "EMAIL", unique = true)
//    private String email;

    private Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

//    private Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
}
