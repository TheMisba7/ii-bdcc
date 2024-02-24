package org.example.exercice2;

public abstract class CompteBancaire {
    protected int num;
    protected double balance;
    protected String holder;

    public CompteBancaire(int num, double balance, String holder) {
        this.num = num;
        this.balance = balance;
        this.holder = holder;
    }

    public int getNum() {
        return num;
    }

    public void afficherCompteInfo() {
        String detail = "Num: " + num  + " solde: " + balance;
        System.out.println(detail);
    }

    void retirer(double montant) {
        if (this.balance < montant)
            throw new FondsInsuffisantsException("insufficient funds ");

        this.balance = this.balance - montant;
    }

    void deposer(double montant) {
        this.balance += montant;
    }
    void transfer(int to, double amount) {
        CompteBancaire account = Managment.getAccount(to);
        this.retirer(amount);
        account.deposer(amount);
    }
}
