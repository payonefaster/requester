open module requester {
  requires spring.beans;
  requires spring.core;
  requires spring.context;
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.boot.starter.reactor.netty;
  requires spring.boot.starter.data.cassandra.reactive;
  requires spring.data.cassandra;
  requires spring.webflux;
  requires reactor.core;
  requires reactor.netty;
  requires jdk.unsupported;
}
