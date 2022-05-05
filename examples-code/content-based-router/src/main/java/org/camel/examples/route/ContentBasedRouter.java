package org.camel.examples.route;

import org.apache.camel.builder.RouteBuilder;
import org.camel.examples.bean.ContentBaseRouterBean;
import org.springframework.stereotype.Component;

@Component
public class ContentBasedRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:a")
                .routeId("direct:a.id")
                .choice()
                    .when(simple("${header.foo} == 'bar'"))
                        .to("direct:b")
                    .when(simple("${header.foo} == 'cheese'"))
                        .to("direct:c")
                    .otherwise()
                        .to("direct:d")
                .endChoice()
                        .end();


        from("direct:b").routeId("direct:b.id")
                .bean(ContentBaseRouterBean.class, "directB")
                .to("stream:out")
                .end();

        from("direct:c").routeId("direct:c.id")
                .bean(ContentBaseRouterBean.class, "directC")
                .to("stream:out")
                .end();

        from("direct:d").routeId("direct:d.id")
                .bean(ContentBaseRouterBean.class, "directD")
                .to("stream:out")
                .end();
    }

}
