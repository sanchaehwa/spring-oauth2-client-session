package com.example.demo.service;

import com.example.demo.dto.CustomOAuth2User;
import com.example.demo.dto.GoogleResponse;
import com.example.demo.dto.NaverResponse;
import com.example.demo.dto.OAuth2Response;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    //DefaultOAuth2UserService OAuth2UserService 구현체
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("oAuth2User.getAttributes() = " + oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        //registrationId == Naver
        if(registrationId.equals("naver")){
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        //registrationId == Google
        else if(registrationId.equals("google")){
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());

        }
        //Naver , Google 도 아닌 경우에는 NUll 반환
        else {
            return null;
        }
        String username = oAuth2Response.getProvider()+" "+ oAuth2Response.getProviderId();
        UserEntity existData = userRepository.findByUsername(username);
        String role = "ROLE_USER";

        if (existData == null) {

            //신규인 경우
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setRole("ROLE_USER");

            userRepository.save(userEntity);
        }
        else{

            role =existData.getRole();
            //기존회원인 경우
            existData.setEmail(oAuth2Response.getEmail());
            userRepository.save(existData);
        }


        return new CustomOAuth2User(oAuth2Response, role);

    }


}
