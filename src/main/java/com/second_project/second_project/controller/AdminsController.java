package com.second_project.second_project.controller;

import com.second_project.second_project.model.Admins;
import com.second_project.second_project.repository.AdminRepository;
import com.second_project.second_project.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AdminsController {
    @Autowired
    private AdminRepository adminRepository;

    private static String UPLOAD_DIRECTORY = System.getProperty("user.dir")+"/uploads";

    @GetMapping("/admin")
    public ResponseEntity<Object> findAllAdmin(@RequestParam(name = "name", defaultValue = "") String name)
    {
        try{
            List<Admins> admins;
            if(StringUtils.hasText(name))
            {
                admins = adminRepository.findAdminByName(name);
            }else{
                admins = adminRepository.findAll();
            }
            if(admins.isEmpty())
            {
                return ResponseHandler.generateResponse("Data Admin Empty", HttpStatus.MULTI_STATUS, null);
            }
            return ResponseHandler.generateResponse("Success Retrived Data", HttpStatus.OK, admins);
        }catch (Exception e)
        {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Object> adminById(@PathVariable("id") Long id)
    {
        Admins admins = adminRepository.findAdminById(id);
        try{
            if(ObjectUtils.isEmpty(admins)){
                return ResponseHandler.generateResponse("Data Not Found", HttpStatus.MULTI_STATUS, null);
            }
            return ResponseHandler.generateResponse("Success Retrived Data", HttpStatus.OK, admins);
        }catch (Exception e)
        {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(path = "/admin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAdmin(@RequestBody Admins admins)
    {
        try{
            return ResponseHandler.generateResponse("Success Created Admin", HttpStatus.CREATED, adminRepository.save(admins));
        }catch (Exception e)
        {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping(path = "/admin/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateAdmin(@PathVariable("id") Long id, @RequestBody Admins admins)
    {
        Admins adminsData = adminRepository.findAdminById(id);
        try{
            if(!ObjectUtils.isEmpty(adminsData))
            {
                return ResponseHandler.generateResponse("Admin Updated", HttpStatus.OK, adminRepository.save(admins));
            }
            return ResponseHandler.generateResponse("Admin Not Found", HttpStatus.NOT_FOUND, null);
        }catch (Exception e)
        {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
