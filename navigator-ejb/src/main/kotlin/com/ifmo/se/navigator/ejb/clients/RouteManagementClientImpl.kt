import com.ifmo.se.navigator.dtos.*
import com.ifmo.se.navigator.ejb.PropertiesUtil
import jakarta.annotation.PostConstruct
import jakarta.ejb.Stateless
import jakarta.ws.rs.client.Client
import jakarta.ws.rs.client.ClientBuilder
import jakarta.ws.rs.client.Entity
import jakarta.ws.rs.core.MediaType
import org.apache.hc.core5.ssl.SSLContextBuilder
import javax.net.ssl.SSLContext

@Stateless
open class RouteManagementClientImpl : RouteManagementClient {

//    private lateinit var httpClient: Client
    private lateinit var sslContext: SSLContext
    private lateinit var baseUrl: String

    private val propertiesUtil = PropertiesUtil()

    @PostConstruct
    fun init() {
        val keystorePath = propertiesUtil.getValueByPropertyNameOrEmpty("ssl.keystore.path")
        val keystorePassword = propertiesUtil.getValueByPropertyNameOrEmpty("ssl.keystore.password").toCharArray()

        sslContext = SSLContextBuilder().loadTrustMaterial(
            this.javaClass.classLoader.getResource(keystorePath), keystorePassword
        ).build()

        baseUrl = propertiesUtil.getValueByPropertyNameOrEmpty("client.route-management.url")
        println("baseurl: $baseUrl")
    }


    override fun addRoute(request: RouteUpsertRequestDto): RouteDto {
        val httpClient = ClientBuilder.newBuilder().sslContext(sslContext).hostnameVerifier { _, _ -> true }.build()

        val webTarget = httpClient.target("$baseUrl/routes")
        val entity = Entity.entity(request, MediaType.APPLICATION_JSON_TYPE)

        val response = webTarget.request(MediaType.APPLICATION_JSON).post(entity)

        return response.readEntity(RouteDto::class.java)
    }

    override fun getLocationById(id: Long): LocationDto {
//        return LocationDto(x = 1, z = 10L)
        val httpClient = ClientBuilder.newBuilder().sslContext(sslContext).hostnameVerifier { _, _ -> true }.build()

        val webTarget = httpClient.target("$baseUrl/locations/$id")
        val response = webTarget.request().get()

        return response.readEntity(LocationDto::class.java)
    }

    override fun getRoutes(
        filter: GetRoutesFilterParameterDto?, sortBy: List<SortFieldsDto>?, limit: Int?, offset: Int?
    ): RouteResponseDto {
//        return RouteResponseDto(total = 10)
        val httpClient = ClientBuilder.newBuilder().sslContext(sslContext).hostnameVerifier { _, _ -> true }.build()

        val webTarget = httpClient.target("$baseUrl/routes").queryParam("filter", filter?.toString())
            .queryParam("sortBy", sortBy?.joinToString(",")).queryParam("limit", limit).queryParam("offset", offset)
        val response = webTarget.request(MediaType.APPLICATION_JSON).get()

        return response.readEntity(RouteResponseDto::class.java)
    }

    override fun getLocations(
        limit: Int?, offset: Int?
    ): LocationResponseDto {
        val httpClient = ClientBuilder.newBuilder().sslContext(sslContext).hostnameVerifier { _, _ -> true }.build()

        val webTarget = httpClient.target("$baseUrl/locations").queryParam("limit", limit).queryParam("offset", offset)

        val response = webTarget.request().get()

        return response.readEntity(LocationResponseDto::class.java)
    }
}