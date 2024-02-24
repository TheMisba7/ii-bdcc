package org.example.exercice2;

import java.util.ArrayList;
import java.util.List;

public class Managment {
    private static final List<CompteBancaire> accounts = new ArrayList<>();

    static {
        accounts.add(new CompteCourant(38474, 2873744, "holder-1"));
        accounts.add(new CompteEpargne(937462, 4473646, "holder"));
        accounts.add(new CompteEpargne(948753, 38473, "holder-3"));
    }

    public static void main(String[] args) {

        transfer(38474, 937462, 83747);
        // no account with num 64757
        transfer(937462, 64757, 74758);

    }
    public static CompteBancaire getAccount(int num) {
       return accounts.stream()
                .filter(ac -> ac.getNum() == num)
                .findFirst()
                .orElseThrow(() -> new CompteInexistantException(num));
    }

    private static void transfer(int from, int to, double amount) {
        try {

            CompteBancaire fromAccount = getAccount(from);
            fromAccount.transfer(to, amount);
        } catch (CompteInexistantException e) {
            System.err.println(e.getNum());
        }
    }
}
