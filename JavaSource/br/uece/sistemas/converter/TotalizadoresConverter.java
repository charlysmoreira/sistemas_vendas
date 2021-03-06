package br.uece.sistemas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.uece.sistemas.model.Totalizadores;

public class TotalizadoresConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty()) {
			return null;
		} else {
			Totalizadores totalizadores = new Totalizadores();
			totalizadores.setId(Long.valueOf(arg2));
			return totalizadores;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null || !(arg2 instanceof Totalizadores)) {
			return "";
		} else {
			return ((Totalizadores) arg2).getId().toString();
		}
	}

}
