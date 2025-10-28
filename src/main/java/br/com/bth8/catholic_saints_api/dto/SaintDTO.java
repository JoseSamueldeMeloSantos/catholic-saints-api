package br.com.bth8.catholic_saints_api.dto;

import br.com.bth8.catholic_saints_api.model.Miracle;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class SaintDTO {

    private UUID saintId;
    private String name;
    private String description;
    private Date baptismDate;
    private Date deathDate;
    private Date canonizationDate;
    private List<Miracle> miracles;

    public SaintDTO(
            UUID saintId, String name,
            String description, Date baptismDate,
            Date deathDate, Date canonizationDate,
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

    public Date getBaptismDate() {
        return baptismDate;
    }

    public void setBaptismDate(Date baptismDate) {
        this.baptismDate = baptismDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public Date getCanonizationDate() {
        return canonizationDate;
    }

    public void setCanonizationDate(Date canonizationDate) {
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
