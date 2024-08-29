# Demo Resilience4J

## Procedimentos

* Suba as aplicações `demo-resilience4j-consumer` e `demo-hystrix-resilience4j-producer`
* Faça algumas chamadas para o endpint da consumer passando qualquer nome, por exemplo: `curl localhost:8082/resilience4J/hello/Thiago`
  * O retorno nesse caso será `Hello Thiago`
* Faça novas chamadas para o mesmo endpoint, mas passando o valor `error` no lugar do nome: `curl localhost:8082/resilience4J/hello/error`
  * O retorno nesse caso será: `Hello John Doe`
  * No log, vamos observar inicialmente o retorno `Erro ao obter o nome, será devolvido um nome padrão. Mensagem de retorno => 400 : "Erro na chamada"`
  * Após algumas chamadas, quando cair nos critérios da configuração realizada em `CustomResilience4JConfiguration`, veremos o retorno `Erro ao obter o nome, será devolvido um nome padrão. Mensagem de retorno => CircuitBreaker 'hello' is OPEN and does not permit further calls`
    * No log da aplicação `producer`, podemos verificar que quando cai no status de `OPEN` não são realizadas novas chamadas