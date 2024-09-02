package com.thexyde.hris.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "job_levels")
public class JobLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Client client;

    public JobLevel() {
    }

    public JobLevel(UUID id, String name, Client client) {
        this.id = id;
        this.name = name;
        this.client = client;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public JobLevel id(UUID id) {
        setId(id);
        return this;
    }

    public JobLevel name(String name) {
        setName(name);
        return this;
    }

    public JobLevel client(Client client) {
        setClient(client);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof JobLevel)) {
            return false;
        }
        JobLevel jobLevel = (JobLevel) o;
        return Objects.equals(id, jobLevel.id) && Objects.equals(name, jobLevel.name)
                && Objects.equals(client, jobLevel.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, client);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", client='" + getClient() + "'" +
                "}";
    }

}
