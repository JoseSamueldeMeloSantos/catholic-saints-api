package br.com.bth8.catholic_saints_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "saints")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//Define que todos os dados das subclasses serão armazenados na tabela desta classe.
@DiscriminatorColumn(//usada para definir uma coluna na tabela do banco de dados que será usada para distinguir
        name = "SaintType",
        discriminatorType = DiscriminatorType.STRING

)
public abstract class Saint {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID saintId;

    @NotBlank
    @Column(nullable = false, length = 30)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(name = "baptism_date")
    @Temporal(TemporalType.DATE)
    private Date baptismDate;

    @Column(name = "death_date")
    @Temporal(TemporalType.DATE)
    private Date deathDate;

    @NotBlank
    @Column(name = "canocization_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date canonizationDate;

    @OneToMany(mappedBy = "saint")
    private List<Miracle> miracles;

}
