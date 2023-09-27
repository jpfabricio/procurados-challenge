package br.com.fiap.procurados.config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitConfig {

	public static <T> T createRepository(String baseUrl , Class<T> clazz) {
		Retrofit retrofit = new Retrofit.Builder()
										.baseUrl(baseUrl)
										.addConverterFactory(GsonConverterFactory.create())
										.build();
		return retrofit.create(clazz);
	};
}
