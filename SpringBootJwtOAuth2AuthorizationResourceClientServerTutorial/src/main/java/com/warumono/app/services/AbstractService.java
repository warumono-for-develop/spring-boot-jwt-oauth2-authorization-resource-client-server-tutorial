package com.warumono.app.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import com.warumono.app.entities.AuditingEntity;
import com.warumono.app.exceptions.ExceptionReason;
import com.warumono.app.exceptions.RestException;
import com.warumono.app.repositories.AbstractRepository;

@Service
public abstract class AbstractService<T extends AbstractRepository<E>, E extends AuditingEntity>
{
	private T repository;
	
	public List<E> getAll()
	{
		return repository.findAll();
	}
	
	public E getByIdentity(String identity)
	{
		return repository.findByIdentity(identity);
	}
	
	public E store(E entity)
	{
		return repository.save(entity);
	}
	
	/**
	 * <pre>
	 * @method now
	 * @since 2018. 02. 26.
	 * @author warumono
	 * @Usage 
	 * <p>
	 * </p>
	 * @description
	 * <p>
	 * 현재 일시
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * @return Date
	 * </pre>
	 */
	protected Date now()
	{
		return new Date();
	}
	
	/**
	 * <pre>
	 * @method early
	 * @since 2018. 02. 26.
	 * @author warumono
	 * @Usage 
	 * <p>
	 * </p>
	 * @description
	 * <p>
	 * 날짜의 최초 시간(00:00:00)
	 * yyyy-MM-dd 00:00:00
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * @return Date
	 * </pre>
	 */
	protected Date early(@NotNull Date date)
	{
		return DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
	}

	/**
	 * <pre>
	 * @method lately
	 * @since 2018. 02. 26.
	 * @author warumono
	 * @Usage 
	 * <p>
	 * </p>
	 * @description
	 * <p>
	 * 날짜의 최종 시간(23:59:59)
	 * 2017-10-12 23:59:59
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * @return Date
	 * </pre>
	 */
	protected Date lately(@NotNull Date date)
	{
		return DateUtils.addSeconds(DateUtils.addDays(early(date), 1), -1);
	}
	
	/**
	 * <pre>
	 * @method required
	 * @since 2018. 02. 26.
	 * @author warumono
	 * @Usage 
	 * <p>
	 * </p>
	 * @description
	 * <p>
	 * null 이 아니면 obj 값 반환, 
	 * null 이면 throw OverallException
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * @return T
	 * </pre>
	 */
	protected <T> T required(T obj)
	{
		try
		{
			return Objects.requireNonNull(obj);
		}
		catch(NullPointerException e)
		{
			throw new RestException(ExceptionReason.REQUIRED);
		}
	}

	/**
	 * <pre>
	 * @method requiredTrue
	 * @since 2018. 02. 26.
	 * @author warumono
	 * @Usage 
	 * <p>
	 * </p>
	 * @description
	 * <p>
	 * 참이면 true, 
	 * 거짓이면 throw OverallException
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * @return Boolean
	 * </pre>
	 */
	protected Boolean requiredTrue(Boolean bool)
	{
		if(bool)
		{
			return Boolean.TRUE;
		}
		
		throw new RestException(ExceptionReason.REQUIRED);
	}
	
	/**
	 * <pre>
	 * @method nullified
	 * @since 2018. 02. 26.
	 * @author warumono
	 * @Usage 
	 * <p>
	 * </p>
	 * @description
	 * <p>
	 * null 이면 true, 
	 * null 이 아니면 false
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * @return Boolean
	 * </pre>
	 */
	protected <T> Boolean nullified(T obj)
	{
		return Objects.isNull(obj);
	}
	
	/**
	 * <pre>
	 * @method nonnullified
	 * @since 2018. 02. 26.
	 * @author warumono
	 * @Usage 
	 * <p>
	 * </p>
	 * @description
	 * <p>
	 * null 이 아니면 true, 
	 * null 이면 false
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * @return Boolean
	 * </pre>
	 */
	protected <T> Boolean nonnullified(T obj)
	{
		return Objects.nonNull(obj);
	}
}
