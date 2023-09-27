package br.com.fiap.procurados.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "classificacao")
@SequenceGenerator(name = "classificacao", sequenceName = "SQ_T_CLASSIFICACAO", allocationSize = 1)
@Data
public class Classificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classificacao")
    private int id;

    private String origem;

    private String classificacao;

    @Column(name = "ano_publicacao")
    private LocalDateTime anoPublicacao;

    private String detalhes;

    @OneToOne
    @JoinColumn(name = "procurado_id", referencedColumnName = "id")
    private Procurado procurado;

    private void setAnoPublicacao(String anoPublicacao){
        this.anoPublicacao = LocalDateTime.parse(anoPublicacao);
    }
}
