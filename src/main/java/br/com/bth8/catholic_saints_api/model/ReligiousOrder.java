package br.com.bth8.catholic_saints_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "religious_order")
public class ReligiousOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID religiousOrderId;

    @NotBlank
    @Column(nullable = false, length = 30, unique = true)
    private String founder;

    @NotBlank
    @Column(nullable = false, length = 30,unique = true)
    private String name;

    @Column(name = "foundation_date")
    private LocalDate foundationDate;

    /*
    cascade = CascadeType.ALL permite que, se você salvar um ReligiousOrder, os membros sejam salvos automaticamente.
    orphanRemoval = true garante que, se um ConsecratedPerson for removido da lista, ele também será deletado do banco.
     */
    @OneToMany(mappedBy = "religiousOrder",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsecratedPerson> menbers;

    @ElementCollection
    @Column(name = "vow")
    private List<String> vows;

}
