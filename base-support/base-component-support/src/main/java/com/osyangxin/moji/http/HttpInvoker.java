package com.osyangxin.moji.http;

import com.osyangxin.moji.common.constants.Constants;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import javax.net.ssl.SSLContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class HttpInvoker {

    private static final int CONN_TIMEOUT_IN_MILLIS = 5 * 1000;
    private static final int READ_TIMEOUT_IN_MILLIS = 5 * 1000;

    private static final int MAX_TOTAL = 1000;

    private RestTemplate restTemplate;

    private HttpInvoker(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static RestTemplate getRestTemplate() {
        return InstanceHolder.httpInvoker.restTemplate;
    }

    private static class InstanceHolder {

        public static final HttpInvoker httpInvoker;

        public static CloseableHttpClient getHttpsClient() {
            CloseableHttpClient httpClient = null;
            try {
                SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
                    @Override
                    public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                        return true;
                    }
                }).build();
                httpClient = HttpClients.custom().setSSLContext(sslContext)
                        .setSSLHostnameVerifier(new NoopHostnameVerifier())
                        .setConnectionManager(poolingConnectionManager())
                        .build();
            } catch (Exception ex) {
                log.error("初始化SSLContext出错", ex);
            }
            if (httpClient == null) {
                httpClient = HttpClients.createDefault();
            }
            return httpClient;
        }

        public static HttpClientConnectionManager poolingConnectionManager() {
            PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager();
            poolingConnectionManager.setMaxTotal(MAX_TOTAL);
            poolingConnectionManager.setDefaultMaxPerRoute(MAX_TOTAL);
            return poolingConnectionManager;
        }

        static {
            HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
            httpRequestFactory.setConnectionRequestTimeout(CONN_TIMEOUT_IN_MILLIS);
            httpRequestFactory.setConnectTimeout(CONN_TIMEOUT_IN_MILLIS);
            httpRequestFactory.setReadTimeout(READ_TIMEOUT_IN_MILLIS);
            // https
            CloseableHttpClient httpClient = getHttpsClient();
            httpRequestFactory.setHttpClient(httpClient);

            RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
            restTemplate.getMessageConverters()
                    .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            restTemplate.setInterceptors(Collections.singletonList(new CustomInterceptor()));
            httpInvoker = new HttpInvoker(restTemplate);
        }
    }

    public static class CustomInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            String traceId = MDC.get(Constants.REQUEST_HEADER_TRACEID);
            if (StringUtils.isNotBlank(traceId)) {
                HttpHeaders headers = request.getHeaders();
                headers.add(Constants.REQUEST_HEADER_TRACEID, traceId);
            }
            return execution.execute(request, body);
        }
    }

}
