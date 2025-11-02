package br.com.bth8.catholic_saints_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "consecrated_person")
public class ConsecratedPerson extends Saint {

    //religiosos consagrados não podem ter position,diocese e ordinationDate

    @NotBlank
    @Column(length = 20)
    private String position = "N/A";

    @NotBlank
    @Column(length = 20)
    private String diocese = "N/A";

    @Column(name = "ordination_date")
    private LocalDate ordinationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "religious_order_id")
    private ReligiousOrder religiousOrder;
}
