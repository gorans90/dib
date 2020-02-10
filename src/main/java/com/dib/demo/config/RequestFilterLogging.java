package com.dib.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RequestFilterLogging extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        long timeInitiated = System.currentTimeMillis();
        try {
            createRequestLog(request);
            chain.doFilter(request, response);
        } finally {
            String requestBody = getRequestBody(request);
            String responseBody = getResponseBody(response);
            createResponseLog(request, response, requestBody, responseBody, timeInitiated);
        }
    }

    private String getRequestBody(HttpServletRequest requestWrapper) throws UnsupportedEncodingException {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(requestWrapper, ContentCachingRequestWrapper.class);
        if (wrapper == null) {
            return null;
        }
        byte[] buf = wrapper.getContentAsByteArray();
        if (buf.length == 0) {
            return null;
        }
        String requestBody = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
        return requestBody;
    }

    private String getResponseBody(HttpServletResponse responseWrapper) throws IOException {
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(responseWrapper, ContentCachingResponseWrapper.class);
        if (wrapper == null) {
            return null;
        }
        if (MediaType.APPLICATION_OCTET_STREAM_VALUE.equalsIgnoreCase(wrapper.getContentType())
                || MediaType.APPLICATION_PDF_VALUE.equalsIgnoreCase(wrapper.getContentType())
                || "application/xls".equalsIgnoreCase(wrapper.getContentType())
                || "application/csv".equalsIgnoreCase(wrapper.getContentType())) {
            wrapper.copyBodyToResponse();
            return null;
        }

        byte[] buf = wrapper.getContentAsByteArray();
        if (buf.length == 0) {
            wrapper.copyBodyToResponse();
            return null;
        }
        String responseBody = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
        wrapper.copyBodyToResponse();
        return responseBody;
    }

    private void createRequestLog(HttpServletRequest requestWrapper) {
        if (!checkIfIsSwaggerUrl(requestWrapper.getRequestURL().toString())) {

            StringBuilder logBuilder = new StringBuilder();
            logBuilder.append("\nREQUEST: " + DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()));
            logBuilder.append("\nAddress: ").append(requestWrapper.getRequestURL());
            if (StringUtils.isNotBlank(requestWrapper.getQueryString())) {
                logBuilder.append("?" + requestWrapper.getQueryString());
            }
            logBuilder.append("\nHttp-Method: ").append(requestWrapper.getMethod());
            logBuilder.append("\nContent-Type : ").append(requestWrapper.getContentType());
            if (StringUtils.isNotBlank(requestWrapper.getRemoteAddr())) {
                logBuilder.append("\nClient=").append(requestWrapper.getRemoteAddr());
            }
            HttpSession session = requestWrapper.getSession(false);
            if (session != null) {
                logBuilder.append("\nSession=").append(session.getId());
            }
            if (StringUtils.isNotBlank(requestWrapper.getRemoteUser())) {
                logBuilder.append("\nUser=").append(requestWrapper.getRemoteUser());
            }
            logBuilder.append("\nHeaders: ").append(Collections.list(requestWrapper.getHeaderNames()).stream()
                    .collect(Collectors.toMap(
                            headerName -> headerName,
                            headerName -> requestWrapper.getHeader(headerName),
                            (headerName1, headerName2) -> {
                                return headerName1;
                            }
                    )));
            log.info(logBuilder.toString());
        }
    }

    private void createResponseLog(HttpServletRequest requestWrapper, HttpServletResponse responseWrapper, String requestBody, String responseBody, long timeInitiated) {
        if (!checkIfIsSwaggerUrl(requestWrapper.getRequestURL().toString())) {
            StringBuilder responseLog = new StringBuilder();
            responseLog.append("\nRESPONSE: " + DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()));
            responseLog.append("\nDuration: ").append(String.valueOf(System.currentTimeMillis() - timeInitiated).concat(" ms"));
            responseLog.append("\nAddress: ").append(requestWrapper.getRequestURL());
            if (StringUtils.isNotBlank(requestWrapper.getQueryString())) {
                responseLog.append("?" + requestWrapper.getQueryString());
            }
            responseLog.append("\nResponse-Code: ").append(responseWrapper.getStatus());
            responseLog.append("\nContent-Type: ").append(responseWrapper.getContentType());
            responseLog.append("\nHeaders: ").append(responseWrapper.getHeaderNames().stream()
                    .collect(Collectors.toMap(
                            headerName -> headerName,
                            headerName -> responseWrapper.getHeader(headerName),
                            (headerName1, headerName2) -> {
                                return headerName1;
                            }
                    )));
            if (StringUtils.isNotBlank(requestBody)) {
                responseLog.append("\nRequestBody: ").append(requestBody);
            }
            if (StringUtils.isNotBlank(responseBody) && log.isDebugEnabled()) {
                responseLog.append(" ResponseBody: " + responseBody);
            }
            log.info(responseLog.toString());
        }
    }

    private boolean checkIfIsSwaggerUrl(String url) {
        return url.contains("swagger");
    }
}
