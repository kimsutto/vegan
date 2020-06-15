package vegan.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vegan.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
  private String title;
  private String content;
  private String author;
  @Builder
  public PostsSaveRequestDto(String title, String content, String author){
    this.title = title;
    this.content = content;
    this.author = author;
  }
  public Posts toEntity() {
    return Posts.builder()
              .title(title)
              .content(content)
              .author(author)
              .build();
  }
}

//이 클래스는 domain.posts.Posts인 Entity 클래스와 거의 유사하다. 하지만 Entity 클래스는 Request/Response클래스로 사용하면 안된다
// 디비와 맞닿은 핵심 클래스 이기 때문이다. 꼭 Entity 클래스와 Controller에서 쓸 Dto는 분리해서 사용해야 한다.
//이 클래스는 Request와 Response용 Dto 이다. View를 위한 클래스라 자주 변경이 필요하다.