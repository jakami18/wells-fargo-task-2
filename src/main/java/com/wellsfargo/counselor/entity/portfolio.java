package com.wellsfargo.counselor.entity;

import jakarta.persistence.*;

import java.util.ArrayList;

import java.util.List;


@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // primary key (auto-generated)

    // One portfolio belongs to exactly one client
    @OneToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false, unique = true)
    private Client client;

    // One portfolio has many securities
    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Security> securities = new ArrayList<>();

    // --- constructors ---
    public Portfolio() { }

    // initializes all non-id fields
    public Portfolio(Client client, List<Security> securities) {
        this.client = client;
        if (securities != null) this.securities = securities;
    }

    // --- getters / setters ---
    public Long getId() { return id; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public List<Security> getSecurities() { return securities; }
    public void setSecurities(List<Security> securities) { this.securities = securities; }
}

