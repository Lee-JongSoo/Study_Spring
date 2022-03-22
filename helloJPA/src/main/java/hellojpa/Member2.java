package hellojpa;

import javax.persistence.*;

@Entity
public class Member2 {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "USERNAME")
    private String username;

}
