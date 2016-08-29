package com.cloudcof.controller;

import com.cloudcof.domain.AppVersion;
import com.cloudcof.domain.AppVersionRepository;
import com.cloudcof.util.ServerAppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by simon on 2016/8/26.
 */
@Controller
@RequestMapping("/app-version")
public class AppVersionController {
    @Autowired
    private AppVersionRepository appVersionRepository;

    public static final String ROOT = "upload-dir/app-version";

    private final ResourceLoader resourceLoader;

    @Autowired
    public AppVersionController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String provideAppVersionInfo(Model model) throws IOException{
        //如果upload-dir目录不存在，则创建该目录
        if (!Files.exists(Paths.get(ROOT))){
            Files.createDirectory(Paths.get(ROOT));
        }
        model.addAttribute("files", Files.walk(Paths.get(ROOT))
                .filter(path -> !path.equals(Paths.get(ROOT)))
                .map(path -> Paths.get(ROOT).relativize(path))
                .map(path -> linkTo(methodOn(AppVersionController.class).getFile(path.toString())).withRel(path.toString()))
                .collect(Collectors.toList()));
        return "subpages/app-version";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename){
        try{
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleFileUpload(
            @RequestParam("file") MultipartFile file,
            AppVersion appVersion,
            RedirectAttributes redirectAttributes){
        if (!file.isEmpty()){
            try{
                Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + file.getOriginalFilename() + "!");
                appVersion.setAppUrl(ROOT + "/" + file.getOriginalFilename());
                appVersion.setUploadTime(System.currentTimeMillis());
                appVersionRepository.save(appVersion);
            }catch (IOException|RuntimeException e){
                redirectAttributes.addFlashAttribute("message", "Failured to upload " + file.getOriginalFilename() + " => " + e.getMessage());
            }
        }else{
            redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");
        }
        return "redirect:/app-version";
    }

}
