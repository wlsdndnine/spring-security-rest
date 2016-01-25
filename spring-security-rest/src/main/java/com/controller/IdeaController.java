package com.controller;

import java.util.*;

import com.service.DomainType;
import com.service.Role;
import com.model.Idea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.repository.IdeaRepository;

@RestController
@RequestMapping("/api/ideas")
public class IdeaController {
  @Autowired
  private IdeaRepository repository;

  @RequestMapping(value = "/domain/{domain}/apiPath/{apiPath}", method = RequestMethod.GET)
  @Role(DomainType = DomainType.ORDER,apiName = "get")
  public List<Idea> findAll(@PathVariable String domain) {
    System.out.println("domain :" + domain);
    return repository.findAll();
 }

  @RequestMapping(method = RequestMethod.POST)
  public Idea add(@RequestBody Idea idea) {
    Idea model = new Idea();
    model.setCreatedAt(new Date());
    model.setTitle(idea.getTitle());
    model.setDescription(idea.getDescription());
    System.out.print("model" + model.getId() + " /" +model.getTitle());
    return repository.saveAndFlush(model);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Idea findOne(@PathVariable long id) {
    return repository.findOne(id);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public Idea update(@PathVariable long id, @RequestBody Idea idea) {
    Idea model = repository.findOne(id);
    if (model != null) {
      model.setTitle(idea.getTitle());
      model.setDescription(idea.getDescription());
      return repository.saveAndFlush(model);
    }
    return null;
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void delete(@PathVariable long id) {
    repository.delete(id);
  }
}
