package pojo;

import java.io.Serializable;
import java.util.Date;

@Table(name = "`persistent_logins`")
public class PersistentLogins implements Serializable {
    @Id
    @Column(name = "`series`")
    private String series;

    @Column(name = "`username`")
    private String username;

    @Column(name = "`token`")
    private String token;

    @Column(name = "`last_used`")
    private Date lastUsed;

    private static final long serialVersionUID = 1L;

    /**
     * @return series
     */
    public String getSeries() {
        return series;
    }

    /**
     * @param series
     */
    public void setSeries(String series) {
        this.series = series;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return last_used
     */
    public Date getLastUsed() {
        return lastUsed;
    }

    /**
     * @param lastUsed
     */
    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}
