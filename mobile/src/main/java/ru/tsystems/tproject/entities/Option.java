package ru.tsystems.tproject.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * One of the entities.
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
    private final List<Option> optionsTogether = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "OPTIONS_INCOMPATIBLE",
            joinColumns = @JoinColumn(name = "optionOne_id"),
            inverseJoinColumns = @JoinColumn(name = "optionTwo_id")
    )
    private final List<Option> optionsIncompatible = new ArrayList<>();
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

    /**
     * Gets options that are necessary for current option,
     * @return
     */
    public List<Option> getOptionsTogether() {
        return optionsTogether;
    }

    /**
     * Deletes all options that are necessary for current option,
     */
    public void removeOptionsTogether() { this.optionsTogether.clear(); }

    /**
     * Adds an option necessary for current option.
     * @param optionTogether
     */

    public void addOptionsTogether(Option optionTogether) {
        this.optionsTogether.add(optionTogether);
    }

    /**
     * Gets options that are incompatible with current option,
     * @return
     */

    public List<Option> getOptionsIncompatible() {
        return optionsIncompatible;
    }

    /**
     * Removes all options that are incompatible with current option.
     */

    public void removeOptionsIncompatible() { this.optionsIncompatible.clear(); }

    /**
     * Adds an option that is incompatible with current option.
     * @param optionIncompatible
     */

    public void addOptionsIncompatible(Option optionIncompatible) {
        this.optionsIncompatible.add(optionIncompatible);
    }

        public String toString(){
            return String.format("Option{name: %s, price: %s, initialPrice: %s}", this.getName(), this.getPrice(), this.getInitialPrice());
    }
}
