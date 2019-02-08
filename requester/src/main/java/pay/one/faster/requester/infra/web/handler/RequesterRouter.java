package pay.one.faster.requester.infra.web.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import pay.one.faster.requester.domain.Requester;
import pay.one.faster.requester.domain.data.RegisterRequester;
import pay.one.faster.requester.domain.service.RequesterService;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RequesterRouter {

  @Bean
  RouterFunction<ServerResponse> requesterRoutes(RequesterService requesterService) {
    return route(GET("/api/requesters"), req -> ok().body(requesterService.all(), Requester.class))
        .and(
            route(
                GET("/api/requesters/{id}"),
                req -> ok().body(requesterService.find(req.pathVariable("id")), Requester.class)))
        .and(
            route(
                POST("/api/requesters"),
                req ->
                    req.body(toMono(RegisterRequester.class))
                        .doOnNext(requesterService::register)
                        .then(ok().build())));
  }

}
