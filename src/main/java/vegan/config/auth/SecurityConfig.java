package vegan.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import vegan.domain.user.Role;

@RequiredArgsConstructor
@EnableWebSecurity //spring security 설정들 활성화 시킴
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomOAuth2UserService customOAuth2UserService;

  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http.csrf().disable().headers().frameOptions().disable() //h2 console화면 사용위해 해당 옵션들 disable
        .and()
        .authorizeRequests() //url별 권한관리 설정하는 옵션의 시작점..이게 있어야 antMatchers가능
        .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
        .antMatchers("/api/v1/**").hasRole(Role.USER.name())
        .anyRequest() //설정된 값들 외의 나머지 URL 나타냄
        .authenticated() //나머지 uRL들은 모두 인증된 사용자들에게만 허용하게함(로그인)
        .and().logout().logoutSuccessUrl("/") //로그아웃 기능에 대한 설정 진입점으로 로그아웃 성공시 / 주소로 이동
        .and().oauth2Login() //OAuth2로그인 기능에 대한 여러 설정 진입점
        .userInfoEndpoint() // OAuth2로그인 성공 후 사용자 정보 가져올 때 설정 담당
        .userService(customOAuth2UserService); // 로그인 성공 시 후속 조치 진행할 유저서비스 인터페이스의 구현체 등록
  } //.antMatchers는 권한 관리 대상을 지정하는 옵션으로 URL, HTTP메소드별 관리가 가능하다.

}
