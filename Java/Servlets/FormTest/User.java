package beans;

public class User {

    private String email = "";
    private String password = "";

    private String validationErrorMessage = "";

    public String getEmail() {
        return email;
    }

    public User() {

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean isValid() {

        if (email == null && password == null) {
            validationErrorMessage = "Please set the email and password.";
            return false;

        } else if (email == null) {
            validationErrorMessage = "Please set the email.";
            return false;

        } else if (password == null) {
            validationErrorMessage = "Please set the password.";
            return false;

        } else if (password.length() < 6) {
            validationErrorMessage = "Password is too short, minimum 6 characters.";
            return false;

        } else if (password.length() > 20) {
            validationErrorMessage = "Password is too long, maximum 20 characters.";
            return false;

        } else if (password.matches("\\w*\\s+\\w*")) {
            validationErrorMessage = "Password cannot contain space.";
            return false;

        } else if (!email.matches("\\w+@\\w+\\.\\w+")) {
            validationErrorMessage = "Invalid email address";
            return false;
        }
        validationErrorMessage = "";
        return true;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidationErrorMessage() {
        return validationErrorMessage;
    }
}
