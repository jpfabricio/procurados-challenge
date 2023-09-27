package br.com.fiap.procurados.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "caracteristica")
@SequenceGenerator(name = "caracteristica", sequenceName = "SQ_T_CARACTERISTICA", allocationSize = 1)
@Data
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caracteristica")
    private int id;

    private String nacionalidade;

    private String idioma;

    @Column(name = "cor_do_olho")
    private String corDoOlho;

    private String altura;

    private String peso;

    @OneToOne
    @JoinColumn(name = "procurado_id", referencedColumnName = "id")
    private Procurado procurado;

}
