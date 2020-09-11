package locadoraveiculos;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import locadoraveiculos.DataInvalidaException;
import locadoraveiculos.Caminhao;
import locadoraveiculos.Carro;
import locadoraveiculos.Cliente;
import locadoraveiculos.Locacao;
import locadoraveiculos.Moto;
import locadoraveiculos.PessoaFisica;
import locadoraveiculos.PessoaJuridica;
import locadoraveiculos.Veiculo;
import locadoraveiculos.Cambio;
import locadoraveiculos.Cilindrada;
import locadoraveiculos.NumeroPortas;
import locadoraveiculos.TipoCaminhao;
import locadoraveiculos.TipoCarro;
import locadoraveiculos.TipoCombustivel;
import locadoraveiculos.TipoDocumento;

// Classe Singleton, que tem somente uma única instância.
// Representa o banco de dados em memória de nossa aplicação
public class BancoDados {

	// Singleton item 1
	// instância da própria classe
	private static BancoDados unicaInstancia;

	private List<Cliente> clientes;
	private List<Locacao> locacoes;
	private List<Veiculo> veiculos;

	// Singleton item 2
	// construtor privado, ninguém deve instanciá-lo de
	// fora da própria classe
	private BancoDados() {
		Veiculo carro = new Carro("AAA1234", "PEUGEOT", "203", 0f, TipoCombustivel.FLEX, TipoCarro.HATCH, Cambio.MANUAL,
				NumeroPortas.QUATRO_PORTAS);
		Veiculo caminhao = new Caminhao("AAA1235", "VOLVO", "V1", 0, TipoCombustivel.DIESEL, TipoCaminhao.TRES_QUARTOS,
				Cambio.AUTOMATICO);
		Veiculo moto = new Moto("AAA1236", "Kawasaki", "Ninja", 0, TipoCombustivel.GASOLINA, Cilindrada.CC1000);

		Cliente pessoaFisica = new PessoaFisica("Fulano da Silva", TipoDocumento.CPF, "001.002.003-45");
		Cliente pessoaJuridica = new PessoaJuridica("Empresa Teste", "12.345.678/0001-12");

		Locacao locacao1 = null;
		Locacao locacao2 = null;

		try {
			locacao1 = new Locacao(pessoaFisica, moto, LocalDate.now().minusDays(14));
//			locacao1.encerrar(956.7f);
			locacao2 = new Locacao(pessoaJuridica, caminhao, LocalDate.now().minusMonths(1));
//			locacao2.encerrar(4983.9f);
		} catch (DataInvalidaException e) {
			e.printStackTrace();
		}

		this.clientes = new LinkedList<>(Arrays.asList(pessoaFisica, pessoaJuridica));
		this.veiculos = new LinkedList<>(Arrays.asList(carro, caminhao, moto));
		this.locacoes = new LinkedList<>(Arrays.asList(locacao1, locacao2));
	}

	// Singleton item 3
	// Método responsável por instanciar e retornar
	// a instância única do singleton
	public static BancoDados getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new BancoDados();
		}

		return unicaInstancia;
	}

	public void incluirVeiculo(Veiculo veiculo) {
		veiculos.add(veiculo);
	}

	public List<Veiculo> listarTodosVeiculos() {
		return veiculos;
	}

	public void adicionarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public List<Cliente> listarTodosClientes() {
		return clientes;
	}

	public Cliente selecionarCliente(int indice) {
		return clientes.get(indice);
	}

	public Veiculo selecionarVeiculo(int indice) {
		return veiculos.get(indice);
	}

	public void adicionarLocacao(Locacao locacao) {
		locacoes.add(locacao);
	}

	public List<Locacao> listarTodasLocacoes() {
		return locacoes;
	}

	public Locacao selecionarLocacao(int indice) {
		return locacoes.get(indice);
	}

//	public List<Locacao> listarLocacoesAtivas() {
//		List<Locacao> locacoesAtivas = new LinkedList<>();
//		
//		for (Locacao locacao : locacoes) {
//			
//			if (locacao.isAtiva()) {
//				locacoesAtivas.add(locacao);
//			}
//		}
//		
//		return locacoesAtivas;
//	}
}
