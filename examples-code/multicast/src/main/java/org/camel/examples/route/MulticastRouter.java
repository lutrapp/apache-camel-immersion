package org.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class MulticastRouter extends RouteBuilder {

    @Override
    public void configure() {
//        from("timer:hello?period={{timer.period}}").routeId("hello")
        from("direct:a")
                .multicast()
                .to("direct:x")
                .to("direct:y")
                .to("direct:z");

        from("direct:x")
                .routeId("direct-x-id")
                .process(exchange -> {
                    exchange.getMessage().setBody("direct-x-id");
                })
                .to("stream:out")
                .end();

        from("direct:y")
                .routeId("direct-y-id")
                .process(exchange -> {
                    exchange.getMessage().setBody("direct-y-id");
                })
                .to("stream:out")
                .end();

        from("direct:z")
                .routeId("direct-z-id")
                .process(exchange -> {
                    exchange.getMessage().setBody("direct-z-id");
                })
                .to("stream:out")
                .end();
    }

}
