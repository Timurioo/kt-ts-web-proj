package by.tska.backend

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@TestPropertySource(properties = arrayOf("management.metrics.export.wavefront.enabled=false"))
@SpringBootTest
class BackendApplicationTests {

	@Test
	fun contextLoads() {
	}

}
