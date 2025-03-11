import generated.com.ifmo.se.route.dto.LocationDto
import org.example.com.ifmo.se.route.management.data.models.Location


internal fun Location.mapToDto() = LocationDto(
    x = this.x,
    y = this.y,
    z = this.z,
    id = this.id?.toInt(),
    name = this.name
)