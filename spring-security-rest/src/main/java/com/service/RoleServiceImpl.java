package com.service;

import com.model.Domain;
import org.springframework.stereotype.Component;

/**
 * Created by we on 2016. 1. 13..
 */
@Component
public class RoleServiceImpl implements RoleService {

    @Override
    public boolean checkRole(DomainType domainType, String apiName){
        if(SingletonRoleMap.getDomainMap().containsKey(domainType)){
            Domain domain = SingletonRoleMap.getDomainMap().get(domainType);
            boolean isPossiable = checkApriName(domain);
            return isPossiable;
        }
        return false;
    }

    private static boolean checkApriName(Domain domain){
        return false;
    }

}
