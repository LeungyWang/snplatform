package com.wly.controller;

import com.wly.entity.Element;
import com.wly.repository.ElementRepository;
import com.wly.repository.SoilTypeRepository;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/findAll/{index}/{limit}")
    public Result findAll(@PathVariable int index, @PathVariable int limit){
        return new Result(0,"",elementRepository.count(),elementRepository.findAll(index,limit));
    }

    @GetMapping("/findAll")
    public Result findElements(){
        return new Result(0,"",elementRepository.count(),elementRepository.findElements());
    }


    @PutMapping("/update")
    public Result update(@RequestBody Element element){
        elementRepository.update(element);
        return new Result(200,"修改成功！",1,"");
    }

    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam("id") String id){
        elementRepository.deleteById(id);
        return new Result(200,"删除成功！",1,"");
    }

    //增加功能
    @PostMapping("/save")
    public Result save(@RequestBody Element element){
        elementRepository.save(element);
        return new Result(200,"保存成功！",0,"");
    }
}
