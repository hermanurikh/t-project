package ru.tsystems.tproject.entities;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * An entity class.
 */
@Entity
@Table (name = "TARIFFS")
@NamedQuery(name = "Tariff.getAll", query = "SELECT tar FROM Tariff tar")
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
    private final List<Option> possibleOptions = new ArrayList<>();
    public Tariff(){}
    public Tariff(String name, int price) {
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
        return this.possibleOptions;
    }

    public void removePossibleOptions() {
        this.possibleOptions.clear();
    }

    /**
     * This method removes an option from the list of possible options.
     * @param option the option to delete
     */
    public void removeOptionForTariff(Option option) {
        this.possibleOptions.remove(option);
    }

    /**
     * This method adds an option to the list of possible options.
     * @param option the option to add
     */

    public void addPossibleOption(Option option) {
        this.possibleOptions.add(option);
    }
    @Override
    public String toString(){
        return String.format("Tariff{name: %s, price: %s}", this.getName(), this.getPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tariff)) {
            return false;
        }

        Tariff tariff = (Tariff) o;

        return (id == tariff.id);
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }



}
