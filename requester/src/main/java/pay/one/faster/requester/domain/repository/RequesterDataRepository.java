package pay.one.faster.requester.domain.repository;

import pay.one.faster.requester.domain.Requester;
import pay.one.faster.requester.domain.data.RegisterRequester;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author claudioed on 2019-02-23.
 * Project requester
 */
public interface RequesterDataRepository {

  Flux<Requester> all();

  Mono<Requester> find(String id);

  Mono<Requester> register(RegisterRequester registerRequester);

}
