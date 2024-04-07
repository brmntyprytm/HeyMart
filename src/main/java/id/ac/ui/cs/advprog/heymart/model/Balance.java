package id.ac.ui.cs.advprog.heymart.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Balance {
    private double balance;
    private Long id;

    public Balance(Long id, double balance) {
        this.id = id;
        this.balance = balance;
    }
}
