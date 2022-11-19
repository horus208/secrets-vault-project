package com.example.authenticationserver.controllers;


import com.example.authenticationserver.dto.updaters.Updater;
import com.example.authenticationserver.model.MemberProfile;
import com.example.authenticationserver.model.Secret;
import com.example.authenticationserver.service.member.MemberProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/profiles")
public class MemberProfileController
{
    @Autowired
    MemberProfileService memberProfileService;

    @PutMapping(path="/{profileId}")
    public ResponseEntity<?> updateProfile(@RequestBody Updater<MemberProfile> updater,@PathVariable Integer profileId)
    {
        memberProfileService.updateMemberProfile(updater,profileId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @PostMapping(path = "/{profileId}/secrets")
    public ResponseEntity<?> addSecret(@RequestBody Secret secret, @PathVariable Integer profileId)
    {

        memberProfileService.addSecret(secret,profileId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(path = "/{profileId}/secrets/{secretId}")
    public ResponseEntity<?> deleteSecret(@PathVariable Integer profileId,
                                          @PathVariable Integer secretId)
    {

        memberProfileService.deleteSecret(secretId,profileId);
        return new ResponseEntity<>(HttpStatus.OK);

    }



}
