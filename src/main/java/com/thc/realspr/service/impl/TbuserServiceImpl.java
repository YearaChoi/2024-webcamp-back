package com.thc.realspr.service.impl;

import com.thc.realspr.domain.Tbuser;
import com.thc.realspr.repository.TbuserRepository;
import com.thc.realspr.service.TbuserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbuserServiceImpl implements TbuserService {

    private final TbuserRepository tbuserRepository;
    public TbuserServiceImpl(
            TbuserRepository tbuserRepository
    ) {
        this.tbuserRepository = tbuserRepository;
    }

    public Map<String, Object> create(Map<String, Object> param){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        System.out.println(param);
        Tbuser tbuser = Tbuser.of(param.get("username") + "", param.get("password") + "");
        tbuserRepository.save(tbuser);

        returnMap.put("id", tbuser.getId());
        return returnMap;
    }
    public Map<String, Object> update(Map<String, Object> param){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        System.out.println(param);
        Tbuser tbuser = tbuserRepository.findById(param.get("id") + "").orElseThrow(() -> new RuntimeException(""));
        if(param.get("name") != null) {
            tbuser.setName(param.get("name") + "");
        }
        if(param.get("nick") != null) {
            tbuser.setNick(param.get("nick") + "");
        }
        if(param.get("phone") != null) {
            tbuser.setPhone(param.get("phone") + "");
        }

        tbuserRepository.save(tbuser);
//        tbuserRepository.deleteAll();
//        tbuserRepository.deleteById(param.get("id") + "");
//        tbuserRepository.delete(tbuser);

        returnMap.put("id", tbuser.getId());
        returnMap.put("updated", "complete");
        return returnMap;
    }
    public Map<String, Object> get(String id){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        System.out.println(id);
        Tbuser tbuser = tbuserRepository.findById(id).orElseThrow(() -> new RuntimeException(""));

        returnMap.put("id", tbuser.getId());
        returnMap.put("username", tbuser.getUsername());
        returnMap.put("name", tbuser.getName());
        returnMap.put("nick", tbuser.getNick());
        returnMap.put("phone", tbuser.getPhone());
        returnMap.put("yeara", "is love");
        returnMap.put("createdAt", tbuser.getCreatedDate());
        returnMap.put("modifiedAt", tbuser.getModifiedDate());

        return returnMap;
    }

    public List<Map<String, Object>> getAll() {
        List<Map<String, Object>> result = new ArrayList<>();
//        List<Tbfeed> allFeed  = tbfeedRepository.findAll();
        List<Tbuser> allFeed  = tbuserRepository.findAll();

        for(Tbuser tbuser : allFeed) {
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("id", tbuser.getId());
            returnMap.put("username", tbuser.getUsername());
            returnMap.put("name", tbuser.getName());
            returnMap.put("nick", tbuser.getNick());
            returnMap.put("phone", tbuser.getPhone());
            returnMap.put("yeara", "is love");
            returnMap.put("createdAt", tbuser.getCreatedDate());
            returnMap.put("modifiedAt", tbuser.getModifiedDate());
            result.add(returnMap);
        }

        return result;
    }
}
