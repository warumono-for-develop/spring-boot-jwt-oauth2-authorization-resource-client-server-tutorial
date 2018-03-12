package com.warumono.app.enums;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.warumono.app.models.packs.PayloadPack;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author warumono
 *
 */
@AllArgsConstructor
@Getter
public enum Payload
{
	/**
	 * <pre>
	 * @field TICKET_PAY_COMPLETED
	 * @type Payload
	 * @since 2017. 11. 30.
	 * @author warumono
	 * @description
	 * <p>
	 * 구매 완료시	이용권구매가 완료되었습니다.
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param ""
	 * </pre>
	 */
	TICKET_PAY_COMPLETED("이용권구매가 완료되었습니다."), 

	/**
	 * <pre>
	 * @field TICKET_CANCEL_REQUESTED
	 * @type Payload
	 * @since 2017. 11. 30.
	 * @author warumono
	 * @description
	 * <p>
	 * 구매 취소시 발송	이용권구매 취소요청이 정상적으로 처리완료되었습니다.
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * </pre>
	 */
	TICKET_CANCEL_REQUESTED("이용권구매 취소요청이 정상적으로 처리완료되었습니다."), 
	
	/**
	 * <pre>
	 * @field TICKET_REFUND_COMPLETED
	 * @type Payload
	 * @since 2017. 11. 30.
	 * @author warumono
	 * @description
	 * <p>
	 * 환불완료시	이용권구매 환불요청이 정상적으로 처리완료되었습니다.
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * </pre>
	 */
	TICKET_REFUND_COMPLETED("이용권구매 환불요청이 정상적으로 처리완료되었습니다."), 
	
	/**
	 * <pre>
	 * @field COUPON_ALMOST_EXPIRATION
	 * @type Payload
	 * @since 2017. 11. 30.
	 * @author warumono
	 * @description
	 * <p>
	 * 쿠폰 만료 안내	쿠폰의 사용종료일이 얼마남지 않았습니다.
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * </pre>
	 */
	@Deprecated
	COUPON_ALMOST_EXPIRATION("쿠폰의 사용종료일이 얼마남지 않았습니다."), 
	
	/**
	 * <pre>
	 * @field COUPON_ISSUED
	 * @type Payload
	 * @since 2017. 11. 30.
	 * @author warumono
	 * @description
	 * <p>
	 * 쿠폰 자동발급시	이용해주셔서 감사합니다. 감사의 뜻으로 쿠폰을 발급해드렸습니다.
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * </pre>
	 */
	COUPON_ISSUED("이용해주셔서 감사합니다. 감사의 뜻으로 쿠폰을 발급해드렸습니다."), 
	
	/**
	 * <pre>
	 * @field RESET_PASSWORD_COMPLETED
	 * @type Payload
	 * @since 2017. 11. 30.
	 * @author warumono
	 * @description
	 * <p>
	 * 패스워드 재설정시	새로운 비밀번호로 재설정되었습니다.
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * </pre>
	 */
	RESET_PASSWORD_COMPLETED("새로운 비밀번호로 재설정되었습니다."), 
	
	/**
	 * <pre>
	 * @field RESET_PHONE_COMPLETED
	 * @type Payload
	 * @since 2017. 11. 30.
	 * @author warumono
	 * @description
	 * <p>
	 * 전화번호 재설정시	새로운 전화번호로 변경되었습니다.
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * </pre>
	 */
	RESET_PHONE_COMPLETED("새로운 전화번호로 변경되었습니다."),
	
	/**
	 * <pre>
	 * @field RESET_PHONE_FAILURE
	 * @type Payload
	 * @since 2017. 11. 30.
	 * @author warumono
	 * @description
	 * <p>
	 * 전화번호 재설정시	전화번호 변경처리 중 오류가 발생하였습니다.
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * </pre>
	 */
	RESET_PHONE_FAILURE("전화번호 변경처리 중 오류가 발생하였습니다."), 
	
	/**
	 * <pre>
	 * @field TICKET_MOVIE_RESERVATION_REQUESTED
	 * @type Payload
	 * @since 2017. 11. 30.
	 * @author warumono
	 * @description
	 * <p>
	 * 예매 신청시 (영화)	영화예매 신청이 정상적으로 신청되었고, 예매가 확정되면 바로 알려드리겠습니다.
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * </pre>
	 */
	TICKET_MOVIE_RESERVATION_REQUESTED("영화예매 신청이 정상적으로 신청되었고, 예매가 확정되면 바로 알려드리겠습니다."), 
	
	/**
	 * <pre>
	 * @field TICKET_MOVIE_RESERVATION_COMPLETED
	 * @type Payload
	 * @since 2017. 11. 30.
	 * @author warumono
	 * @description
	 * <p>
	 * 예매 확정시 (영화)	영화예매가 확정되어 좌석이 배정되었습니다!
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * </pre>
	 */
	@Deprecated
	TICKET_MOVIE_RESERVATION_COMPLETED("영화예매가 확정되어 좌석이 배정되었습니다!"), 
	
	/**
	 * <pre>
	 * @field TICKET_THEATRE_RESERVATION_COMPLETED
	 * @type Payload
	 * @since 2017. 11. 30.
	 * @author warumono
	 * @description
	 * <p>
	 * 예매 완료시 (연극)	연극예매가 정상적으로 처리완료되었습니다.
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * </pre>
	 */
	TICKET_THEATRE_RESERVATION_COMPLETED("연극예매가 정상적으로 처리완료되었습니다."), 
	
	/**
	 * <pre>
	 * @field INQUIRY_REQUESTED
	 * @type Payload
	 * @since 2017. 11. 30.
	 * @author warumono
	 * @description
	 * <p>
	 * 1:1문의 시	문의가 정상적으로 등록되었고, 확인처리 후 답변드리겠습니다.
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * </pre>
	 */
	@Deprecated
	INQUIRY_REQUESTED("문의가 정상적으로 등록되었고, 확인처리 후 답변드리겠습니다."), 
	
	/**
	 * <pre>
	 * @field INQUIRY_ANSWERED
	 * @type Payload
	 * @since 2017. 11. 30.
	 * @author warumono
	 * @description
	 * <p>
	 * 1:1문의 답변등록시	문의사항에 대해 답변드렸습니다.
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * </pre>
	 */
	@Deprecated
	INQUIRY_ANSWERED("문의사항에 대해 답변드렸습니다."),
	
	/**
	 * <pre>
	 * @field EVENT_OCCURED
	 * @type Payload
	 * @since 2017. 11. 30.
	 * @author warumono
	 * @description
	 * <p>
	 * 새로운 이벤트 등록 시	새로운 이벤트가 등록되었습니다. 다른 회원이 채가기전에 서두르세요!
	 * </p>
	 * @see 
	 * <p>
	 * </p>
	 * @param 
	 * </pre>
	 */
	EVENT_OCCURED("새로운 이벤트가 등록되었습니다. 다른 회원이 채가기전에 서두르세요!"), 
	;

	public static ObjectMapper jsonObjectMapper;
	
	@Autowired
	private void setJsonObjectMapper(ObjectMapper jsonObjectMapper){ Payload.jsonObjectMapper = jsonObjectMapper; }

	private String message;
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	public static final Map<String, Object> message(Payload payload)
	{
		Map<String, Object> message = Maps.newHashMap();
		message.put(payload.name().toLowerCase(), payload.getMessage());
		
		return message;
	}
	
	public static final String load(Payload payload, String extra)
	{
		try
		{
			Map<String, String> map = Maps.newHashMap();
			map.put("signal", payload.name());
			map.put("decript", payload.getMessage());
			map.put("extra", extra);
			
			return OBJECT_MAPPER.writeValueAsString(map);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw (RuntimeException)e;
		}
	}
	
	public static final PayloadPack pack(Payload payload, Object extra)
	{
//		PayloadPack.of(signal, descript, extra)
//		PayloadPack.on(signal, descript)
		return PayloadPack.builder()
				.signal(payload.name())
				.descript(payload.getMessage())
				.extra(extra)
				.build();
	}
}
