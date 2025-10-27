package br.com.bth8.catholic_saints_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "consecrated_person")
public class ConsecratedPerson extends Saint {

    @NotBlank
    @Column(nullable = false, length = 20)
    private String position;

    @NotBlank
    @Column(length = 20)
    private String diocese;

    @Column(name = "ordination_date")
    @Temporal(TemporalType.DATE)
    private Date ordinationDate;

    @ManyToOne
    @JoinColumn(name = "religious_order_id")
    private ReligiousOrder religiousOrder;
}
