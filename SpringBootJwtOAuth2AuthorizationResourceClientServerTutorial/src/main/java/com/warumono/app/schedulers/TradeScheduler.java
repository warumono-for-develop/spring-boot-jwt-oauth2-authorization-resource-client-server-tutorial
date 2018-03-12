package com.warumono.app.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.warumono.app.exceptions.ExceptionReason;
import com.warumono.app.exceptions.RestException;
import com.warumono.app.schedulers.AbstractScheduler.EveryDay;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TradeScheduler
{
	/**
	 * <pre>
	 * @method scrap
	 * @since 2018. 02. 26.
	 * @author warumono
	 * @Usage 
	 * <p>
	 * </p>
	 * @description
	 * <p>
	 * 매일 자정이 될 무렵(23h 59m 59s) 거래만료 대상을 폐기 및 만료시킨다.
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * @return void
	 * </pre>
	 */
	@Scheduled(cron = EveryDay.MIDNIGHT)
	public void scrap()
	{
		try
		{
			log.debug("-------------------- CouponSerial switch 'ISSUED' to 'DISCARDED' --------------------");
			
			log.debug("-------------------- CouponSerial switch 'VERIFIED' to 'EXPIRED' --------------------");
		}
		catch(Exception e)
		{
			throw new RestException(ExceptionReason.SERVER_ERROR, e);
		}
	}
	
//	// 매월 1일 0시 0분 0초에 실행한다.
//	@Scheduled(cron = "0 0 0 1 * *")
//	public void anotherJob()
//	{
//		// 실행될 로직
//	}
//	
//	// 애플리케이션 시작 후 60초 후에 첫 실행, 그 후 매 60초마다 주기적으로 실행한다.
//	@Scheduled(initialDelay = 60000, fixedDelay = 60000)
//	public void otherJob()
//	{
//		// 실행될 로직
//	}
}
