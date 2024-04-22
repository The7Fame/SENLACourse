package eu.senla.naumovich.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "role_name")
    String roleName;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    List<User> users;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    List<Privilege> privileges;
}
