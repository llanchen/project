package pojo;

import java.io.Serializable;

@Table(name = "`spring_session_attributes`")
public class SpringSessionAttributes implements Serializable {
    @Id
    @Column(name = "`SESSION_ID`")
    private String sessionId;

    @Id
    @Column(name = "`ATTRIBUTE_NAME`")
    private String attributeName;

    @Column(name = "`ATTRIBUTE_BYTES`")
    private byte[] attributeBytes;

    private static final long serialVersionUID = 1L;

    /**
     * @return SESSION_ID
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return ATTRIBUTE_NAME
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * @param attributeName
     */
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    /**
     * @return ATTRIBUTE_BYTES
     */
    public byte[] getAttributeBytes() {
        return attributeBytes;
    }

    /**
     * @param attributeBytes
     */
    public void setAttributeBytes(byte[] attributeBytes) {
        this.attributeBytes = attributeBytes;
    }
}
