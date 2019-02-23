package pay.one.faster.requester.infra.web.handler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.created;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import pay.one.faster.requester.domain.Requester;
import pay.one.faster.requester.domain.data.RegisterRequester;
import pay.one.faster.requester.domain.service.RequesterService;
import pay.one.faster.requester.infra.web.opentracing.OpenTracingHeaders;
import reactor.core.publisher.Mono;

@Configuration
public class RequesterConfigRouter {

  @Bean(name = "requesterRouter")
  RouterFunction<ServerResponse> requesterRouter(RequesterService requesterService) {

    return route(GET("/api/requesters"), req -> ok().body(requesterService.all(), Requester.class))
        .and(
            route(
                GET("/api/requesters/{id}"),
                req -> ok().body(requesterService.find(req.pathVariable("id")), Requester.class)))
        .and(
            route(
                POST("/api/requesters"),
                req -> {
                  Mono<RegisterRequester> request = req.bodyToMono(RegisterRequester.class);
                  final OpenTracingHeaders headers = new OpenTracingHeaders();
                  OpenTracingHeaders.options.forEach(
                      el -> {
                        if (!req.headers().header(el).isEmpty()) {
                          headers.add(el, req.headers().header(el).get(0));
                        }
                      });
                  return created(UriComponentsBuilder.fromPath("/requesters/").build().toUri())
                      .contentType(MediaType.APPLICATION_JSON)
                      .body(
                          fromPublisher(
                              request.map(p -> p).flatMap(requesterService::register),
                              Requester.class));
                }));
  }
}
