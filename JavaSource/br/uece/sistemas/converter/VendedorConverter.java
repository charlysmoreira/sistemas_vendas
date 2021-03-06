package br.uece.sistemas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.uece.sistemas.model.Vendedor;

public class VendedorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty()) {
			return null;
		} else {
			Vendedor vendedor = new Vendedor();
			vendedor.setId(Long.valueOf(arg2));
			return vendedor;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null || !(arg2 instanceof Vendedor)) {
			return "";
		} else {
			return ((Vendedor) arg2).getId().toString();
		}
	}

}
