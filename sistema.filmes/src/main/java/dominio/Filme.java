package dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_filme")
public class Filme implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;

	private String nome;
	private Integer duracao;
	private Integer ano;

	@OneToMany(mappedBy="filme")
	List<Participacao> participacoes;

	public Filme() {
		participacoes = new ArrayList<>();	
	}
	
	public Filme(Integer codigo, String nome, Integer duracao, Integer ano) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.duracao = duracao;
		this.ano = ano;
		this.participacoes = new ArrayList<>();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	

	public void addParticipacao(Participacao participacao) {
		this.participacoes.add(participacao);
		participacao.setFilme(this);
	}

	public void removeParticipacao(Participacao participacao) {
		this.participacoes.remove(participacao);
	}

	@Override
	public String toString() {
		return "Filme [codigo=" + codigo + ", nome=" + nome + ", duracao=" + duracao + ", ano=" + ano + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public BigDecimal cacheTotal() {
		BigDecimal soma = new BigDecimal("0.00");
		for(Participacao participacao : participacoes) {
			soma = soma.add(participacao.cachePago());
			System.out.println(participacao.cachePago());
		}
		return soma;
	}
}
