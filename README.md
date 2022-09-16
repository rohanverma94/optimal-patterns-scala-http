# scala-http-benchmark

1. Build

```
sbt akka-benchmark/stage http4s-benchmark/stage zio-http-benchmark/stage
```

2. Run

```
./akka-benchmark/target/universal/stage/bin/akka-benchmark
```

Or

```
./http4s-benchmark/target/universal/stage/bin/http4s-benchmark
```

Or

```
./zio-http-benchmark/target/universal/stage/bin/http4s-benchmark
```

3. Test

```
wrt -t2 -c100 -d10s "http://127.0.0.1:8080"
```