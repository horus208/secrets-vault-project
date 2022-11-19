package com.example.authenticationserver.persistance;

import com.example.authenticationserver.model.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberProfileRepo extends JpaRepository<MemberProfile,Integer>
{

}
