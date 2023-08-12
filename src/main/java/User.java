public class User { //TODO: seal an account and admin option to unseal it
    String name;
    int accountNumber;
    String password;
    Account account = new Account();  //Automatically instantiates a new object of the subclass. Alternative: instantiate in the method, where user gets created.


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
