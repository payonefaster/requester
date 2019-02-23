package pay.one.faster.requester.domain.service;

import org.springframework.stereotype.Service;
import pay.one.faster.requester.domain.Requester;
import pay.one.faster.requester.domain.data.RegisterRequester;
import pay.one.faster.requester.domain.repository.RequesterDataRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RequesterService {

  private final RequesterDataRepository requesterRepository;

  public RequesterService(RequesterDataRepository requesterRepository) {
    this.requesterRepository = requesterRepository;
  }

  public Mono<Requester> register(RegisterRequester registerRequester) {
    return this.requesterRepository.register(registerRequester);
  }

  public Mono<Requester> find(String id) {
    return this.requesterRepository.find(id);
  }

  public Flux<Requester> all() {
    return this.requesterRepository.all();
  }
}
