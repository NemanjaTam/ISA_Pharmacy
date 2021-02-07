package com.tim40.tim40.service;

import java.util.List;

import com.tim40.tim40.model.User;

public interface IUserService {
	List<User> findAll();
	User findById(Long id);
	User create(User user) throws Exception;
	User update(User user) throws Exception;
    void delete(Long id);
}
