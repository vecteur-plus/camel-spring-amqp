/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/. */
package amqp.spring.camel.component;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.stereotype.Component;

/**
 * To configure connections used by the tests, set the "test.broker.uri" system property
 * to amqp://[username]:[password]@[host]:[port]/[vhost] when running the tests
 */
@Component("connectionFactory")
public class TestConnectionFactory extends CachingConnectionFactory {
    
    public TestConnectionFactory() {
        super();
        
        String brokerUri = System.getProperty("test.broker.uri");
        if ( brokerUri!=null ) {
            try {
                URI uri = new URI(brokerUri);

                String host = uri.getHost();
                int port = uri.getPort();
                String vhost = uri.getPath()==null || uri.getPath().isEmpty() ? null : uri.getPath().substring(1);
                
                String userInfo = uri.getUserInfo();
                String username = userInfo==null ? null : userInfo.substring(0, userInfo.indexOf(':')==-1 ? userInfo.length() : userInfo.indexOf(':'));
                String password = userInfo==null || userInfo.indexOf(':')==-1 ? null : userInfo.substring(userInfo.indexOf(':')+1);

                if ( host!=null ) {
                    this.setHost(host);
                }
                if ( port!=-1 ) {
                    this.setPort(port);
                }
                if ( vhost!=null ) {
                    this.setVirtualHost(vhost);
                }
                if ( username!=null ) {
                    this.setUsername(username);
                }
                if ( password!=null ) {
                    this.setPassword(password);
                }
            } catch ( URISyntaxException e ) {
                throw new RuntimeException("Provided test.broker.uri system property value is not a valid URI", e);
            }
        }
    }
}
