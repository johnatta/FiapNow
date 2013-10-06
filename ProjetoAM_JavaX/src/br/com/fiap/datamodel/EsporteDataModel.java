package br.com.fiap.datamodel;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;
import br.com.fiap.entity.Esporte;

public class EsporteDataModel extends ListDataModel<Esporte> implements SelectableDataModel<Esporte> {

	/**
	 * Construtor padr�o
	 * @author Graziele Vasconcelos
	 */
	public EsporteDataModel() {	}

	/**
	 * M�todo construtor que recebe uma lista de esportes(objeto que ser� selecionado)
	 * @param lista de esportes 
	 * @author Graziele Vasconcelos
	 */	
	public EsporteDataModel(List<Esporte> esportes) {
		super(esportes);
	}

	/**
	 * Realiza a identifica��o da sele��o do usu�rio
	 * @param c�digo do esporte selecionado
	 * @return lista de esportes selecionados ou nulo caso n�o encontre na lista
	 * @author Graziele Vasconcelos
	 */
	@Override
	public Esporte getRowData(String rowKey) {
		List<Esporte> esps =  (List<Esporte>) getWrappedData();
		for(Esporte e : esps){
			int cod = Integer.parseInt(rowKey);
			if( e.getCodEsporte() == cod)
			return e;
		}
		return null;	
	}

	/**
	 * Recebe o objeto Esporte para converter para apenas os c�digos do mesmo
	 * para que quando o usu�rio selecione a aplica��o possa identificar posteriormente
	 * @param inst�ncia do objeto Esporte
	 * @return o c�digo do esporte para ser identificado posteriormente e para o selector efetuar seu processo 
	 * @author Graziele Vasconcelos
	 */
	@Override
	public Object getRowKey(Esporte e) {
		return e.getCodEsporte();
	}
}
