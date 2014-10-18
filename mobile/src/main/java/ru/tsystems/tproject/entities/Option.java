package ru.tsystems.tproject.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by german on 17.10.14.
 */
@Entity
@Table (name = "OPTIONS")
@NamedQuery(name = "Option.getAllOptions", query = "SELECT opt FROM Option opt")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    private String name;
    private int price;
    private int initialPrice;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "OPTIONS_TOGETHER",
            joinColumns = @JoinColumn(name = "optionOne_id"),
            inverseJoinColumns = @JoinColumn(name = "optionTwo_id")
    )
    private List<Option> optionsTogether = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "OPTIONS_INCOMPATIBLE",
            joinColumns = @JoinColumn(name = "optionOne_id"),
            inverseJoinColumns = @JoinColumn(name = "optionTwo_id")
    )
    private List<Option> optionsIncompatible = new ArrayList<>();
    public Option(){}

    public Option(String name, int price, int initialPrice) {
        this.name = name;
        this.price = price;
        this.initialPrice = initialPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(int initialPrice) {
        this.initialPrice = initialPrice;
    }

    public List<Option> getOptionsTogether() {
        return optionsTogether;
    }

    public void addOptionsTogether(Option optionTogether) {
        this.optionsTogether.add(optionTogether);
    }

    public List<Option> getOptionsIncompatible() {
        return optionsIncompatible;
    }

    public void addOptionsIncompatible(Option optionIncompatible) {
        this.optionsIncompatible.add(optionIncompatible);
    }

        public String toString(){
            return String.format("Option{name: %s, price: %s, initialPrice: %s}", this.getName(), this.getPrice(), this.getInitialPrice());
    }
}
