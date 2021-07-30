package com.musala.gateways.manager.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="gateway")
public class Gateway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gateway_id")
    @JsonIgnore
    private int id;
    @Column(name="serial_number",nullable = false,unique = true)
    private String serialNumber;
    @Column(name = "name")
    private String name;
    @Column(name = "ipv4")
    private String ipv4;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "gateway_id")
    private List<Peripheral> peripherals;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gateway gateway = (Gateway) o;
        return id == gateway.id && serialNumber.equals(gateway.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serialNumber);
    }
}
