package br.com.bth8.catholic_saints_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Table(name = "miracles")
public class Miracle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID miracleId;

    @NotBlank
    @Column(nullable = false, length = 70)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author")
    private Saint author;

    @NotBlank
    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private  String location;

}
