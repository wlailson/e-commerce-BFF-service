package io.wlailson.github.e_commerce_BFF_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"jwt.public-key=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzWaCxWEst32Xzf3RsE5zj0QJFtG6yRj8S4iQsKSQ4bbaq69kNSPH9ie9u3uhdWS7wVgOJpN5BvKx9O5N57bhcFjfKGI3Y6twc4p55xGYT+dOnKfooz87cxoRJzuoKUdDV8aSnCUeyNU6fn8lAb5Nj8dY//FuFrYiDx0/BiOfPDZvvVzt4gs1Y+tt3Zl2udVtYL2MwnObWNRuF+ro1O/kB3gUM2jvpgPm+7baZuAVL1NCAy68C/NixtELIxY4bZ02rsUX1/OYVp2UltRBIg1w5Em2NJ/zMe3oebXrphtSBc1/VVCmxW6mPT1f116kbjQK3z0FPZpd++X2QLOfDc57RQIDAQAB",
		"services.identity.url=http://localhost",
		"services.catalog.url=http://localhost"
})
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}
