package icaro.aplicaciones.SIDEMA.informacion;

import java.io.Serializable;

public class InformacionAgente implements Serializable {

	public String identEmisor;
	public String mensajeOrden;
	public Object justificacion;

	public InformacionAgente() {
	}

	public InformacionAgente(String identAgteEmisor) {
		identEmisor = identAgteEmisor;
		mensajeOrden = null;
		justificacion = null;

	}

	public InformacionAgente(String identAgteEmisor, String msgOrden,
			Object justificat) {
		identEmisor = identAgteEmisor;
		mensajeOrden = msgOrden;
		justificacion = justificat;

	}

	public void setMensajeOrden(String msgOrden) {
		mensajeOrden = msgOrden;
	}

	public String getMensajeOrden() {
		return mensajeOrden;
	}

	public String getIdentEmisor() {
		return identEmisor;
	}

	public void setJustificacion(Object contJustificacion) {
		justificacion = contJustificacion;
	}

	public Object getJustificacion() {
		return justificacion;
	}

	@Override
	public String toString() {
		if (justificacion == null)
			return "Agente Emisor :" + identEmisor + " MensajeOrden :+"
					+ mensajeOrden + "  Justificacion: null " + "\n ";
		else
			return "Agente Emisor :" + identEmisor + " MensajeOrden :+"
					+ mensajeOrden + "  Justificacion: "
					+ justificacion.toString() + "\n ";
	}
}
