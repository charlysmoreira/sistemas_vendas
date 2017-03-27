package br.uece.sistemas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.uece.sistemas.model.OrdemServico;

public class OrdemServicoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty()) {
			return null;
		} else {
			OrdemServico ordemServico = new OrdemServico();
			ordemServico.setId(Long.valueOf(arg2));
			return ordemServico;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null || !(arg2 instanceof OrdemServico)) {
			return "";
		} else {
			return ((OrdemServico) arg2).getId().toString();
		}
	}

}
