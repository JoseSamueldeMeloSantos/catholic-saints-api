package br.com.bth8.catholic_saints_api.dto;

import br.com.bth8.catholic_saints_api.model.Miracle;
import br.com.bth8.catholic_saints_api.model.ReligiousOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ConsecratedPersonDTO extends SaintDTO{

    private String position;
    private String diocese;
    private Date ordinationDate;
    private ReligiousOrder religiousOrder;

    public ConsecratedPersonDTO(
            UUID saintId, String name, String description,
            Date baptismDate, Date deathDate, Date canonizationDate,
            List<Miracle> miracles, String position, String diocese,
            Date ordinationDate, ReligiousOrder religiousOrder) {
        super(saintId, name, description, baptismDate, deathDate, canonizationDate, miracles);
        this.position = position;
        this.diocese = diocese;
        this.ordinationDate = ordinationDate;
        this.religiousOrder = religiousOrder;
    }

    public ConsecratedPersonDTO() {
        super();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDiocese() {
        return diocese;
    }

    public void setDiocese(String diocese) {
        this.diocese = diocese;
    }

    public Date getOrdinationDate() {
        return ordinationDate;
    }

    public void setOrdinationDate(Date ordinationDate) {
        this.ordinationDate = ordinationDate;
    }

    public ReligiousOrder getReligiousOrder() {
        return religiousOrder;
    }

    public void setReligiousOrder(ReligiousOrder religiousOrder) {
        this.religiousOrder = religiousOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ConsecratedPersonDTO that = (ConsecratedPersonDTO) o;
        return Objects.equals(position, that.position) && Objects.equals(diocese, that.diocese) && Objects.equals(ordinationDate, that.ordinationDate) && Objects.equals(religiousOrder, that.religiousOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), position, diocese, ordinationDate, religiousOrder);
    }
}
