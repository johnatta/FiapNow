package br.com.fiap.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.fiap.entity.MensagemEvento;

public class MensagemEventoDataModel extends ListDataModel<MensagemEvento> implements SelectableDataModel<MensagemEvento> {

	/**
	 * Construtor padrão
	 *
	 * @author Ariel Molina 
	 */
	public MensagemEventoDataModel(){ }

	/**
	 * Método construtor para exibicão na camada View
	 *
	 * @param data Mensagens Grupo que serão selecionadas
	 * @author Ariel Molina 
	 */
	public MensagemEventoDataModel(List<MensagemEvento> data){
		super(data);
	}

	/**
	 * Seleciona a MensagemEvento conforme a seleção
	 *
	 * @param rowKey Chave da MensagemEvento selecionada
	 * @author Ariel Molina 
	 */
	@Override  
	public MensagemEvento getRowData(String rowKey) {  
		//In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  

		List<MensagemEvento> msgsEvento = (List<MensagemEvento>) getWrappedData();  

		for(MensagemEvento mg : msgsEvento) {  
			if(mg.getCodMensagem() == Integer.parseInt(rowKey)){
				return mg;
			}
		}  

		return null;  
	}  

	/**
	 * Seleciona a mensagemEvento
	 *
	 * @param mensagemEvento Mensagem a ser selcionada
	 * @return mensagemEvento selecionada a partir do parâmetro
	 * @author Ariel Molina 
	 */
	@Override  
	public Object getRowKey(MensagemEvento mensagemEvento) {  
		return mensagemEvento.getCodMensagem();  
	}	


}
