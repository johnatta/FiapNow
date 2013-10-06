package br.com.fiap.datamodel;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;
import br.com.fiap.entity.Esporte;

public class EsporteDataModel extends ListDataModel<Esporte> implements SelectableDataModel<Esporte> {

	/**
	 * Construtor padrão
	 * @author Graziele Vasconcelos
	 */
	public EsporteDataModel() {	}

	public EsporteDataModel(List<Esporte> esportes) {
		super(esportes);
	}

	/**
	 * Realiza a identificação da seleção do usuário
	 * @param código do esporte selecionado
	 * @return lista de esportes selecionados ou nulo caso não encontre na lista
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
	 * Recebe o objeto Esporte para converter para apenas os códigos do mesmo
	 * para que quando o usuário selecione a aplicação possa identificar posteriormente
	 * @param instância do objeto Esporte
	 * @return o código do esporte para ser identificado posteriormente e para o selector efetuar seu processo 
	 * @author Graziele Vasconcelos
	 */
	@Override
	public Object getRowKey(Esporte e) {
		return e.getCodEsporte();
	}
}
