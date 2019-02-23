package pay.one.faster.requester.domain.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pay.one.faster.requester.domain.Requester;

interface RequesterRepository extends ReactiveCrudRepository<Requester, String> {}
