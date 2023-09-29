package br.com.fiap.procurados.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class GsonConfig extends WebMvcConfigurationSupport {

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(customGsonHttpMessageConverter());
		super.configureMessageConverters(converters);
	}

	private GsonHttpMessageConverter customGsonHttpMessageConverter() {
		GsonHttpMessageConverter gsonMessageConverter = new GsonHttpMessageConverter();
		gsonMessageConverter.setGson(createGson());
		return gsonMessageConverter;
	}

	public static GsonBuilder createGsonBuilder() {
		return new GsonBuilder().setLenient()
				.setPrettyPrinting()
				.excludeFieldsWithoutExposeAnnotation();
	}

	public static Gson createGson() {
		return createGsonBuilder().create();
	}
}
