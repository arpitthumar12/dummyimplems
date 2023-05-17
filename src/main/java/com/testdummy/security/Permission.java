package com.testdummy.security;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "permissions")
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "permissions")
    private List<UserGroup> userGroups;

    @ElementCollection
    @CollectionTable(name = "permission_endpoints", joinColumns = @JoinColumn(name = "permission_id"))
    @Column(name = "endpoints", columnDefinition = "jsonb")
    private List<String> endpoints;



    // Add necessary mappings, relationships, and constructors

    // Getters and setters

}
