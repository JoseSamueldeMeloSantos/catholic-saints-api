package br.com.bth8.catholic_saints_api.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ReligiousOrderDTO {

    private UUID religiousOrderId;
    private String founder;
    private String name;
    private Date foundationDate;
    private List<String> vows;

    public ReligiousOrderDTO(UUID religiousOrderId, String founder, String name, Date foundationDate, List<String> vows) {
        this.religiousOrderId = religiousOrderId;
        this.founder = founder;
        this.name = name;
        this.foundationDate = foundationDate;
        this.vows = vows;
    }

    public UUID getReligiousOrderId() {
        return religiousOrderId;
    }

    public void setReligiousOrderId(UUID religiousOrderId) {
        this.religiousOrderId = religiousOrderId;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(Date foundationDate) {
        this.foundationDate = foundationDate;
    }

    public List<String> getVows() {
        return vows;
    }

    public void setVows(List<String> vows) {
        this.vows = vows;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReligiousOrderDTO that = (ReligiousOrderDTO) o;
        return Objects.equals(religiousOrderId, that.religiousOrderId) && Objects.equals(founder, that.founder) && Objects.equals(name, that.name) && Objects.equals(foundationDate, that.foundationDate) && Objects.equals(vows, that.vows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(religiousOrderId, founder, name, foundationDate, vows);
    }
}
