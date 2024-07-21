package com.thc.realspr.service.impl;

import com.thc.realspr.domain.Tbpost;
import com.thc.realspr.domain.Tbuser;
import com.thc.realspr.repository.TbpostRepository;
import com.thc.realspr.repository.TbuserRepository;
import com.thc.realspr.service.TbpostService;
import com.thc.realspr.service.TbuserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbpostServiceImpl implements TbpostService {

    private final TbpostRepository tbpostRepository;
    public TbpostServiceImpl(
            TbpostRepository tbpostRepository
    ) {
        this.tbpostRepository = tbpostRepository;
    }

    public Map<String, Object> create(Map<String, Object> param){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        System.out.println(param);
        Tbpost tbpost = Tbpost.of(param.get("title") + "", param.get("content")+"", param.get("boardname") + "");
        tbpostRepository.save(tbpost);

        returnMap.put("id", tbpost.getId());
        return returnMap;
    }
    public Map<String, Object> update(Map<String, Object> param){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        System.out.println(param);
        Tbpost tbpost = tbpostRepository.findById(param.get("id") + "").orElseThrow(() -> new RuntimeException(""));
        if(param.get("title") != null) {
            tbpost.setTitle(param.get("title") + "");
        }
        if(param.get("content") != null) {
            tbpost.setContent(param.get("content") + "");
        }
        if(param.get("boardname") != null) {
            tbpost.setBoardname(param.get("boardname") + "");
        }

        tbpostRepository.save(tbpost);

        returnMap.put("id", tbpost.getId());
        returnMap.put("updated", "complete");
        return returnMap;
    }

    public Map<String, Object> delete(String id){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        System.out.println();
        Tbpost tbpost = tbpostRepository.findById(id).orElseThrow(() -> new RuntimeException(""));

        tbpostRepository.delete(tbpost);

        returnMap.put("id", tbpost.getId());
        returnMap.put("updated", "complete");
        return returnMap;
    }

    public Map<String, Object> get(String id){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        System.out.println(id);
        Tbpost tbpost = tbpostRepository.findById(id).orElseThrow(() -> new RuntimeException(""));

        returnMap.put("id", tbpost.getId());
        returnMap.put("title", tbpost.getTitle());
        returnMap.put("content", tbpost.getContent());
        returnMap.put("boardname", tbpost.getBoardname());
        returnMap.put("createdAt", tbpost.getCreatedDate());
        returnMap.put("modifiedAt", tbpost.getModifiedDate());

        return returnMap;
    }

    public List<Map<String, Object>> getAll() {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Tbpost> allFeed  = tbpostRepository.findAll();

        for(Tbpost tbpost : allFeed) {
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("id", tbpost.getId());
            returnMap.put("title", tbpost.getTitle());
            returnMap.put("content", tbpost.getContent());
            returnMap.put("boardname", tbpost.getBoardname());
            returnMap.put("createdAt", tbpost.getCreatedDate());
            returnMap.put("modifiedAt", tbpost.getModifiedDate());
            result.add(returnMap);
        }

        return result;
    }

    public List<Map<String, Object>> boardGetAll(String boardname){
        List<Map<String, Object>> result = new ArrayList<>();
        List<Tbpost> allFeed  = tbpostRepository.findAllByBoardname(boardname);

        for(Tbpost tbpost : allFeed) {
//            if (boardname.equals(tbpost.getBoardname())) {
                Map<String, Object> returnMap = new HashMap<String, Object>();
                returnMap.put("id", tbpost.getId());
                returnMap.put("title", tbpost.getTitle());
                returnMap.put("content", tbpost.getContent());
                returnMap.put("boardname", tbpost.getBoardname());
                returnMap.put("createdAt", tbpost.getCreatedDate());
                returnMap.put("modifiedAt", tbpost.getModifiedDate());
                result.add(returnMap);
            }
//        }

        return result;
    }

}
