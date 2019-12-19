package com.test.controller;

import com.test.exception.ValidationException;
import com.test.responseDTO.UserRegisterDto;
import com.test.serviveImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AppController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserRegisterDto register(@RequestParam(value = "file",required = true) MultipartFile file){
        return userService.register(file);
    }

    @RequestMapping(value = "/download/{file}", method = RequestMethod.GET)
    public void download(@PathVariable("file") String fileUrl, HttpServletResponse response) throws ValidationException{
        userService.download(fileUrl,response);
    }
}
