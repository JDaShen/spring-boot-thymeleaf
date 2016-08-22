package com.cloudcof;

import com.cloudcof.domain.CofType;
import com.cloudcof.domain.CofTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by simon on 2016/8/22.
 */
@Controller
@RequestMapping("coftypes")
public class CofTypeController {
    @Autowired
    private CofTypeRepository cofTypeRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> get(@RequestParam Integer ownerId, Integer limit, Integer offset){
        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("total", cofTypeRepository.count());
        //PageRequest的参数page必须大于0
        responseMap.put("rows", cofTypeRepository.findByOwnerId(ownerId, new PageRequest(offset/limit-1, limit)));

        return responseMap;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> post(@RequestParam MultipartFile[] cafImages, HttpServletRequest request, CofType cofType){
        Map<String, Object> responseMap = new LinkedHashMap<>();

        return responseMap;
    }
}
