package shadow

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ShadowApplicationTests {

	@Test
	void contextLoads() {

	}

	@Test
	void botMessageConstructorTest(){
		String messageReturn = "Opa, o meu nome é Shadow"
		//assert messageReturn == new BotResponse().botMessageConstructor("Qual o seu nome?")
	}

}
