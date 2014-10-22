package ru.tsystems.tproject.entities;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by german on 17.10.14.
 */
@Entity
@Table (name = "TARIFFS")
@NamedQuery(name = "Tariff.getAllTariffs", query = "SELECT tar FROM Tariff tar")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private int id;
    private String name;
    private int price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "TARIFF_RULES",
            joinColumns = @JoinColumn(name = "tariff_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<Option> possibleOptions = new ArrayList<>();
    public Tariff(){}
    public Tariff(String name, int price)
    {
        this.name = name;
        this.price = price;
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

    public List<Option> getPossibleOptions() {
        return possibleOptions;
    }

    public void removePossibleOptions() { this.possibleOptions.clear(); }

    /**
     * This method removes an option from the list of possible options.
     * @param option
     */
    public void removeOptionForTariff(Option option) {
        this.possibleOptions.remove(option);
    }

    public void addPossibleOption(Option option) {
        this.possibleOptions.add(option);
    }
    public String toString(){
        return String.format("Tariff{name: %s, price: %s}", this.getName(), this.getPrice());
    }
}
