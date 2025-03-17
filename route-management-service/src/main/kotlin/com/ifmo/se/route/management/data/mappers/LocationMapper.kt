import com.ifmo.se.route_management.LocationDto
import org.example.com.ifmo.se.route.management.data.models.Location


internal fun Location.mapToDto() = LocationDto().apply {
    this.id = this@mapToDto.id!!.toInt()
    this.x = this@mapToDto.x
    this.y = this@mapToDto.y!!
    this.z = this@mapToDto.z
    this.name = this@mapToDto.name
}