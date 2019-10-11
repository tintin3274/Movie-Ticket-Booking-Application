package Class;

public class Account {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public Account(String username, String password, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean checkPasswordCorrect(String password){
        return this.password.equals(password);
    }
}
