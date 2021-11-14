package com.consultadd.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter{

    private String clientId = "pixeltrice";
    private String clientSecret = "pixeltrice-secret-key";
    private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEA0eIcTNSK+28JuqqPZiB5QJAYX9VP4GX+OFItqePED6vUUp1C\n" +
            "Cv2ArqOEJ66gi3uTcESn7+jYd3IkByAa0dY6245i2vikG4KHq26r/OSh6oJ1Avyd\n" +
            "AYHo1IeA5zNQNdAjcyIar3JMCr3pu+kE/HnxQUdE9/j35usgv2XeiINjYWWdn/8E\n" +
            "FoymoBMEkvhGhTZAXK6eXsRoo7/Til1P5KmqIq9+WOPIdrzLYyLH/looEJojtF2I\n" +
            "JxA53BOW4eaqoL7Ymlj7uZT1UuBAS8cCDzJrzAUbjvMXmPsGEivqout9WZwSOa3e\n" +
            "1P0aeuPzO9VRg7TCZSo8ggkqtnaph5qv4OVIkQIDAQABAoIBAB9p2VSCh17Eqs8e\n" +
            "kiH2AE/ygUNuuOrcOSWS3zTLW2ABFuFamjTs5mK9JJ2P3IWj9FhcXkphLm0KT2wl\n" +
            "mP4JM3wDHrapel6HteipwuIyEi34Yq0UxCcim4eb1GZGXt3/Dh9AXoAd+lWtQRuX\n" +
            "7+AeHEbp9N+siuYsERW0mI8UIz7DYk0DP1O/osv8EZqO1/gvqobnBINA+zFTCDTJ\n" +
            "SWxmJHrqbSCtsadkXEWyD3nlZLNG9bK0qHR9gLXnGPhG5L40POGAnOcvfWh9NUqS\n" +
            "g++cNhjvL1APfIEbBI0ItTc5btcCgC87mKXe93AodGKbYdV1SIm04VH114du6c8J\n" +
            "0rr6/GUCgYEA9Gg5V9njtbBDi5rUpsKmZHe7f8p42tb72wcpJzELLt+FspTTqs+P\n" +
            "RLywuTBza2XNCEdhvXaaV4+b6/yjtjduCZwyi4LM+XwLZn/rpzF2npp2o3PCulGQ\n" +
            "5YGP7/fTtIUCvdq1IGxgpsTVLCGB1kM9R57Ic1xgNrS3jwbej9scU38CgYEA29ar\n" +
            "55xxO5ab/bCXb3Nw3IgudOE/JT3Ky3H3RAjyQsHzvrATf/1oJuS1NGnRKgCYhPNa\n" +
            "9/4NaBGlf/Qq1tbZ+t8AdFfCaMZY44K75JxTs8vD3DatBaaiYN7Eej1ORsUqdUnC\n" +
            "Yj4iBBWvc7DDiW1ubGvIgjcxpOvWnKhB8CMTK+8CgYEAy5JAKQ9gnkXpjM9yzk+U\n" +
            "/lvIPcGsdkLY1m+ZdH5iHTGqj7tsrnphj9RGsolLqanKZFnobEHsfoAsVBxDVBPJ\n" +
            "QJHse81/41HRwRdNdh45+hMYPD7AdCZaNP98n1jh6htb1Dzl3tLGkkGieTaTiZ7J\n" +
            "3OzBc2z+6rINrkD/gfz0FAsCgYEA27N2qUmXgE3MJ4TMUv2z1THj+7sAOF/AxAFp\n" +
            "c/zc4AqlZfTiCA9gfz4iOfdmqetegcATMbQcAd1g4LdL+NlPj4A+HAd4qu1O+FA3\n" +
            "Jwl0PUNuwi2CTRtzPAxlnySVjfSNshm8wiIGLL7fUWdeg4Fx0bkF8dznKELpgDZH\n" +
            "b44eItECgYBktNlbOt2g41NyKIrmlhWdG68HOcd4xmmHUoGt9gop0qho4mQ0KO6j\n" +
            "1VS8TmCuR0S2dfNolwy0w5+Jse5QS5Nb5M277Nx4jqrl9sIc7XUlE5I169+rIczF\n" +
            "uxcTPTChRIJU+HZFzTUfn6ZlR3flORCyjvEhOGwMte8ZOTyHRBaNkQ==\n" +
            "-----END RSA PRIVATE KEY-----";
    private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0eIcTNSK+28JuqqPZiB5\n" +
            "QJAYX9VP4GX+OFItqePED6vUUp1CCv2ArqOEJ66gi3uTcESn7+jYd3IkByAa0dY6\n" +
            "245i2vikG4KHq26r/OSh6oJ1AvydAYHo1IeA5zNQNdAjcyIar3JMCr3pu+kE/Hnx\n" +
            "QUdE9/j35usgv2XeiINjYWWdn/8EFoymoBMEkvhGhTZAXK6eXsRoo7/Til1P5Kmq\n" +
            "Iq9+WOPIdrzLYyLH/looEJojtF2IJxA53BOW4eaqoL7Ymlj7uZT1UuBAS8cCDzJr\n" +
            "zAUbjvMXmPsGEivqout9WZwSOa3e1P0aeuPzO9VRg7TCZSo8ggkqtnaph5qv4OVI\n" +
            "kQIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);

    }

}
