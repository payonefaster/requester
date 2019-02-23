package pay.one.faster.requester.domain.service;

import org.springframework.stereotype.Service;
import pay.one.faster.requester.domain.Requester;
import pay.one.faster.requester.domain.data.RegisterRequester;
import pay.one.faster.requester.domain.repository.RequesterRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class RequesterService {

  private final RequesterRepository requesterRepository;

  public RequesterService(RequesterRepository requesterRepository) {
    this.requesterRepository = requesterRepository;

  }

  public Mono<Requester> register(RegisterRequester registerRequester) {
    return this.requesterRepository.save(
        new Requester(
            UUID.randomUUID().toString(),
            registerRequester.getName(),
            registerRequester.getEnabled()));
  }

  public Mono<Requester> find(String id) {
    return this.requesterRepository.findById(id);
  }

  public Flux<Requester> all() {
    return this.requesterRepository.findAll();
  }
}
