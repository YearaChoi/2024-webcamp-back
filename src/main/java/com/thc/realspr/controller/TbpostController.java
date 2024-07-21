package com.thc.realspr.controller;
import com.thc.realspr.service.TbpostService;
import com.thc.realspr.service.TbuserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/tbpost")
@RestController
public class TbpostController {

    private final TbpostService tbpostService;
    public TbpostController(TbpostService tbpostService ) {
        this.tbpostService = tbpostService;
    }

    @PostMapping("")
    public Map<String, Object> create(@RequestBody Map<String, Object> param){
        System.out.println(param);
        return tbpostService.create(param);
    }
    @PutMapping("")
    @CrossOrigin(origins = "http://localhost:3000/")
    public Map<String, Object> update(@RequestBody Map<String, Object> param){
        System.out.println(param);
        return tbpostService.update(param);
    }

    @DeleteMapping ("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:3000/")
    public Map<String, Object> delete(@PathVariable("id") String id){
        System.out.println(id);
        return tbpostService.delete(id);
    }

    @GetMapping("/get/{id}")
    public Map<String, Object> detail(@PathVariable("id") String id){
        System.out.println(id);
        return tbpostService.get(id);
    }

    @GetMapping("/board/{boardname}")
    public List<Map<String, Object>> board(@PathVariable("boardname") String boardname){
        System.out.println(boardname);
        return tbpostService.boardGetAll(boardname);
    }

    @GetMapping("/getAll")
    public List<Map<String, Object>> getAll(){
        return tbpostService.getAll();
    }
}
