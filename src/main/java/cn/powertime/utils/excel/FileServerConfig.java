package cn.powertime.utils.excel;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhuyanwei
 */
@Configuration
@ConfigurationProperties(prefix = "iatp.fileserver")
public class FileServerConfig {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
