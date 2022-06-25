package dev.community.gdg.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommonResponse<T> {

	private String message;
	private ResultCode code;
	private T data;

	public static <U> CommonResponse<U> success() {
		return CommonResponse.<U>builder()
			.data(null)
			.code(ResultCode.SUCCESS)
			.message("응답에 성공하였습니다.")
			.build();
	}

	public static <U> CommonResponse<U> success(U data, ResultCode resultCode) {
		final CommonResponse<U> response = success();
		response.setData(data);
		response.setCode(resultCode);
		return response;
	}

	public static <U> CommonResponse<U> success(U data, ResultCode resultCode, String message) {
		final CommonResponse<U> response = success(data, resultCode);
		response.setMessage(message);
		return response;
	}

	public static <U> CommonResponse<U> failure(ResultCode resultCode) {
		return CommonResponse.<U>builder()
				.data(null)
				.code(resultCode)
				.message(resultCode.name())
				.build();
	}

}
