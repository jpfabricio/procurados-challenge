package br.com.fiap.procurados.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "classificacao")
@SequenceGenerator(name = "classificacao", sequenceName = "SQ_T_CLASSIFICACAO", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
public class Classificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classificacao")
    private int id;

    private String classificacao;

    @Column(name = "ano_publicacao")
    private String anoPublicacao;

    @Column(length = 3000)
    private String detalhes;

    @OneToOne
    @JoinColumn(name = "procurado_id", referencedColumnName = "id")
    private Procurado procurado;

    public Classificacao(String classificacao, String anoPublicacao, String detalhes, Procurado procurado) {
        this.classificacao = classificacao != null ? classificacao : "dado não registrado";
        this.anoPublicacao = anoPublicacao != null ? anoPublicacao : "dado não registrado";
        this.detalhes = detalhes != null ? detalhes : "dado não registrado";
        this.procurado = procurado;
    }
}
