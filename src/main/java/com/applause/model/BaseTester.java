package com.applause.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.IDENTITY;

@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class BaseTester {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @OneToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @ManyToMany(cascade = {PERSIST, MERGE})
    @JoinTable(name = "tester_device",
            schema = "applause",
            joinColumns = @JoinColumn(name = "tester_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "device_id", referencedColumnName = "id"))
    private Set<Device> devices;

    @OneToMany(cascade = {ALL})
    @JoinColumn(name = "tester_id", referencedColumnName = "id")
    private Set<Bug> bugs;

    private LocalDateTime lastLogin;

}
