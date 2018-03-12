package com.warumono.app.schedulers;

public abstract class AbstractScheduler
{
	/*
	
	1       2       3     4             5      6               7
	second, minute, hour, day of month, month, day(s) of week, year [optional]
	
	+-------------------- [1] second (0 - 59)
	|  +----------------- [2] minute (0 - 59)
	|  |  +-------------- [3] hour (0 - 23)
	|  |  |  +----------- [4] day of month (1 - 31)
	|  |  |  |  +-------- [5] month (1 - 12)
	|  |  |  |  |  +----- [6] day(s) of week (0 - 6) (Sunday=0 or 7)
	|  |  |  |  |  |  +-- [7] year [optional]
	|  |  |  |  |  |  |
	*  *  *  *  *  *  *   command to be executed
	1  2  3  4  5  6  7
	
	// --------------------------------------------------------
	
	0 0 12 * * *		: 매일 12시에 실행
	0 15 10 * * *		: 매일 10시 15분에 실행
	0 * 14 * * *		: 매일 14시에 실행
	0 0/5 14 18 * * *	: 매일 14시, 18시에 시작해서 5분간격으로 실행
	0 0-5 14 * * *		: 매일 14시에 시작해서 0분동안 실행
	
	// --------------------------------------------------------
	
	*/
	
	public interface EveryDay
	{
		public static final String MIDNIGHT = "59 59 23 * * *";
	}
}
