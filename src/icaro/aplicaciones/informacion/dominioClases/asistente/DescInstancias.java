//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.11.26 at 01:54:43 AM CET 
//


package icaro.aplicaciones.informacion.dominioClases.asistente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DescInstancias complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DescInstancias">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Gestores" type="{urn:icaro:aplicaciones:descripcionOrganizaciones}Gestores"/>
 *         &lt;element name="AgentesAplicacion" type="{urn:icaro:aplicaciones:descripcionOrganizaciones}AgentesAplicacion"/>
 *         &lt;element name="RecursosAplicacion" type="{urn:icaro:aplicaciones:descripcionOrganizaciones}RecursosAplicacion"/>
 *         &lt;element name="nodoComun" type="{urn:icaro:aplicaciones:descripcionOrganizaciones}Nodo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DescInstancias", propOrder = {
    "gestores",
    "agentesAplicacion",
    "recursosAplicacion",
    "nodoComun"
})
public class DescInstancias {

    @XmlElement(name = "Gestores", required = true)
    protected Gestores gestores;
    @XmlElement(name = "AgentesAplicacion", required = true)
    protected AgentesAplicacion agentesAplicacion;
    @XmlElement(name = "RecursosAplicacion", required = true)
    protected RecursosAplicacion recursosAplicacion;
    @XmlElement(required = true)
    protected Nodo nodoComun;

    /**
     * Gets the value of the gestores property.
     * 
     * @return
     *     possible object is
     *     {@link Gestores }
     *     
     */
    public Gestores getGestores() {
        return gestores;
    }

    /**
     * Sets the value of the gestores property.
     * 
     * @param value
     *     allowed object is
     *     {@link Gestores }
     *     
     */
    public void setGestores(Gestores value) {
        this.gestores = value;
    }

    /**
     * Gets the value of the agentesAplicacion property.
     * 
     * @return
     *     possible object is
     *     {@link AgentesAplicacion }
     *     
     */
    public AgentesAplicacion getAgentesAplicacion() {
        return agentesAplicacion;
    }

    /**
     * Sets the value of the agentesAplicacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link AgentesAplicacion }
     *     
     */
    public void setAgentesAplicacion(AgentesAplicacion value) {
        this.agentesAplicacion = value;
    }

    /**
     * Gets the value of the recursosAplicacion property.
     * 
     * @return
     *     possible object is
     *     {@link RecursosAplicacion }
     *     
     */
    public RecursosAplicacion getRecursosAplicacion() {
        return recursosAplicacion;
    }

    /**
     * Sets the value of the recursosAplicacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecursosAplicacion }
     *     
     */
    public void setRecursosAplicacion(RecursosAplicacion value) {
        this.recursosAplicacion = value;
    }

    /**
     * Gets the value of the nodoComun property.
     * 
     * @return
     *     possible object is
     *     {@link Nodo }
     *     
     */
    public Nodo getNodoComun() {
        return nodoComun;
    }

    /**
     * Sets the value of the nodoComun property.
     * 
     * @param value
     *     allowed object is
     *     {@link Nodo }
     *     
     */
    public void setNodoComun(Nodo value) {
        this.nodoComun = value;
    }

}
