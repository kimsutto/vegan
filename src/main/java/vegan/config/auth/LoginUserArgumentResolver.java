package vegan.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;
import vegan.config.auth.dto.SessionUser;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

  private final HttpSession httpSession;

  @Override
  public boolean supportsParameter(MethodParameter parameter) { //컨트롤러 메서드의 특정 파라미터 지원하는지 판단
    boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
    boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
    return isLoginUserAnnotation && isUserClass;
  }

  //파라미터에 전달할 객체를 생성. 세션에서 객체 가져옴
  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    return httpSession.getAttribute("user");
  }
}

//HandlerMethodArgumentResolver 인터페이스 구현한 클래스로 조건에 맞는 경우 메소드가 있다면 그 구현체가 지정한 값으로
//해당 메소드의 파라미터로 넘길 수 있다.