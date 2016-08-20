package com.cloudcof.controller;

import com.cloudcof.domain.Message;
import com.cloudcof.domain.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by simon on 2016/8/20.
 */
@Controller
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(value = "message", method = RequestMethod.GET)
    public String messages(Model model) {
        model.addAttribute("messages", messageRepository.findAll());
        return "message/list";
    }

    /*@RequestMapping(value = "message", method = RequestMethod.GET)
    public ModelAndView messages() {
        ModelAndView mav = new ModelAndView("message/list");
        mav.addObject("messages", messageRepository.findAll());
        return mav;
    }

    @ModelAttribute("messages")
    public List<Message> messages() {
        return messageRepository.findAll();
    }*/
}
