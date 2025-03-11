package org.example.com.ifmo.se.route.management.configs.consul

import jakarta.annotation.PostConstruct
import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.DefaultUriBuilderFactory


@Service
open class ConsulRegistrationService(
    private val restTemplate: RestTemplate,
) {
    private companion object : KLogging()
//
//    private lateinit var sslContext: SSLContext
//
//    @Value("\${server.ssl.key-store-password}")
//    private lateinit var trustStorePassword: String

    private val consulUrl = "http://consul-server:8500"

    @PostConstruct
    fun registerService() {
        logger.info("ConsulRegistrationService-postConstruct")
        val serviceId = "route-management-service-1"
        val serviceName = "route-management-service"
        val servicePort = 8080

        val registrationJson = """
            {
                "ID": "$serviceId",
                "Name": "$serviceName",
                "Address": "$serviceId",
                "Port": $servicePort,
                "Check": {
                    "HTTP": "http://$serviceId:$servicePort/actuator/health",
                    "Interval": "5s"
                }
            }
        """.trimIndent()

        val uriBuilder = DefaultUriBuilderFactory(consulUrl)
        val uri = uriBuilder.expand("/v1/agent/service/register?replace-existing-checks").toString()

        try {
            restTemplate.put(uri, registrationJson)
            logger.info("Service registered successfully.")
        } catch (ex: HttpClientErrorException) {
            logger.info("Failed to register service: ${ex.message}")
        } catch (ex: HttpServerErrorException) {
            logger.info("Server error: ${ex.message}")
        } catch (ex: Exception) {
            logger.info("Exception while registering service: ${ex.message}")
        }
    }

//
//    @Bean
//    open fun restTemplate(
//        @Value("\${server.ssl.trust-store}") trustStore: Resource,
//    ): RestTemplate? {
//        val sslContext: SSLContext = SSLContextBuilder()
//            .loadTrustMaterial(trustStore.url, trustStorePassword.toCharArray()).build()
//
//        val sslConFactory = SSLConnectionSocketFactory(
//            sslContext,
//            NoopHostnameVerifier.INSTANCE // Игнорируем проверку имени хоста
//        )
//        val cm: HttpClientConnectionManager = PoolingHttpClientConnectionManagerBuilder.create()
//            .setSSLSocketFactory(sslConFactory)
//            .build()
//
//        val httpClient: CloseableHttpClient = HttpClients.custom().setConnectionManager(cm).build()
//        val requestFactory: ClientHttpRequestFactory = HttpComponentsClientHttpRequestFactory(httpClient)
//
//        restTemplate = RestTemplate(requestFactory)
//        return restTemplate;
//    }
}