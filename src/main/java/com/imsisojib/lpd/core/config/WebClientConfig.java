package com.imsisojib.lpd.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}

/*
WebClient client = WebClient.builder()
  .baseUrl("http://localhost:8080")
  .defaultCookie("cookieKey", "cookieValue")
  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
  .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
  .build();


  //With timeout
  HttpClient httpClient = HttpClient.create()
  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
  .responseTimeout(Duration.ofMillis(5000))
  .doOnConnected(conn ->
    conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
      .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

WebClient client = WebClient.builder()
  .clientConnector(new ReactorClientHttpConnector(httpClient))
  .build();

  ///REQUEST: POST
  UriSpec<RequestBodySpec> uriSpec = client.post();
  UriSpec<RequestBodySpec> uriSpec = client.post();
  RequestBodySpec bodySpec = uriSpec.uri("/resource");

  LinkedMultiValueMap map = new LinkedMultiValueMap();
    map.add("key1", "value1");
    map.add("key2", "value2");
    RequestHeadersSpec<?> headersSpec = bodySpec.body(
  BodyInserters.fromMultipartData(map));


  //Preparing Request with Headers
  ResponseSpec responseSpec = headersSpec.header(
    HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
  .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
  .acceptCharset(StandardCharsets.UTF_8)
  .ifNoneMatch("*")
  .ifModifiedSince(ZonedDateTime.now())
  .retrieve();
*/