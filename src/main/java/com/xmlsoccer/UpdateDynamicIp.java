
package com.xmlsoccer;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ApiKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "apiKey",
        "email",
        "newIp"
})
@XmlRootElement(name = "UpdateDynamicIp")
public class UpdateDynamicIp {

    @XmlElement(name = "ApiKey")
    protected String apiKey;
    protected String email;
    protected String newIp;

    /**
     * Gets the value of the apiKey property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Sets the value of the apiKey property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setApiKey(String value) {
        this.apiKey = value;
    }

    /**
     * Gets the value of the email property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the newIp property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getNewIp() {
        return newIp;
    }

    /**
     * Sets the value of the newIp property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNewIp(String value) {
        this.newIp = value;
    }

}
