package com.cloudcof.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.cloudcof.domain.Barista;
import com.cloudcof.domain.BaristaRepository;
import com.cloudcof.domain.CofType;
import com.cloudcof.domain.CofTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    @Autowired
    private BaristaRepository baristaRepository;

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    public static final String ROOT="upload-dir";

    private final ResourceLoader resourceLoader;

    @Autowired
    public CofTypeController(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

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
        if (cafImages.length>0){
            int i=0;
            ArrayList<String> imageDescs = new ArrayList<>();
            for (MultipartFile cafImage : cafImages){
                i++;
                String contentType = cafImage.getContentType();
                if(contentType.startsWith("image")){
                    String realPath = request.getServletContext().getRealPath("/");
                    
                }
            }
        }
        return responseMap;
    }

    /**
     * 处理编辑咖啡种类
     * @param files
     * @param cofType
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/files")
    public String handleFilesUpload(@RequestParam("file") MultipartFile[] files,
                                    CofType cofType,
                                    RedirectAttributes redirectAttributes){
        //获得当前登录用户信息
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Barista barista = baristaRepository.findByName(userDetails.getUsername());
        cofType.setOwnerId(barista.getId());
        String imgDesc = "";
        if (files.length>1){
            for (MultipartFile file : files){
                if (!file.isEmpty()){
                    try{
                        String temp = System.currentTimeMillis()+file.getOriginalFilename();
                        Files.copy(file.getInputStream(), Paths.get(ROOT, temp));
                        imgDesc+=ROOT + "/" + temp+",";
//                        redirectAttributes.addFlashAttribute("message",
//                                "You successfully uploaded " + file.getOriginalFilename() + "!");
                    }catch (IOException |RuntimeException e){
//                        redirectAttributes.addFlashAttribute("message", "Failured to upload " + file.getOriginalFilename() + " => " + e.getMessage());
                    }
                }else{
//                    redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");
                }
            }
        }
        cofType.setImgDesc(imgDesc);
        cofTypeRepository.save(cofType);
        return "redirect:/coffee-type-edit";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/upload-dir/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename){
        try{
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
