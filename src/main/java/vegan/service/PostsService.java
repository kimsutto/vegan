package vegan.service;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vegan.domain.posts.Posts;
import vegan.domain.posts.PostsRepository;
import vegan.web.dto.PostsListResponseDto;
import vegan.web.dto.PostsResponseDto;
import vegan.web.dto.PostsSaveRequestDto;
import vegan.web.dto.PostsUpdateRequestDto;

@RequiredArgsConstructor //final로 선언된 모든 필드를 인자값으로 하는 생성자를 생성해준다.
@Service
public class PostsService {
  private final PostsRepository postsRepository;

  @Transactional
  public Long save(PostsSaveRequestDto requestDto){
    return postsRepository.save(requestDto.toEntity()).getId();
  }

  //update는 영속성 컨텍스트 유지 때문에 쿼리를 날릴 필요가 없음
  @Transactional
  public Long update(Long id, PostsUpdateRequestDto requestDto){
    Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id =" +id));
    posts.update(requestDto.getTitle(),requestDto.getContent());

    return id;
  }

  public PostsResponseDto findById(Long id){
    Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

    return new PostsResponseDto(entity);
  }

  @Transactional(readOnly = true) //트랙잭션 범위는 유지하되, 조회 기능만 남겨두어 조회속도 개선
  public List<PostsListResponseDto> findAllDesc(){
    return postsRepository.findAllDesc().stream()
        .map(PostsListResponseDto::new).collect( //Posts의 Stream을 map을 통해 PostsListResponseDto로 변환 -> List로 반환
        Collectors.toList());
  }

  @Transactional
  public void delete(Long id){
    Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

    postsRepository.delete(posts); //JPA에서 지원하는 delete 메소드 사용. entity를 파라미터로 삭제할 수 있고 id로도 가능
  }
}

//스프링에서 Bean을 주입받는 방식들은 Autowired, setter, 생성자가 있다.
//생성자로 주입받는 것을 가장 권장하고 @Autowired는 권장하지 않는다.
