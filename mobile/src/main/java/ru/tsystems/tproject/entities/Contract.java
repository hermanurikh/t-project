package ru.tsystems.tproject.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by german on 17.10.14.
 */
@Entity
@Table (name = "CONTRACTS", uniqueConstraints = @UniqueConstraint(columnNames = {"number"}))
@NamedQuery (name = "Contract.getAllContracts", query = "SELECT c FROM Contract c")
public class Contract {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id")
    private int id;
    private long number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;
    private boolean isBlocked;

    @ManyToOne
    @JoinColumn(name = "blockedEmp_id")
    private User employee;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
            (
                    name = "CONTRACT_OPTION",
                    joinColumns = {@JoinColumn(name = "contract_id")},
                    inverseJoinColumns = {@JoinColumn(name = "option_id")}
            )
    private List<Option> options = new ArrayList<>();
    public Contract(){

    }
    public Contract(long number, User user, Tariff tariff)
    {
        this.number = number;
        this.user = user;
        this.tariff = tariff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void removeOption(Option option) { this.options.remove(option); }

    public void removeAllOptions() { this.options.clear(); }

    public void addOption(Option option) {
        this.options.add(option);
    }
    public String toString(){
        return String.format("Contract{number: %s, user: %s, tariff: %s, isBlocked: %s, blockedEmpID: %s}", this.getNumber(), this.getUser(), this.getTariff(), this.isBlocked(), this.getEmployee());
    }
}
