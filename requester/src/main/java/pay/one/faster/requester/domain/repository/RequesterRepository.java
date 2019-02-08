package pay.one.faster.requester.domain.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import pay.one.faster.requester.domain.Requester;

public interface RequesterRepository extends ReactiveCassandraRepository<Requester, String> {}
