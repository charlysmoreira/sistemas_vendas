package br.uece.sistemas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.uece.sistemas.model.Transportadora;

public class TransportadoraConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty()) {
			return null;
		} else {
			Transportadora transportadora = new Transportadora();
			transportadora.setId(Long.valueOf(arg2));
			return transportadora;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null || !(arg2 instanceof Transportadora)) {
			return "";
		} else {
			return ((Transportadora) arg2).getId().toString();
		}
	}

}
