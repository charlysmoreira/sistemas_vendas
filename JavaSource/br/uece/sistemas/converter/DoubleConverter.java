package br.uece.sistemas.converter;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


public class DoubleConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1,
			String valorTela) {
		if (valorTela == null || valorTela.trim().isEmpty()) {
			return null;

		} else {
			String valorFormatado = valorTela.trim();
			
			try {
				valorFormatado = valorFormatado.replaceAll(Pattern.quote("."), "");
				
				valorFormatado = valorFormatado.replace(",", ".");
				
				Double doubleValue = new Double(valorFormatado);
				
				return doubleValue;

			} catch (Exception e) {
				return 0.0d;
			}
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1,
			Object valorTela) {
		if (valorTela == null || valorTela.toString().trim().equals("")) {
			return null;
		} else {
			NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);

			return nf.format(Double.valueOf(valorTela.toString()));
		}
	}
}
