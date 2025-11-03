package br.com.bth8.catholic_saints_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode
@Entity
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
    @Column(nullable = false, length = 30,unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(name = "baptism_date")
    private LocalDate baptismDate;

    @Column(name = "death_date")
    private LocalDate deathDate;

    @NotNull
    @Column(name = "canocization_date", nullable = false)
    private LocalDate canonizationDate;

    /**(o all e para definir que para todas as operações CRUD)
     *  cascade = CascadeType.ALL ->
     *  O JPA vai salvar o santo e automaticamente salvar o milagre junto,
     *  sem você precisar chamar miracleRepository.save(m1).
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "saint_id_fk")
    private List<Miracle> miracles;

}
