package com.practicaljava.lesson29.onlinestore.model;

import com.practicaljava.lesson29.onlinestore.RoleType;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@NamedQueries({
    @NamedQuery(name = "User.byId", query = "select u from User u where u.id = :id")
})
public class User implements Serializable {
    @Id
    private String id = "guest";
    private Integer rewardPoints = 0;

    private String password;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<RoleType> roles = new HashSet<RoleType>();

    private String address;

    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Review> reviews;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(Integer rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public void addRole(RoleType role) {
        roles.add(role);
    }
}
