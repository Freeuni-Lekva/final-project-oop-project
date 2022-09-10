package com.quizzetta.Model;

import java.util.List;

public class User {
    private long id;
    private String email;
    private String username;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private boolean isAdmin;

    private String imageUrl;
    private List<User> friends;

    public User(long id, String email, String username, String passwordHash, String firstName, String lastName, boolean isAdmin, String imageUrl) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.imageUrl = imageUrl;
    }

    public User(String email, String username, String passwordHash, String firstName, String lastName, boolean isAdmin, String imageUrl) {
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.imageUrl = imageUrl;
    }

    public User(long id, String email, String username, String passwordHash, String firstName, String lastName, boolean isAdmin) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.imageUrl = null;
    }

    public User(String email, String username, String passwordHash, String firstName, String lastName, boolean isAdmin) {
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.imageUrl = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return (id == user.id);
    }

    @Override
    public String toString() {
        return "User {" + "username ='" + username + '\'' +
                ", hashedPassword ='" + passwordHash + '\'' +
                ", id =" + id + ", isAdmin =" + isAdmin +
                ", firstName ='" + firstName + '\'' +
                ", lastName ='" + lastName + '\'' + '}';
    }
}
