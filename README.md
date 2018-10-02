
## Getting started

```
git clone https://github.com/gshaw-pivotal/hystrix-demo.git
```

## Overview

A simple springboot demo application of hystrix's circuit breaker feature and how to work with it including exception handling. The demo is made up of 2 applications:
1. a 'middleware' application that utilizes Hystrix
2. a 'backend' application featuring fast and slow endpoints, that is called by the middleware

By default the middleware runs locally on port 3000 while the backend by default runs on port 3001.

## Demo endpoints

The following endpoints do not have any controller advice applied to them.

`/front/fast` endpoint that is quick (no timeout)

`/front/slow-nh` endpoint that will timeout and has no hystrix circuit breaker applied

`/front/slow-wh-nf` endpoint that will timeout and has hystrix circuit breaker applied, but no fallback method

`/front/slow-wf` endpoint that will timeout and has hystrix circuit breaker applied, with a fallback method

`/front/slow-wtf` endpoint that will timeout and has hystrix circuit breaker applied, with a fallback that will throw an exception

`/front/slow-capture` endpoint that will timeout and has hystrix circuit breaker applied, with a fallback that will throw an exception, but the code annotated by the '@HystrixCommand' will also throw a custom exception

`/front/slow-no-capture` endpoint that will timeout and has hystrix circuit breaker applied, with a fallback, but the code annotated by the '@HystrixCommand' will throw a custom exception and hystrix is instructed to ignore the custom exception

The following endpoints do have a controller advice applied to them.

`/front/slow-advice-nf` endpoint that will timeout and has hystrix circuit breaker applied, but no fallback method

`/front/slow-advice-tf` endpoint that will timeout and has hystrix circuit breaker applied, with a fallback that will throw an exception

`/front/slow-advice-no-capture` endpoint that will timeout and has hystrix circuit breaker applied, with a fallback, but the code annotated by the '@HystrixCommand' will throw a custom exception and hystrix is instructed to ignore the custom exception

## Backend application

The 'delay' in milliseconds for the slow endpoint (to allow a timeout to occur in the Hystrix application) is set via:

```yaml
delay:
    slow: 5000
```

## Hystrix application

The global hystrix timeout is set using:

```yaml
hystrix:
    command:
        default:
            execution.isolation.thread.timeoutInMilliseconds: 1000
```

A custom Hystrix timeout for a specific command key would look like:

```yaml
hystrix:
    command:
        specialCommandKey:
            execution.isolation.thread.timeoutInMilliseconds: 10000
```

## Useful websites

- [Hystrix timeout settings](https://github.com/Netflix/Hystrix/wiki/Configuration#execution.isolation.thread.timeoutInMilliseconds)
- [Basic tutorial on controller advice](https://www.baeldung.com/exception-handling-for-rest-with-spring)
