import com.ifmo.se.navigator.dtos.*
import com.ifmo.se.navigator.ejb.PropertiesUtil
import jakarta.annotation.PostConstruct
import jakarta.ejb.Remote
import jakarta.ws.rs.client.Client
import jakarta.ws.rs.client.ClientBuilder
import jakarta.ws.rs.client.Entity
import jakarta.ws.rs.core.MediaType
import org.apache.hc.core5.ssl.SSLContextBuilder
import javax.net.ssl.SSLContext

@Remote
interface RouteManagementClient {

    fun addRoute(request: RouteUpsertRequestDto): RouteDto

    fun getLocationById(id: Long): LocationDto

    fun getRoutes(
        filter: GetRoutesFilterParameterDto?, sortBy: List<SortFieldsDto>?, limit: Int?, offset: Int?
    ): RouteResponseDto

    fun getLocations(
        limit: Int?, offset: Int?
    ): LocationResponseDto
}