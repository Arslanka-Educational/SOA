package com.ifmo.se.navigator.com.ifmo.se.navigator.configs

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder
import org.apache.hc.client5.http.io.HttpClientConnectionManager
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory
import org.apache.hc.core5.ssl.SSLContextBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import javax.net.ssl.SSLContext


@Configuration
open class ClientConfiguration {

    @Value("\${client.route-management.server.url}")
    private lateinit var url: String

    @Value("\${server.ssl.key-store-password}")
    private lateinit var trustStorePassword: String

    @Bean
    open fun restTemplate(
        @Value("\${server.ssl.trust-store}") trustStore: Resource,
    ): RestTemplate? {
        val sslContext: SSLContext = SSLContextBuilder()
            .loadTrustMaterial(trustStore.url, trustStorePassword.toCharArray()).build()

        val sslConFactory = SSLConnectionSocketFactory(
            sslContext,
            NoopHostnameVerifier.INSTANCE // Игнорируем проверку имени хоста
        )
        val cm: HttpClientConnectionManager = PoolingHttpClientConnectionManagerBuilder.create()
            .setSSLSocketFactory(sslConFactory)
            .build()

        val httpClient: CloseableHttpClient = HttpClients.custom().setConnectionManager(cm).build()
        val requestFactory: ClientHttpRequestFactory = HttpComponentsClientHttpRequestFactory(httpClient)

        return RestTemplate(requestFactory)
    }
}