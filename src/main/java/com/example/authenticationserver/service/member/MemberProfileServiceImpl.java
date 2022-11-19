package com.example.authenticationserver.service.member;

import com.example.authenticationserver.dto.updaters.Updater;
import com.example.authenticationserver.exceptions.ResourceNotFoundException;
import com.example.authenticationserver.model.MemberProfile;
import com.example.authenticationserver.model.Secret;
import com.example.authenticationserver.persistance.MemberProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberProfileServiceImpl implements MemberProfileService {

    @Autowired
    MemberProfileRepo memberProfileRepo;

    public MemberProfile getMemberProfile(Integer profileId)
    {
        Optional<MemberProfile> profile = memberProfileRepo.findById(profileId);
        if(profile.isEmpty())
            throw new ResourceNotFoundException("profile with id = "+profileId+" does not exist" );

        return profile.get();
    }

    @Override
    public void updateMemberProfile(Updater<MemberProfile> updater,Integer profileId)
    {
        MemberProfile profile = getMemberProfile(profileId);
        updater.update(profile);
        memberProfileRepo.save(profile);
    }

    @Override
    public void addSecret(Secret secret, Integer profileId)
    {

        secret.setId(null);
        MemberProfile profile = getMemberProfile(profileId);
        profile.getSecrets().add(secret);
        memberProfileRepo.save(profile);


    }

    @Override
    public void deleteSecret(Integer secretId, Integer profileId)
    {
        MemberProfile profile = getMemberProfile(profileId);
        List<Secret> secretList = profile.getSecrets();
        for(int i=0;i<secretList.size();i++)
        {
            if(secretList.get(i).getId().equals(secretId)) {secretList.remove(i); break;}

        }

        memberProfileRepo.save(profile);


    }


}
