package br.uece.sistemas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.uece.sistemas.model.FormaDePagamento;

public class FormaDePagamentoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty()) {
			return null;
		} else {
			FormaDePagamento formaDePagamento = new FormaDePagamento();
			formaDePagamento.setId(Long.valueOf(arg2));
			return formaDePagamento;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null || !(arg2 instanceof FormaDePagamento)) {
			return "";
		} else {
			return ((FormaDePagamento) arg2).getId().toString();
		}
	}

}
