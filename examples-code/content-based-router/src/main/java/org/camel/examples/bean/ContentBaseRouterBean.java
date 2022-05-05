package org.camel.examples.bean;

import org.apache.camel.Exchange;

public class ContentBaseRouterBean {

    void directB(Exchange exchange){
        exchange.getMessage().setBody("direct-b-id");
    }

    void directC(Exchange exchange){
        exchange.getMessage().setBody("direct-c-id");
    }

    void directD(Exchange exchange){
        exchange.getMessage().setBody("direct-d-id");
    }
}
