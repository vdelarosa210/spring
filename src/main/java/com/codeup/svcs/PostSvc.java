package com.codeup.svcs;

import com.codeup.Repositories.PostsRepository;
import com.codeup.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by violet on 6/20/17.
 */


@Service("postSvc")
public class PostSvc {
    private final PostsRepository postsDao;

    @Autowired
    public PostSvc(PostsRepository postsDao) {
        this.postsDao = postsDao;
    }

    public Iterable<Post> findAll() {
        return postsDao.findAll();
    }

    public Post save(Post post) {
        return postsDao.save(post);
    }

    public Post findOne(long id)
    {
        return postsDao.findOne(id);
    }

    public void deletePost (long id){
        Post post = this.findOne(id);
        postsDao.delete(post);
    }

}


