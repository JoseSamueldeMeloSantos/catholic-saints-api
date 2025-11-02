package br.com.bth8.catholic_saints_api.dto;

import br.com.bth8.catholic_saints_api.model.Miracle;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Relation(collectionRelation = "lay_persons")
public class LayPersonDTO extends SaintDTO{

    private String ocupation;
    private Boolean maried;
    private String associatedMovement;

    public LayPersonDTO(
            UUID saintId, String name, String description,
            LocalDate baptismDate, LocalDate deathDate, LocalDate canonizationDate,
            List<Miracle> miracles, String ocupation, Boolean maried, String associatedMovement) {
        super(saintId, name, description, baptismDate, deathDate, canonizationDate, miracles);
        this.ocupation = ocupation;
        this.maried = maried;
        this.associatedMovement = associatedMovement;
    }

    public LayPersonDTO() {
        super();
    }

    public String getOcupation() {
        return ocupation;
    }

    public void setOcupation(String ocupation) {
        this.ocupation = ocupation;
    }

    public Boolean getMaried() {
        return maried;
    }

    public void setMaried(Boolean maried) {
        this.maried = maried;
    }

    public String getAssociatedMovement() {
        return associatedMovement;
    }

    public void setAssociatedMovement(String associatedMovement) {
        this.associatedMovement = associatedMovement;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LayPersonDTO that = (LayPersonDTO) o;
        return Objects.equals(ocupation, that.ocupation) && Objects.equals(maried, that.maried) && Objects.equals(associatedMovement, that.associatedMovement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ocupation, maried, associatedMovement);
    }
}
