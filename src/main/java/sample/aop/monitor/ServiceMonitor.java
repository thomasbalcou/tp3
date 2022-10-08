package sample.aop.monitor;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitor {

	//log des fonctions pour les beans
	@Before("execution(* sample.simple.*.*.*(..))")
	public void logServiceAccess(JoinPoint joinPoint) {
		 System.out.printf("Appel de %s avec %d paramètres%n",
                 joinPoint.toShortString(),
                 joinPoint.getArgs().length);
	}
	
	//log des controller rest
	@Before("execution(* sample.data.jpa.web.*.*(..))")
	public void logServiceAccessRest(JoinPoint joinPoint) {
		 System.out.printf("Appel de %s %n",
                 joinPoint.toShortString(),
                 joinPoint.getArgs().length);
	}
	
	
	//methode around verifie les exceptions
	@Around("execution(* sample.simple.bank.Bank.transfert(..))")
	public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
		System.out.println("Before invoking transfert() method");
		Object value = null;
		try {
			value = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("After invoking transfert() method. Return value="+value);
		return value;
	}

}