import com.ifmo.se.navigator.dtos.*
import com.ifmo.se.navigator.ejb.PropertiesUtil
import jakarta.annotation.PostConstruct
import jakarta.ws.rs.client.Client
import jakarta.ws.rs.client.ClientBuilder
import jakarta.ws.rs.client.Entity
import jakarta.ws.rs.core.MediaType
import org.apache.hc.core5.ssl.SSLContextBuilder
import javax.net.ssl.SSLContext

class RouteManagementClientImpl {

    private lateinit var httpClient: Client
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

        httpClient = ClientBuilder.newBuilder().sslContext(sslContext).hostnameVerifier { _, _ -> true }.build()


        baseUrl = propertiesUtil.getValueByPropertyNameOrEmpty("client.route-management.url")
    }


    fun addRoute(request: RouteUpsertRequestDto): RouteDto {
        val webTarget = httpClient.target("$baseUrl/routes")
        val entity = Entity.entity(request, MediaType.APPLICATION_JSON_TYPE)

        val response = webTarget.request(MediaType.APPLICATION_JSON).post(entity)

        return response.readEntity(RouteDto::class.java)
    }

    fun getLocationById(id: Long): LocationDto {
        val webTarget = httpClient.target("$baseUrl/locations/$id")
        val response = webTarget.request(MediaType.APPLICATION_JSON).get()

        return response.readEntity(LocationDto::class.java)
    }

    fun getRoutes(
        filter: GetRoutesFilterParameterDto?, sortBy: List<SortFieldsDto>?, limit: Int?, offset: Int?
    ): RouteResponseDto {
        val webTarget = httpClient.target("$baseUrl/routes").queryParam("filter", filter?.toString())
            .queryParam("sortBy", sortBy?.joinToString(",")).queryParam("limit", limit).queryParam("offset", offset)

        val response = webTarget.request(MediaType.APPLICATION_JSON).get()

        return response.readEntity(RouteResponseDto::class.java)

    }

    fun getLocations(
        limit: Int?, offset: Int?
    ): LocationResponseDto {
        val webTarget = httpClient.target("$baseUrl/locations").queryParam("limit", limit).queryParam("offset", offset)

        val response = webTarget.request(MediaType.APPLICATION_JSON).get()

        return response.readEntity(LocationResponseDto::class.java)
    }
}