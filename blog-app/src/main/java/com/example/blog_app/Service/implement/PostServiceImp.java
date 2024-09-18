package com.example.blog_app.Service.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.blog_app.Service.PostService;
import com.example.blog_app.model.Post;
import com.example.blog_app.model.User;
import com.example.blog_app.repository.PostRepository;

@Service
public class PostServiceImp implements PostService {

	private final PostRepository postRepository;
	
	@Autowired
    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
	@Override
	public Optional<Post> findForId(Long Id) {
		
		return postRepository.findById(Id);
	}

	@Override
	public Post save(Post post) {
		
		return postRepository.saveAndFlush(post);
	}

	
	@Override
	public Page<Post> findByUserOrderByDatePageable(User user, int page) {
	    return postRepository.findByUserOrderByCreateDateDesc(user, PageRequest.of(subtractPageByOne(page), 5));
	}
	
	@Override
	public Page<Post> findAllOrderedByDatePageable(int page) {
	    return postRepository.findAllByOrderByCreateDateDesc(PageRequest.of(subtractPageByOne(page), 5));
	}

	@Override
	public void delete(Post post) {
		postRepository.delete(post);
		
	}
private int subtractPageByOne(int page){
        return (page < 1) ? 0 : page - 1;
    }
	 
	
}