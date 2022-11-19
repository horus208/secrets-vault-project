package com.example.authenticationserver.service.member;

import com.example.authenticationserver.dto.updaters.Updater;
import com.example.authenticationserver.model.MemberProfile;
import com.example.authenticationserver.model.Secret;

public interface MemberProfileService {
    public void addSecret(Secret secret,Integer profileId);
    public void deleteSecret(Integer secretId,Integer profileId);
    public void updateMemberProfile(Updater<MemberProfile> updater,Integer profileId);
}
