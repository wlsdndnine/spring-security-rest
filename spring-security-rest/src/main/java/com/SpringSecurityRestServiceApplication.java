package com;

import com.service.RoleService;
import com.service.SingletonRoleMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class SpringSecurityRestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityRestServiceApplication.class, args);
        SingletonRoleMap.getInstance().initDomainMap();
    }

}
