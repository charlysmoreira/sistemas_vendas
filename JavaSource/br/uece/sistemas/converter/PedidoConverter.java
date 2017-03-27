package br.uece.sistemas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.uece.sistemas.model.Pedido;

public class PedidoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty()) {
			return null;
		} else {
			Pedido pedido = new Pedido();
			pedido.setId(Long.valueOf(arg2));
			return pedido;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null || !(arg2 instanceof Pedido)) {
			return "";
		} else {
			return ((Pedido) arg2).getId().toString();
		}
	}

}
