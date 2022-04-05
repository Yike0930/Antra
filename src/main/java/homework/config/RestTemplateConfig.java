import org.apache.http.Header;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

// necessary
@Configuration
@ConfigurationProperties(prefix = "spring.restTemplate")
@ConditionalOnClass(value = {RestTemplate.class, CloseableHttpClient.class})
public class RestTemplateConfig {

    // The priority of java configuration is lower than yml configuration; if yml configuration does not exist, java configuration will be used
    // ####Start java configuration of restTemplate####
    private int maxTotalConnection = 500; //Maximum number of connections to the connection pool

    private int maxConnectionPerRoute = 100; //Concurrent number of the same route

    private int connectionTimeout = 2 * 1000; //Connection timeout, default 2s

    private int readTimeout = 30 * 1000; //Read timeout, default 30s

    private String charset = "UTF-8";

    public void setMaxTotalConnection(int maxTotalConnection) {
        this.maxTotalConnection = maxTotalConnection;
    }

    public void setMaxConnectionPerRoute(int maxConnectionPerRoute) {
        this.maxConnectionPerRoute = maxConnectionPerRoute;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    //Create HTTP client factory
    @Bean(name = "clientHttpRequestFactory")
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        return createClientHttpRequestFactory(this.connectionTimeout, this.readTimeout);
    }

    //Initialize RestTemplate and join spring's Bean factory for spring's unified management
    @Bean(name = "restTemplate")
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return createRestTemplate(factory);
    }

    //Initialize the RestTemplate that supports asynchrony, and join the Bean factory of spring, which is under the unified management of spring
    //If you don't use asynchrony, you don't have to create the object
    @Bean(name = "asyncRestTemplate")
    @ConditionalOnMissingBean(AsyncRestTemplate.class)
    public AsyncRestTemplate asyncRestTemplate(RestTemplate restTemplate) {
        final Netty4ClientHttpRequestFactory factory = new Netty4ClientHttpRequestFactory();
        factory.setConnectTimeout(this.connectionTimeout);
        factory.setReadTimeout(this.readTimeout);
        return new AsyncRestTemplate(factory, restTemplate);
    }

    private ClientHttpRequestFactory createClientHttpRequestFactory(int connectionTimeout, int readTimeout) {
        //maxTotalConnection and maxConnectionPerRoute must be equipped with
        if (this.maxTotalConnection <= 0) {
            throw new IllegalArgumentException("invalid maxTotalConnection: " + maxTotalConnection);
        }
        if (this.maxConnectionPerRoute <= 0) {
            throw new IllegalArgumentException("invalid maxConnectionPerRoute: " + maxTotalConnection);
        }

        //Global default header configuration
        List<Header> headers = new LinkedList<>();
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6"));

        //Disable automatic retry. If you need to retry, please control yourself
        HttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(0, false);

        //Create an httpClient instance that actually processes http requests
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultHeaders(headers)
                .setRetryHandler(retryHandler)
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
                httpClient);
        factory.setConnectTimeout(connectionTimeout);
        factory.setReadTimeout(readTimeout);
        return factory;
    }

    private RestTemplate createRestTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);

        //We use the message converter inside the RestTemplate
        //Reset the StringHttpMessageConverter character set to solve the problem of Chinese scrambling
        modifyDefaultCharset(restTemplate);

        //Set error handler
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

        return restTemplate;
    }

    private void modifyDefaultCharset(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> item : converterList) {
            if (StringHttpMessageConverter.class == item.getClass()) {
                converterTarget = item;
                break;
            }
        }
        if (null != converterTarget) {
            converterList.remove(converterTarget);
        }
        Charset defaultCharset = Charset.forName(charset);
        converterList.add(1, new StringHttpMessageConverter(defaultCharset));
    }
}