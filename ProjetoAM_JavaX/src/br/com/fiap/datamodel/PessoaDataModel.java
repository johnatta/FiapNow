package br.com.fiap.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.fiap.entity.Pessoa;

public class PessoaDataModel extends ListDataModel<Pessoa> implements SelectableDataModel<Pessoa> {

	/**
	 * Construtor padrão
	 * @author Graziele Vasconcelos
	 */
	public PessoaDataModel(){	
	}
	
	/**
	 * Método construtor que recebe uma lista de pessoas(objeto que será selecionado)
	 * @param lista de pessoas 
	 * @author Graziele Vasconcelos
	 */		
	public PessoaDataModel(List<Pessoa> pessoas) {
		super(pessoas);
	}

	/**
	 * Realiza a identificação da seleção do usuário
	 * @param código da pessoa selecionada
	 * @return lista de pessoas selecionadas ou nulo caso não encontre na lista
	 * @author Graziele Vasconcelos
	 */
	@Override
	public Pessoa getRowData(String rowKey) {
		List<Pessoa> pes = (List<Pessoa>) getWrappedData(); 
		for(Pessoa p : pes){
			int cod = Integer.parseInt(rowKey);
			if (p.getCodPessoa() == cod)
				return p;
		}
		return null;
	}

	/**
	 * Recebe o objeto Pessoa para converter para apenas os códigos do mesmo
	 * para que quando o usuário selecione a aplicação possa identificar posteriormente
	 * @param instância do objeto Pessoa
	 * @return o código da pessoa para ser identificado posteriormente e para o selector efetuar seu processo 
	 * @author Graziele Vasconcelos
	 */
	@Override
	public Object getRowKey(Pessoa p) {
		return p.getCodPessoa();
	}
}
