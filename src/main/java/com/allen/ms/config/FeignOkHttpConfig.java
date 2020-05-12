package com.allen.ms.config;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;

/**
 * Feign OkHttp 配置类
 * 
 * @author Allen
 * @date 2020年5月12日
 * @since 1.0.0
 *
 */
//@Configuration
//@ConditionalOnClass(Feign.class)
//@AutoConfigureBefore(FeignAutoConfiguration.class)
public class FeignOkHttpConfig {

//	@Bean
	public okhttp3.OkHttpClient okHttpClient() {
		return new okhttp3.OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).connectTimeout(5, TimeUnit.SECONDS)
				.writeTimeout(5, TimeUnit.SECONDS).retryOnConnectionFailure(true)
				.connectionPool(new ConnectionPool(10, 60, TimeUnit.SECONDS))
				// .addInterceptor();
				.build();
	}
}
