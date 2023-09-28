package br.com.fiap.procurados.model;

import br.com.fiap.procurados.DTO.fbi.ProcuradoFbiDTO;
import br.com.fiap.procurados.enuns.Origem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "procurado")
@SequenceGenerator(name = "procurado", sequenceName = "SQ_T_PROCURADO", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
public class Procurado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "procurado")
    private int id;

    private String nome;

    private String sexo;

    private String raca;

    private String nascimento;

    @Column(name = "id_fbi")
    private String idFbi;

    @Column(name = "id_interpol")
    private String idInterpol;

    @Enumerated(EnumType.STRING)
    private Origem origem;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "procurado")
    private Caracteristica caracteristicas;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "procurado")
    private Classificacao classificacao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procurado")
    private List<Imagem> imagens;

    public Procurado(ProcuradoFbiDTO procuradoFbiDTO) {
        this.nome = procuradoFbiDTO.getNome() != null ? procuradoFbiDTO.getNome() : "dado não registrado";
        this.sexo = procuradoFbiDTO.getSexo() != null ? procuradoFbiDTO.getSexo() : "dado não registrado";
        this.raca = procuradoFbiDTO.getRaca() != null ? procuradoFbiDTO.getRaca() : "dado não registrado";
        this.nascimento = (procuradoFbiDTO.getDataAniversario() != null && !procuradoFbiDTO.getDataAniversario().isEmpty()) ? procuradoFbiDTO.getDataAniversario().get(0) : "dado não registrado";
        this.idFbi = procuradoFbiDTO.getIdFbi() != null ? procuradoFbiDTO.getIdFbi() : "dado não registrado";
        this.origem = Origem.FBI;
        criaCaracteristica(procuradoFbiDTO);
        criaClassificacao(procuradoFbiDTO);
    }

//    public Procurado(ProcuradoInterpolDTO procuradoInterpolDTO) {
//        this.nome = procuradoInterpolDTO.getNome() != null ? procuradoInterpolDTO.getNome().concat(" ").concat(procuradoInterpolDTO.getSobrenome()) : "dado não registrado";
//        this.sexo = "dado não registrado";
//        this.raca = "dado não registrado";
//        this.nascimento = procuradoInterpolDTO.getDataNascimento() != null ? procuradoInterpolDTO.getDataNascimento() : "dado não registrado";
//        this.idInterpol = procuradoInterpolDTO.getIdInterpol() != null ? procuradoInterpolDTO.getIdInterpol() : "dado não registrado";
//        this.origem = Origem.INTERPOL;
//        this.caracteristicas = new Caracteristica(
//                procuradoInterpolDTO.getNacionalidades() != null ?  procuradoInterpolDTO.getNacionalidades().get(0) : "dado não registrado",
//                "dado não registrado",
//                "dado não registrado",
//                "dado não registrado",
//                "dado não registrado",
//                this);
//        this.classificacao = new Classificacao(
//                "dado não registrado",
//                "dado não registrado",
//                "dado não registrado",
//                this);
//    }

    public void criaCaracteristica(ProcuradoFbiDTO procurado){
        this.caracteristicas = new Caracteristica(
                procurado.getNacionalidade(),
                (procurado.getLingua() != null && !procurado.getLingua().isEmpty()) ? procurado.getLingua().get(0) : "dado não registrado",
                procurado.getCorDosOlhos(),
                String.valueOf(procurado.getAltura()),
                procurado.getPeso(),
                this);
    }

    public void criaClassificacao(ProcuradoFbiDTO procurado){
        this.classificacao = new Classificacao(
                procurado.getClassificacaoDaPessoa(),
                procurado.getDataPublicacao(),
                procurado.getDescricao(),
                this);
    }
}
