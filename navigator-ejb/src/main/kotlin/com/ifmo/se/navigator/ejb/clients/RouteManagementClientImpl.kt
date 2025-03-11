import com.ifmo.se.navigator.dtos.*
import jakarta.ejb.Stateless
import jakarta.ws.rs.client.ClientBuilder
import jakarta.ws.rs.client.Entity
import jakarta.ws.rs.core.MediaType

@Stateless
open class RouteManagementClientImpl : RouteManagementClient {

    private companion object {
        const val BASE_URL = "http://route-management-service-1:8444"
    }

    override fun addRoute(request: RouteUpsertRequestDto): RouteDto {
        val httpClient = ClientBuilder.newBuilder().hostnameVerifier { _, _ -> true }.build()

        val webTarget = httpClient.target("$BASE_URL/routes")
        val entity = Entity.entity(request, MediaType.APPLICATION_JSON_TYPE)

        val response = webTarget.request(MediaType.APPLICATION_JSON).post(entity)

        return response.readEntity(RouteDto::class.java)
    }

    override fun getLocationById(id: Long): LocationDto {
        val httpClient = ClientBuilder.newBuilder().hostnameVerifier { _, _ -> true }.build()

        val webTarget = httpClient.target("$BASE_URL/locations/$id")
        val response = webTarget.request(MediaType.APPLICATION_JSON).get()

        return response.readEntity(LocationDto::class.java)
    }

    override fun getRoutes(
        filter: GetRoutesFilterParameterDto?, sortBy: List<SortFieldsDto>?, limit: Int?, offset: Int?
    ): RouteResponseDto {
        val httpClient = ClientBuilder.newBuilder().hostnameVerifier { _, _ -> true }.build()

        val webTarget = httpClient.target("$BASE_URL/routes").queryParam("filter", filter?.toString())
            .queryParam("sortBy", sortBy?.joinToString(",")).queryParam("limit", limit).queryParam("offset", offset)
        val response = webTarget.request(MediaType.APPLICATION_JSON).get()

        return response.readEntity(RouteResponseDto::class.java)
    }


    override fun getLocations(
        limit: Int?, offset: Int?
    ): LocationResponseDto {
        val httpClient = ClientBuilder.newBuilder().build()

        val webTarget = httpClient.target("$BASE_URL/locations").queryParam("limit", limit).queryParam("offset", offset)
        val response = webTarget.request(MediaType.APPLICATION_JSON).get()

        println("response type: ${response.mediaType}")
//        println("Response Body: ${response.readEntity(String::class.java)}")

        return response.readEntity(LocationResponseDto::class.java)
    }
}