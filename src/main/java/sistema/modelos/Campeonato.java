package sistema.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="campeonatos")
@NamedQuery(name = "Campeonato.pesquisarPorNome", query = "select u from Campeonato u where u.nome = :nome")
public class Campeonato implements Serializable{
	
	private static final long serialVersionUID = -345530919306581961L;

	public static final String PESQUISAR_POR_NOME = "Campeonato.pesquisarPorNome";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id_campeonato;
	
	private String nome;
	
	@OneToMany(mappedBy="campeonato")
	private ArrayList<Categoria> categorias = new ArrayList<Categoria>();

	private Calendar data_inicio = new GregorianCalendar();
	private Calendar data_fim = new GregorianCalendar();
	private Calendar data_insc_inicio = new GregorianCalendar();
	private Calendar data_insc_fim = new GregorianCalendar();
	
	private double taxa_insc;
	private int jogadores_min;
	private int jogadores_max;
	
	public int getId() {
		return id_campeonato;
	}
	public void setId(int id) {
		this.id_campeonato = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ArrayList<Categoria> getCategorias() {
		return categorias;
	}
	public void addCategorias(Categoria categoria) {
		this.categorias.add(categoria);
	}
	public Calendar getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(Calendar data_inicio) {
		this.data_inicio = data_inicio;
	}
	public Calendar getData_fim() {
		return data_fim;
	}
	public void setData_fim(Calendar data_fim) {
		this.data_fim = data_fim;
	}
	public Calendar getData_insc_inicio() {
		return data_insc_inicio;
	}
	public void setData_insc_inicio(Calendar data_insc_inicio) {
		this.data_insc_inicio = data_insc_inicio;
	}
	public Calendar getData_insc_fim() {
		return data_insc_fim;
	}
	public void setData_insc_fim(Calendar data_insc_fim) {
		this.data_insc_fim = data_insc_fim;
	}
	public double getTaxa_insc() {
		return taxa_insc;
	}
	public void setTaxa_insc(double taxa_insc) {
		this.taxa_insc = taxa_insc;
	}
	public int getJogadores_min() {
		return jogadores_min;
	}
	public void setJogadores_min(int jogadores_min) {
		this.jogadores_min = jogadores_min;
	}
	public int getJogadores_max() {
		return jogadores_max;
	}
	public void setJogadores_max(int jogadores_max) {
		this.jogadores_max = jogadores_max;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_campeonato;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Campeonato other = (Campeonato) obj;
		if (id_campeonato != other.id_campeonato)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
