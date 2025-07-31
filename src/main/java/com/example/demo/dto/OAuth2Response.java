package com.example.demo.dto;

//Google,Naver 데이터를 받는 규격
public interface OAuth2Response {

    //제공자(Ex.Naver, Google, ...)
    String getProvider();

    //제공자에서 발급해주는 아이디(번호)
    String getProviderId();

    //이메일
    String getEmail();

    //이름
    String getName();
}
