
## Getting started

```
git clone https://github.com/gshaw-pivotal/hystrix-demo.git
```

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
