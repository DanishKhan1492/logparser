package com.elixirtask.parselogs.dto;

import java.util.Date;

/**
 * @author Muhammad Danish Khan
 * @created 4/12/20 - 12:27 AM
 */
public class LogDto {

    private final String url;
    private final Date dateTime;
    private final String requestType;
    private String resourceUrl;
    private final Integer status;

    public LogDto(String url, Date dateTime, String requestType, Integer status) {
        this.url = url;
        this.dateTime = dateTime;
        this.requestType = requestType;
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }
    public Integer getStatus() {
        return status;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }
}
