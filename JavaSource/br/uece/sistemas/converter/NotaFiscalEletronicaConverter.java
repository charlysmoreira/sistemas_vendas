package br.uece.sistemas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.uece.sistemas.model.NotaFiscalEletronica;

public class NotaFiscalEletronicaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty()) {
			return null;
		} else {
			NotaFiscalEletronica notaFiscalEletronica = new NotaFiscalEletronica();
			notaFiscalEletronica.setId(Long.valueOf(arg2));
			return notaFiscalEletronica;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null || !(arg2 instanceof NotaFiscalEletronica)) {
			return "";
		} else {
			return ((NotaFiscalEletronica) arg2).getId().toString();
		}
	}

}
