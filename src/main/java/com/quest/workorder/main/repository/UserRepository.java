package com.quest.workorder.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.quest.workorder.main.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	//User findOne(long userId);

	//User findOne(long userId);
	
	
}