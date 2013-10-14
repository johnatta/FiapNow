package br.com.fiap.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.fiap.entity.MensagemGrupo;

public class MensagemGrupoDataModel extends ListDataModel<MensagemGrupo> implements SelectableDataModel<MensagemGrupo> {

	/**
	 * Construtor padr�o
	 *
	 * @author Ariel Molina 
	 */
	public MensagemGrupoDataModel(){ }

	/**
	 * M�todo construtor para exibic�o na camada View
	 *
	 * @param data Mensagens Grupo que ser�o selecionadas
	 * @author Ariel Molina 
	 */
	public MensagemGrupoDataModel(List<MensagemGrupo> data){
		super(data);
	}


	/**
	 * Seleciona a MensagemGrupo conforme a sele��o
	 *
	 * @param rowKey Chave da MensagemGrupo selecionada
	 * @author Ariel Molina 
	 */
	@Override  
	public MensagemGrupo getRowData(String rowKey) {  
		//In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  

		List<MensagemGrupo> msgsGrupo = (List<MensagemGrupo>) getWrappedData();  

		for(MensagemGrupo mg : msgsGrupo) {  
			if(mg.getCodMensagem() == Integer.parseInt(rowKey)){
				return mg;
			}
		}  

		return null;  
	}  

	/**
	 * Seleciona a mensagemGrupo
	 *
	 * @param mensagemGrupo Mensagem a ser selcionada
	 * @return mensagemGrupo selecionada a partir do par�metro
	 * @author Ariel Molina 
	 */
	@Override  
	public Object getRowKey(MensagemGrupo mensagemGrupo) {  
		return mensagemGrupo.getCodMensagem();  
	}	


}
