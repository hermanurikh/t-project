package ru.tsystems.tproject.entities;

import javax.persistence.*;
import java.util.*;

/**
 * A basic entity class.
 */


@Entity
@Table (name = "USERS")
@NamedQuery(name = "User.getAll", query = "SELECT u FROM User u")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id")
    private int id;
    private String name;
    private String surname;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    private String passport;
    private String address;
    private String email;
    private String login;
    private int balance;
    private String password;
    @ManyToOne
    @JoinColumn (name = "role")
    private Role role;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "user")
    private final List<Contract> contracts = new ArrayList<>();

    public User() {
    }

    public User(String name, String surname, Date birthday, String passport, String address, String email, String login, int balance, String password, Role role) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.passport = passport;
        this.address = address;
        this.email = email;
        this.login = login;
        this.balance = balance;
        this.password = password;
        this.role = role;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getBalance() { return balance; }

    public void setBalance(int balance) { this.balance = balance; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void addContract(Contract contract) {
        this.contracts.add(contract);
    }

    public void removeContracts() {
        this.contracts.clear();
    }

    public void removeContract(Contract contract) {
        this.contracts.remove(contract);
    }

    public String toString(){
        return String.format("User{name: %s, surname: %s, birthday: %s, address: %s, passport: %s, balance: %s, e-mail: %s, password: %s}", this.getName(), this.getSurname(), this.getBirthday(), this.getAddress(), this.getPassport(), this.getBalance(), this.getEmail(), this.getPassword());
    }
}
