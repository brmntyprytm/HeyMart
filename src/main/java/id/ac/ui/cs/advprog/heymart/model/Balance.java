package id.ac.ui.cs.advprog.heymart.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
public class Balance {
    private Long id;
    private double balance;
    private String type;

    public Balance(){}

    public Balance(Long id, double balance, String type) {
        this.id = id;
        this.balance = balance;
        this.type = type;
    }
}
