package vegan.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//선언한 모든 필드에 get메소드 생성
//RequiredArgsConstructor : 선언된 모든 final 필드가 포함된 생성자 생성, final 없이는 포함X
@Getter
@RequiredArgsConstructor
public class HelloResponseDto {

  private final String name;
  private final int amount;

}