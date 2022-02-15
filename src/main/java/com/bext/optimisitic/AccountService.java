package com.bext.optimisitic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bext.optimistic.dao.Account;
import com.bext.optimistic.dao.AccountDao;

@Service
@ComponentScan("com.bext.optimistic.dao")
public class AccountService {

	@Autowired
	private AccountDao accountDao;
	
	@Transactional
	public void save(final Account account){
		int updateCount = accountDao.update(account);
		
		if (updateCount != 1) {
			throw new IllegalStateException("Data is Stale. Please Retry");
		}
	}
}
