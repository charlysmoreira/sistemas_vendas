package br.uece.sistemas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.uece.sistemas.model.Fornecedor;

public class FornecedorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty()) {
			return null;
		} else {
			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setId(Long.valueOf(arg2));
			return fornecedor;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null || !(arg2 instanceof Fornecedor)) {
			return "";
		} else {
			return ((Fornecedor) arg2).getId().toString();
		}
	}

}
