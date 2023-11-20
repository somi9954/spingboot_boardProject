package boardProject.models.post;

import boardProject.entities.Post;
import boardProject.repositories.PostRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class PostService {

    @Autowired
   private PostRepository postRepository;

    public List<Post> fetchPosts() {
        // 데이터베이스에서 게시글을 조회
        return postRepository.findAll();
    }

    // @Scheduled 어노테이션을 사용하여 매일 새벽 1시에 실행
    @Scheduled(cron = "0 0 1 * * ?")
    public void fetchPostsDaily() {
        // 데이터베이스에서 게시글을 조회
        List<Post> posts = postRepository.findAll();

        // 게시글 조회
        posts.forEach(post -> {
            System.out.println("Title: " + post.getTitle() + ", Content: " + post.getContent());
        });
    }
}

