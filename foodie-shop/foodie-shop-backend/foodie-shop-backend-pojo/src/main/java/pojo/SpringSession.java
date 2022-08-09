package pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "`spring_session`")
public class SpringSession implements Serializable {
    @Id
    @Column(name = "`SESSION_ID`")
    private String sessionId;

    @Column(name = "`CREATION_TIME`")
    private Long creationTime;

    @Column(name = "`LAST_ACCESS_TIME`")
    private Long lastAccessTime;

    @Column(name = "`MAX_INACTIVE_INTERVAL`")
    private Integer maxInactiveInterval;

    @Column(name = "`PRINCIPAL_NAME`")
    private String principalName;

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
     * @return CREATION_TIME
     */
    public Long getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime
     */
    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * @return LAST_ACCESS_TIME
     */
    public Long getLastAccessTime() {
        return lastAccessTime;
    }

    /**
     * @param lastAccessTime
     */
    public void setLastAccessTime(Long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    /**
     * @return MAX_INACTIVE_INTERVAL
     */
    public Integer getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    /**
     * @param maxInactiveInterval
     */
    public void setMaxInactiveInterval(Integer maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }

    /**
     * @return PRINCIPAL_NAME
     */
    public String getPrincipalName() {
        return principalName;
    }

    /**
     * @param principalName
     */
    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }
}
