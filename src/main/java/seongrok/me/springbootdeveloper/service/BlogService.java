package seongrok.me.springbootdeveloper.service;

import seongrok.me.springbootdeveloper.domain.Article;
import seongrok.me.springbootdeveloper.dto.AddArticleRequest;
import seongrok.me.springbootdeveloper.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogService {
  private final BlogRepository blogRepository;

  public Article save(AddArticleRequest request) {
    return blogRepository.save(request.toEntity());
  }
}
