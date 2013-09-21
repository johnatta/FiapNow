package br.com.fiap.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.fiap.entity.Pessoa;

public class PessoaDataModel extends ListDataModel<Pessoa> implements SelectableDataModel<Pessoa> {

	public PessoaDataModel(List<Pessoa> pessoas) {
		super(pessoas);
	}
	
	public PessoaDataModel(){
		
	}
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

	@Override
	public Object getRowKey(Pessoa p) {
		return p.getCodPessoa();
	}
}
