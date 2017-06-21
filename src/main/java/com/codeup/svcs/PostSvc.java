package com.codeup.svcs;

import com.codeup.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by violet on 6/20/17.
 */


    @Service("postSvc")
    public class PostSvc {
        private List<Post> posts;

//        List<Post> posts = new ArrayList<>();

        public PostSvc() {
            createPosts();
        }

        public List<Post> findAll() {
            return posts;
        }

        public Post save(Post post) {
            post.setId( (long) posts.size() + 1);
            posts.add(post);
            return post;
        }

        public Post findOne(long id) {
            return posts.get((int) (id - 1));
        }

        private void createPosts() {
            posts = new ArrayList<>();

            save(new Post("playstation for sale", "$1000 OBO"));
            save(new Post("xbox for sale", "$1000 OBO"));
        }
    }


