package com.model;

import com.service.DomainType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by we on 2016. 1. 13..
 */

@Getter
@Setter
public class Domain implements Serializable {
    private DomainType domainType;
    private List<String> apiNames = new ArrayList<String>();
}
