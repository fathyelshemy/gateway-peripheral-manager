package com.musala.gateways.manager.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="peripheral")
public class Peripheral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    @Column(name = "vendor")
    private String vendor;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "status")
    private Boolean status;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peripheral that = (Peripheral) o;
        return uid == that.uid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }
}
