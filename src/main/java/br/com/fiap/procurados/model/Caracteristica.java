package br.com.fiap.procurados.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "caracteristica")
@SequenceGenerator(name = "caracteristica", sequenceName = "SQ_T_CARACTERISTICA", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
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

    public Caracteristica(String nacionalidade, String idioma, String corDoOlho, String altura, String peso,
                          Procurado procurado) {
        this.nacionalidade = nacionalidade != null ? nacionalidade : "dado não registrado";
        this.idioma = idioma != null ? idioma : "dado não registrado";
        this.corDoOlho = corDoOlho != null ? corDoOlho : "dado não registrado";
        this.altura = altura != null ? altura : "dado não registrado";
        this.peso = peso != null ? peso : "dado não registrado";
        this.procurado = procurado;
    }
}
