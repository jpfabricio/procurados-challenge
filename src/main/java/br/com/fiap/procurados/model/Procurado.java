package br.com.fiap.procurados.model;

import br.com.fiap.procurados.DTO.fbi.ProcuradoFbiDTO;
import br.com.fiap.procurados.DTO.interpol.ProcuradoInterpolDTO;
import br.com.fiap.procurados.enuns.Origem;
import com.google.gson.annotations.Expose;
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
    @Expose
    private int id;

    @Expose
    private String nome;

    @Expose
    private String sexo;

    @Expose
    private String raca;

    @Expose
    private String nascimento;

    @Expose
    @Column(name = "id_fbi")
    private String idFbi;

    @Expose
    @Column(name = "id_interpol")
    private String idInterpol;

    @Expose
    @Enumerated(EnumType.STRING)
    private Origem origem;

    @Expose
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "procurado")
    private Caracteristica caracteristicas;

    @Expose
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "procurado")
    private Classificacao classificacao;

    @Expose
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procurado", fetch = FetchType.EAGER)
    private List<Imagem> imagens;

    public Procurado(ProcuradoFbiDTO procuradoFbiDTO) {
        this.nome = procuradoFbiDTO.getNome() != null ? procuradoFbiDTO.getNome() : "dado não registrado";
        this.sexo = procuradoFbiDTO.getSexo() != null ? procuradoFbiDTO.getSexo() : "dado não registrado";
        this.raca = procuradoFbiDTO.getRaca() != null ? procuradoFbiDTO.getRaca() : "dado não registrado";
        this.nascimento = (procuradoFbiDTO.getDataAniversario() != null && !procuradoFbiDTO.getDataAniversario().isEmpty()) ? procuradoFbiDTO.getDataAniversario().get(0) : "dado não registrado";
        this.idFbi = procuradoFbiDTO.getIdFbi() != null ? procuradoFbiDTO.getIdFbi() : "dado não registrado";
        this.origem = Origem.FBI;
        criaCaracteristicaFbi(procuradoFbiDTO);
        criaClassificacaoFbi(procuradoFbiDTO);
    }

    public Procurado(ProcuradoInterpolDTO procuradoInterpolDTO) {
        this.nome = procuradoInterpolDTO.getNome() != null ? procuradoInterpolDTO.getNome().concat(" ").concat(procuradoInterpolDTO.getSobrenome()) : "dado não registrado";
        this.sexo = "dado não registrado";
        this.raca = "dado não registrado";
        this.nascimento = procuradoInterpolDTO.getDataNascimento() != null ? procuradoInterpolDTO.getDataNascimento() : "dado não registrado";
        this.idInterpol = procuradoInterpolDTO.getIdInterpol() != null ? procuradoInterpolDTO.getIdInterpol() : "dado não registrado";
        this.origem = Origem.INTERPOL;
        criaCaracteristicaInterpol(procuradoInterpolDTO);
        criaClassificacaoInterpol();
    }

    public void criaCaracteristicaFbi(ProcuradoFbiDTO procurado){
        this.caracteristicas = new Caracteristica(
                procurado.getNacionalidade(),
                (procurado.getLingua() != null && !procurado.getLingua().isEmpty()) ? procurado.getLingua().get(0) : "dado não registrado",
                procurado.getCorDosOlhos(),
                String.valueOf(procurado.getAltura()),
                procurado.getPeso(),
                this);
    }

    public void criaCaracteristicaInterpol(ProcuradoInterpolDTO procuradoInterpolDTO){
        this.caracteristicas = new Caracteristica(
                procuradoInterpolDTO.getNacionalidades() != null ?  procuradoInterpolDTO.getNacionalidades().get(0) : "dado não registrado",
                "dado não registrado",
                "dado não registrado",
                "dado não registrado",
                "dado não registrado",
                this);
    }

    public void criaClassificacaoFbi(ProcuradoFbiDTO procurado){
        this.classificacao = new Classificacao(
                procurado.getClassificacaoDaPessoa(),
                procurado.getDataPublicacao(),
                procurado.getDescricao(),
                this);
    }

    public void criaClassificacaoInterpol(){
        this.classificacao = new Classificacao(
                "dado não registrado",
                "dado não registrado",
                "dado não registrado",
                this);
    }
}
