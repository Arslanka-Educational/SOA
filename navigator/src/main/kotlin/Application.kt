package generated.com.ifmo.seopenbook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import com.ifmo.se.navigator.configs.ApiConfig

@SpringBootApplication
@EnableConfigurationProperties(ApiConfig::class)
open class Application: SpringBootServletInitializer()

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}