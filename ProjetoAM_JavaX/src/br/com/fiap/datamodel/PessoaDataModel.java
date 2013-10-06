package br.com.fiap.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.fiap.entity.Pessoa;

public class PessoaDataModel extends ListDataModel<Pessoa> implements SelectableDataModel<Pessoa> {

	/**
	 * Construtor padr�o
	 * @author Graziele Vasconcelos
	 */
	public PessoaDataModel(){	
	}
	
	/**
	 * M�todo construtor que recebe uma lista de pessoas(objeto que ser� selecionado)
	 * @param lista de pessoas 
	 * @author Graziele Vasconcelos
	 */		
	public PessoaDataModel(List<Pessoa> pessoas) {
		super(pessoas);
	}

	/**
	 * Realiza a identifica��o da sele��o do usu�rio
	 * @param c�digo da pessoa selecionada
	 * @return lista de pessoas selecionadas ou nulo caso n�o encontre na lista
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
	 * Recebe o objeto Pessoa para converter para apenas os c�digos do mesmo
	 * para que quando o usu�rio selecione a aplica��o possa identificar posteriormente
	 * @param inst�ncia do objeto Pessoa
	 * @return o c�digo da pessoa para ser identificado posteriormente e para o selector efetuar seu processo 
	 * @author Graziele Vasconcelos
	 */
	@Override
	public Object getRowKey(Pessoa p) {
		return p.getCodPessoa();
	}
}
