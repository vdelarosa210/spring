package com.codeup.Repositories;

import com.codeup.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by violet on 6/22/17.
 */
public interface PostsRepository extends CrudRepository <Post, Long> {
}
