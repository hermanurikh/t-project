package ru.tsystems.tproject.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by german on 17.10.14.
 */
@Entity
@Table (name = "OPTIONS")
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
            joinColumns = @JoinColumn(name = "optionOne_ID"),
            inverseJoinColumns = @JoinColumn(name = "optionTwo_ID")
    )
    private List<Option> optionsTogether = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "OPTIONS_INCOMPATIBLE",
            joinColumns = @JoinColumn(name = "optionOne_ID"),
            inverseJoinColumns = @JoinColumn(name = "optionTwo_ID")
    )
    private List<Option> optionsIncompatible = new ArrayList<>();

}
