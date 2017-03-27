package br.uece.sistemas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.uece.sistemas.model.Cliente;

public class ClienteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty()) {
			return null;
		} else {
			Cliente cliente = new Cliente();
			cliente.setId(Long.valueOf(arg2));
			return cliente;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null || !(arg2 instanceof Cliente)) {
			return "";
		} else {
			return ((Cliente) arg2).getId().toString();
		}
	}

}
