package cn.blz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class BioGatewaySwagger2Application {

    public static void main(String[] args) {
        SpringApplication.run(BioGatewaySwagger2Application.class, args);
    }


    @Component
    @Primary
    public class GatewaySwaggerResourcesProvider implements SwaggerResourcesProvider {
        private final RouteLocator routeLocator;

        public GatewaySwaggerResourcesProvider(RouteLocator routeLocator) {
            this.routeLocator = routeLocator;
        }

        @Override
        public List<SwaggerResource> get() {
            List<SwaggerResource> resources = new ArrayList<>();
            List<Route> routes = routeLocator.getRoutes();
            for (Route route : routes) {
                resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs")));
            }
            return resources;
        }

        private SwaggerResource swaggerResource(String name, String location) {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion("2.0");
            return swaggerResource;
        }
    }
}
