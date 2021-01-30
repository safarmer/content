package co.thatch.hub.svc.content;

import io.micronaut.runtime.Micronaut;
import io.dekorate.kubernetes.annotation.KubernetesApplication;
import io.dekorate.kubernetes.annotation.Label;
import io.dekorate.kubernetes.annotation.Port;
import io.dekorate.kubernetes.annotation.Probe;
import io.dekorate.prometheus.annotation.EnableServiceMonitor;

@KubernetesApplication(
    name = "content",
    labels = @Label(key = "app", value = "content"),
    ports = @Port(name = "http", containerPort = 8080),
    livenessProbe = @Probe(httpActionPath = "/health/liveness", initialDelaySeconds = 5, timeoutSeconds = 3, failureThreshold = 10),
    readinessProbe = @Probe(httpActionPath = "/health/readiness", initialDelaySeconds = 5, timeoutSeconds = 3, failureThreshold = 10)
)
@EnableServiceMonitor(port = "http", path="/prometheus")
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
