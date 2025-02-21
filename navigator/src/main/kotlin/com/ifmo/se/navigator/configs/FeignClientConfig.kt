package com.ifmo.se.navigator.com.ifmo.se.navigator.configs

import com.ifmo.se.navigator.com.ifmo.se.navigator.clients.CustomErrorDecoder
import com.ifmo.se.navigator.com.ifmo.se.navigator.clients.RouteManagementClient
import feign.Client
import feign.Contract
import feign.Feign
import feign.Request
import feign.codec.ErrorDecoder
import feign.httpclient.ApacheHttpClient
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.impl.client.HttpClients
import org.apache.http.ssl.SSLContextBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.security.KeyStore
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext


@Configuration
open class FeignClientConfig {

    @Value("\${client.route-management.server.url}")
    private lateinit var url: String

    @Value("\${client.route-management.server.connect-timeout}")
    private var connectTimeout: Long = 5000

    @Value("\${client.route-management.server.read-timeout}")
    private var readTimeout: Long = 10000

    @Value("\${server.ssl.key-store-password}")
    private lateinit var trustStorePassword: String

    @Bean
    open fun feignRequestOptions(): Request.Options {
        return Request.Options(connectTimeout, TimeUnit.MILLISECONDS, readTimeout, TimeUnit.MILLISECONDS, false)
    }

    @Bean
    open fun feignClient(): RouteManagementClient {
        return Feign.builder()
            .options(feignRequestOptions())
            .target(RouteManagementClient::class.java, url)
    }
//
//    @Bean
//    fun client(sslBundles: SslBundles): Client {
//        val sslContext: SSLContext = sslBundles.getBundle("my-client").createSslContext();
//        return Client.Default(sslContext.socketFactory, HttpsURLConnection.getDefaultHostnameVerifier());
//    }

    @Bean
    open fun feignClientWithSslContext(sslContext: SSLContext): Client {
        val socketFactory = SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE)
        val httpClient = HttpClients.custom()
            .setSSLSocketFactory(socketFactory)
            .build()

        return ApacheHttpClient(httpClient)
    }

    @Bean
    open fun feignContract(): Contract {
        return Contract.Default()
    }

    @Bean
    open fun errorDecoder(): ErrorDecoder {
        return CustomErrorDecoder()
    }

    @Bean
    open fun sslContext(): SSLContext {
        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        keyStore.load(
            this::class.java.classLoader.getResourceAsStream("soa.keystore"),
            trustStorePassword.toCharArray()
        )

        val trustStore = KeyStore.getInstance(KeyStore.getDefaultType())
        trustStore.load(
            this::class.java.classLoader.getResourceAsStream("soa.truststore"), // Truststore для WildFly
            trustStorePassword.toCharArray()
        )

        return SSLContextBuilder()
            .loadKeyMaterial(keyStore, trustStorePassword.toCharArray())
            .loadTrustMaterial(trustStore, null)
            .build()
    }
}