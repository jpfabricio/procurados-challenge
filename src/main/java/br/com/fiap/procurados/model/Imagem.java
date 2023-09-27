package br.com.fiap.procurados.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "imagem")
@SequenceGenerator(name = "imagem", sequenceName = "SQ_T_IMAGEM", allocationSize = 1)
@Data
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imagem")
    private int id;

    private String legenda;

    private String link;

    @ManyToOne
    @JoinColumn(name = "procurado_id", referencedColumnName = "id")
    private Procurado procurado;

}
