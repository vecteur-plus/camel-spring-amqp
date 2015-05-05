/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/. */
package amqp.spring.camel.component;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.stereotype.Component;

@Component("connectionFactory")
public class TestConnectionFactory extends CachingConnectionFactory {
    
    public TestConnectionFactory() {
        super();
        
        // NOTE: Change the factory properties (host, port, vhost, username, password, ...)
        //       here to run all the tests with specifically configured connections.
        
    }
}
