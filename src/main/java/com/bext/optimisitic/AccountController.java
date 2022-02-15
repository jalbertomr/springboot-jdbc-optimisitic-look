package com.bext.optimisitic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bext.optimistic.dao.Account;
import com.bext.optimistic.dao.AccountDao;

@Controller
@RequestMapping(AccountController.ACCOUNT_BASE_URL)
public class AccountController {
	
	public static final String ACCOUNT_BASE_URL = "/v1/accounts";

	private static final Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountDao accountDao;
	
	@GetMapping("/{id}")
	@ResponseBody
	public String process(@PathVariable("id") final int id) {
		log.info("process id: {} ", id);
		Account account = accountDao.selectById(id);
		log.info("Account loaded: {}", account);
		
		accountService.save(account);
		return "processed and saved id: " + id;
	}
	
	@GetMapping("/test/{id}")
	@ResponseBody
	public String test(@PathVariable("id") final int id) {
		return "test" + id;
	}
}
