import java.util.*;

public class Methods {
    List<User> userList = new ArrayList<>();

    public void greetUser(Scanner scanner) { //TODO: user interface: create account, login (& logout? & info abt bank?)
        System.out.println("We welcome you at one of our many Simplified ATM's in your area!");
        System.out.println("A service free of charge from us: the Simplified Bank. Where banking is still simple.");
        System.out.println();
//        addDummyUser();
        checkUserLoginOrCreate(scanner);
    }

    private void checkUserLoginOrCreate(Scanner scanner) {
        String userCommands = """
                Type in:
                        login    to log into your account.
                        create   to register and create an account.
                """;
        System.out.println(userCommands);
        System.out.print("What do you want to do: ");
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "login" -> login(scanner);
                case "create" -> createAccount(scanner);
//                case "admin" -> adminLogin(scanner);
                default -> retryCheckUserLoginOrCreate(scanner);
            }
        } else {
            System.out.println("Please choose one of the available keywords.");
            retryCheckUserLoginOrCreate(scanner);
        }
    }

    private void retryCheckUserLoginOrCreate(Scanner scanner) {
        System.out.println("There must have occurred a typo. Please try again.");
        checkUserLoginOrCreate(scanner);
    }

//    private void adminLogin(Scanner scanner) {
//        System.out.println("Hello Thomas. What would you like to make?");
//        String adminInput = scanner.next().toLowerCase();
//        switch (adminInput) {
//            case "clients" -> System.out.println("We have " + userList.size() + " happy clients.");
//            default -> greetUser(scanner);
//        }
//    }

    private void createAccount(Scanner scanner) {
        System.out.println("We are happy that you want to join the Simplified Banking Family!");
        System.out.println("Please enter your last name followed by your firstname with a space in between like: ");
        System.out.println("Lastname Firstname");
        User user = new User();
        if (scanner.hasNextLine()) {
            user.setName(scanner.nextLine());
            user.setPassword(createPassword(scanner));
            user.setAccountNumber(userList.size() + 1);
            userList.add(user);

            System.out.println(userList.get(0));

        } else {
            System.out.println("There may ave occurred an error. Please try again.");
            createAccount(scanner);
        }
        System.out.println();
        System.out.println("Welcome in the Simplified Banking Family dear " + user.getName() + "!");
        System.out.println("Your Account Number is: " + user.getAccountNumber());
        System.out.println();
        System.out.println("We will redirect you to the welcoming screen.");
        System.out.println("If you want to deposit money please log yourself in.");
        System.out.println();
        checkUserLoginOrCreate(scanner);
    }

    protected String createPassword(Scanner scanner) {
        System.out.println("Please enter your password. It needs to be between 4 and 16 symbols long.");
        String password = scanner.nextLine();
        if (password.length() <= 4 || password.length() >= 16) {
            System.out.println("Your password needs to be between 5 and 16 symbols long.");
            System.out.println("Please try again.");
            createPassword(scanner);
        }
        return password;
    }

    public void login(Scanner scanner) {
        System.out.println("Please enter your Account Number: ");
        int accountNumber = scanner.nextInt();
        User foundUser = null;
        boolean isUserFound = false;
        for (User user : userList) {
            if (user.getAccountNumber() == accountNumber) {
                isUserFound = true;
                foundUser = user;
            } 
        }
        if (isUserFound) {
            loginPasswordLoop(foundUser, scanner);    
        } else {
            System.out.println("We haven't found an Account Number with your supplied information. Please try again.");
            login(scanner);   
        }
    }

    public void loginPasswordLoop(User user, Scanner scanner) {
        for (int i = 0; i < 3; i++) {
            System.out.println("Please enter your Password: ");
            if (user.getPassword().equals(scanner.next())) {
                banking(user, scanner);
            } else {
                System.out.println("Incorrect password. You have " + (3 - i) + " tries left.");
                // TODO: Seal account.
            }
        }
        System.out.println("You entered the wrong password three times. Your account has been sealed.");
        System.out.println("Please call our service number to get access again.");
        greetUser(scanner);
    }

    public void banking(User user, Scanner scanner) {

        System.out.println("Hello " + user.getName() + ".");
        System.out.println("Your balance is $" + user.account.balance);
        System.out.println("""
                Type in:
                        deposit     to stock up your account.
                        withdraw    to withdraw money from your account.
                        """);
        if (scanner.hasNextLine()) { //TODO: else
            switch (scanner.next().toLowerCase()) {
                case ("deposit") -> {
                    user.account.depositMoney(scanner.nextInt());
                    greetUser(scanner);
                }
                case ("withdraw") -> { //TODO: Put nesting stuff in Account's withdraw method
                    if (user.account.balance != 0) {
                        System.out.println("Please tell us the amount you want to withdraw.");
                        user.account.withdrawMoney(scanner.nextInt());
                        System.out.println("Thank you for using Simplified Banking. We will return you to the Welcome Screen.");
                        greetUser(scanner);
                    } else {
                        System.out.println("You can't withdraw money on an empty account.");
                    }
                }
                default -> {
                    System.out.println("We didn't get that. Please try again.");
                    banking(user, scanner);
                }
            }
        }
    }
}