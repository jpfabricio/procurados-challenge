package br.com.fiap.procurados.model;

import com.google.gson.annotations.Expose;
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
    @Expose
    private int id;

    @Expose
    @Column(length = 3000)
    private String legenda;

    @Expose
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
