package br.com.fiap.procurados.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "imagem")
@SequenceGenerator(name = "imagem", sequenceName = "SQ_T_IMAGEM", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imagem")
    private int id;

    @Column(length = 3000)
    private String legenda;

    private String link;

    @ManyToOne
    @JoinColumn(name = "procurado_id", referencedColumnName = "id")
    private Procurado procurado;

    public Imagem(String legenda, String link, Procurado procurado) {
        this.legenda = legenda != null ? legenda : "dado não registrado";
        this.link = link != null ? link : "dado não registrado";
        this.procurado = procurado;
    }
}
