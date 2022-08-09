package common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LEGION
 * @date 2022/05/09 17:05
 **/
@Configuration
@ConfigurationProperties(prefix = "net.seehope")
@Data
public class ProjectProperties {
    public List<String> allowedOrigins = new ArrayList<>();
}
