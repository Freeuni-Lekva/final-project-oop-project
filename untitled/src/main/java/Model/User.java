package Model;

public class User {
    private long id;
    private String username;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private boolean isAdmin;

    public User (long id, String username, String passwordHash, String firstName, String lastName, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
    }

    public User (String username, String passwordHash, String firstName, String lastName, boolean isAdmin) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

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

    @Override
    public boolean equals (Object o) {
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
