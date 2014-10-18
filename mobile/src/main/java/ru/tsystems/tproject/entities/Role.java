package ru.tsystems.tproject.entities;
import javax.persistence.*;

/**
 * Created by german on 17.10.14.
 */
@Entity
@Table (name = "ROLES")
@NamedQuery(name = "Role.getAllRoles", query = "SELECT r FROM Role r")
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id")
    private int id;
    private String name;
    public Role(){
    }
    public Role(String name)
    {
        this.name = name;
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
    public String toString(){
        return String.format("Role{name: %s}", this.getName());
    }
}
