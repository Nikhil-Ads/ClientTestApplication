package com.newgen.example.dao;

import com.newgen.example.model.Lock;

public interface LockDao {
	public Lock insert(Lock lock);

	public Lock delete(String id,String tenantId);

	public Lock findById(String id);

	public Lock save(Lock lock);

	public Lock findAndIncrementSharedCount(String id,String tenantId );

	public Lock findAndDecrementSharedCount(String id,String tenantId);
}
