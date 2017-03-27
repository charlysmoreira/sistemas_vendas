package br.uece.sistemas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.uece.sistemas.model.Tecnico;

public class TecnicoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty()) {
			return null;
		} else {
			Tecnico tecnico = new Tecnico();
			tecnico.setId(Long.valueOf(arg2));
			return tecnico;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null || !(arg2 instanceof Tecnico)) {
			return "";
		} else {
			return ((Tecnico) arg2).getId().toString();
		}
	}

}
