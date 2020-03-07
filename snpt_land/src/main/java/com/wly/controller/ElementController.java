package com.wly.controller;

import com.wly.entity.Element;
import com.wly.repository.ElementRepository;
import com.wly.repository.SoilTypeRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/element")
public class ElementController {

    @Autowired
    public ElementRepository elementRepository;

    @GetMapping("/findByTypeid/{id}")
    public List<String> findByTypeid(@PathVariable int id){
        List<Element> elements = elementRepository.findByTypeid(id);
        List<String> names = new ArrayList<>();
        for (int i=0;i<elements.size();i++){
            Element element = elements.get(i);
            names.add(element.getName());
        }
        return names;
    }


}
