package com.tcg.userprovider.configuration;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 14861
 * @date 2020/5/31
 */
@Component
@ConfigurationProperties(prefix = "multi-mail-sources")
public class EmailConfiguration {
    private Map<String, Map<String, String>> mails;

    public Map<String, Map<String, String>> getMails() {
        return mails;
    }

    public void setMails(Map<String, Map<String, String>> mails) {
        this.mails = mails;
    }
}
