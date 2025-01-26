package seongrok.me.springbootdeveloper.controller;

import seongrok.me.springbootdeveloper.domain.Article;
import seongrok.me.springbootdeveloper.dto.AddArticleRequest;
import seongrok.me.springbootdeveloper.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  protected ObjectMapper objectMapper;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  BlogRepository blogRepository;

  @BeforeEach
  public void mockMvcSetUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
        .build();
    blogRepository.deleteAll();
  }

  @DisplayName("addArticle: Successfully adding blog post.")
  @Test
  public void addArticle() throws Exception {
    final String url = "/api/articles";
    final String title = "title";
    final String content = "content";
    final AddArticleRequest userRequest = new AddArticleRequest(title, content);

    final String requestBody = objectMapper.writeValueAsString(userRequest);

    ResultActions result = mockMvc.perform(post(url)
        .content(requestBody)
        .contentType(MediaType.APPLICATION_JSON_VALUE));
    result.andExpect(status().isCreated());
    List<Article> articles = blogRepository.findAll();

    assertThat(articles.size()).isEqualTo(1);
    assertThat(articles.get(0).getTitle()).isEqualTo(title);
    assertThat(articles.get(0).getContent()).isEqualTo(content);
  }
}
