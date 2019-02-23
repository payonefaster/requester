package pay.one.faster.requester.domain.repository;

import java.util.UUID;
import org.springframework.stereotype.Service;
import pay.one.faster.requester.domain.Requester;
import pay.one.faster.requester.domain.data.RegisterRequester;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author claudioed on 2019-02-23.
 * Project requester
 */
@Service
public class CommonRequesterDataRepository implements RequesterDataRepository {

  private final RequesterRepository requesterRepository;

  public CommonRequesterDataRepository(RequesterRepository requesterRepository) {
    this.requesterRepository = requesterRepository;
  }

  @Override
  public Flux<Requester> all() {
    return this.requesterRepository.findAll();
  }

  @Override
  public Mono<Requester> find(String id) {
    return this.requesterRepository.findById(id);
  }

  @Override
  public Mono<Requester> register(RegisterRequester registerRequester) {
    return this.requesterRepository.save(new Requester(
        UUID.randomUUID().toString(),
        registerRequester.getName(),
        registerRequester.getEnabled()));
  }
}
