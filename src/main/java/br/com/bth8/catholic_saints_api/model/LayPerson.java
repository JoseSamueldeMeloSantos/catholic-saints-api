package br.com.bth8.catholic_saints_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@DiscriminatorValue("lay_person")
public class LayPerson extends Saint {

    @NotBlank
    @Column(length = 30)
    private String ocupation;

    private Boolean maried;

    @NotBlank
    @Column(name = "associated_movement", length = 50)
    private String associatedMovement;

}
