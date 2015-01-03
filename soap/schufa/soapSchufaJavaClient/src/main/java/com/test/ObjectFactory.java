
package com.test;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.test package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetScoreValue_QNAME = new QName("http://vschufa.hfu.de/", "getScoreValue");
    private final static QName _GetScoreValueResponse_QNAME = new QName("http://vschufa.hfu.de/", "getScoreValueResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.test
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetScoreValue }
     * 
     */
    public GetScoreValue createGetScoreValue() {
        return new GetScoreValue();
    }

    /**
     * Create an instance of {@link GetScoreValueResponse }
     * 
     */
    public GetScoreValueResponse createGetScoreValueResponse() {
        return new GetScoreValueResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetScoreValue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vschufa.hfu.de/", name = "getScoreValue")
    public JAXBElement<GetScoreValue> createGetScoreValue(GetScoreValue value) {
        return new JAXBElement<GetScoreValue>(_GetScoreValue_QNAME, GetScoreValue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetScoreValueResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://vschufa.hfu.de/", name = "getScoreValueResponse")
    public JAXBElement<GetScoreValueResponse> createGetScoreValueResponse(GetScoreValueResponse value) {
        return new JAXBElement<GetScoreValueResponse>(_GetScoreValueResponse_QNAME, GetScoreValueResponse.class, null, value);
    }

}
