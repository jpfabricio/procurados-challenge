package br.com.fiap.procurados.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "procurado")
@SequenceGenerator(name = "procurado", sequenceName = "SQ_T_PROCURADO", allocationSize = 1)
@Data
public class Procurado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicamento")
    private int id;

    private String nome;

    private String sexo;

    private String raca;

    private String nascimento;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "procurado")
    private Caracteristica caracteristicas;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "procurado")
    private Classificacao classificacao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procurado")
    private List<Imagem> imagens;
}
