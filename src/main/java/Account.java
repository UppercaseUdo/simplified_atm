public class Account {
    double balance = 0;

    protected void withdrawMoney (double amount) { //TODO: No credit
        balance = balance - amount;
        System.out.println("Have fun with your §" + amount + ". You have $" + balance + " left on your account.");
    }

    protected void depositMoney (double amount) { //TODO: Only allow positive values.
        System.out.println("Please fill the deposit box and enter the amount.");
        balance = balance + amount;
        System.out.println("It's always wise to build up a nest egg. Your $" + amount + " stocks your balance to $" + balance);
        System.out.println("Thank you for using Simplified Banking. We will return you to the Welcome Screen.");
    }
}