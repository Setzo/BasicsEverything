package temp;

import beans.User;

public class Test {

    public static void main(String[] args) {

        User user = new User("set@set.com", "Password");

        if (user.isValid()) {
            System.out.println("Bean validated");
        } else {
            System.out.println(user.getValidationErrorMessage());
        }

    }
}
