package com.spring.first_student_mgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// TODO（可选 Bonus，暂不实现）：cache（缓存）、log（业务日志，目前仅有异常错误日志）、async（异步处理）。
public class FirstStudentMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstStudentMgmtApplication.class, args);
	}

}
