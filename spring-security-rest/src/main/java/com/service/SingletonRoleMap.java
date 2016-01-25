package com.service;

import com.model.Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by we on 2016. 1. 13..
 */

public class SingletonRoleMap {
    public static final Map<String,Domain> domainMap = new HashMap<String, Domain>();
    private SingletonRoleMap() {}

    private static class Singleton {
        private static final SingletonRoleMap instance = new SingletonRoleMap();
    }

    public static SingletonRoleMap getInstance() {
        return Singleton.instance;
    }

    public static void initDomainMap(){
        Domain orderDomain = new Domain();
        orderDomain.setDomainType(DomainType.ORDER);
        List<String> apiNames = new ArrayList<String>();
        apiNames.add("add");
        orderDomain.setApiNames(apiNames);
        domainMap.put(DomainType.ORDER.name(),orderDomain);
    }

    public static Map<String,Domain> getDomainMap(){
       return domainMap;
    }
}
