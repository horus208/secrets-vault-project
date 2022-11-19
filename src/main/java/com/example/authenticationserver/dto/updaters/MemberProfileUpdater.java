package com.example.authenticationserver.dto.updaters;

import com.example.authenticationserver.model.MemberProfile;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


@NoArgsConstructor
public class MemberProfileUpdater implements Updater<MemberProfile> {

    @NotEmpty @Min(0)
    Integer id;

    @NotEmpty(message = "first name shouldn't be null") @Length(min = 3,max = 20)
    private String firstName;
    @NotEmpty(message = "second name shouldn't be null") @Length(min = 3,max = 20)
    private String secondName;

    @Override
    public void update(MemberProfile toBeUpdated)
    {
        toBeUpdated.setFirstName(this.firstName);
        toBeUpdated.setSecondName(this.secondName);

    }


}
