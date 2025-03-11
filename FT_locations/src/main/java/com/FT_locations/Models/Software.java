package com.FT_locations.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "laptop")
public class Software {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String os;
    private String firmwareVersion;
    private LocalDate firmwareUpdatedAt;

    @OneToOne(mappedBy = "software")
    @JsonIgnore
    private Laptop laptop;

    public Software(String os, String firmwareVersion, LocalDate firmwareUpdatedAt) {
        this.os = os;
        this.firmwareVersion = firmwareVersion;
        this.firmwareUpdatedAt = firmwareUpdatedAt;
    }
}
