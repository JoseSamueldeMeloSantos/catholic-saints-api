package br.com.bth8.catholic_saints_api.dto;

import br.com.bth8.catholic_saints_api.model.Miracle;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Relation(collectionRelation = "saints")
public  class SaintDTO extends RepresentationModel<SaintDTO> {

    private UUID saintId;
    private String name;
    private String description;
    private LocalDate baptismDate;
    private LocalDate deathDate;
    private LocalDate canonizationDate;
    private List<Miracle> miracles;

    public SaintDTO(
            UUID saintId, String name,
            String description, LocalDate baptismDate,
            LocalDate deathDate, LocalDate canonizationDate,
            List<Miracle> miracles) {

        this.saintId = saintId;
        this.name = name;
        this.description = description;
        this.baptismDate = baptismDate;
        this.deathDate = deathDate;
        this.canonizationDate = canonizationDate;
        this.miracles = miracles;
    }

    public SaintDTO() {
    }

    public UUID getSaintId() {
        return saintId;
    }

    public void setSaintId(UUID saintId) {
        this.saintId = saintId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getBaptismDate() {
        return baptismDate;
    }

    public void setBaptismDate(LocalDate baptismDate) {
        this.baptismDate = baptismDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public LocalDate getCanonizationDate() {
        return canonizationDate;
    }

    public void setCanonizationDate(LocalDate canonizationDate) {
        this.canonizationDate = canonizationDate;
    }

    public List<Miracle> getMiracles() {
        return miracles;
    }

    public void setMiracles(List<Miracle> miracles) {
        this.miracles = miracles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SaintDTO saint = (SaintDTO) o;
        return Objects.equals(saintId, saint.saintId) && Objects.equals(name, saint.name) && Objects.equals(description, saint.description) && Objects.equals(baptismDate, saint.baptismDate) && Objects.equals(deathDate, saint.deathDate) && Objects.equals(canonizationDate, saint.canonizationDate) && Objects.equals(miracles, saint.miracles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saintId, name, description, baptismDate, deathDate, canonizationDate, miracles);
    }
}
