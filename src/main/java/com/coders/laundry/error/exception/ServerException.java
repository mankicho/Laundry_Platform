package com.coders.laundry.error.exception;

import com.coders.laundry.error.Detail;
import com.coders.laundry.error.ErrorResponse;
import java.io.Serial;

public class ServerException extends BaseException {

  @Serial
  private static final long serialVersionUID = -7164442729403054499L;

  private Detail detail;

  public ServerException(String message, Detail detail) {
    super(message);
    this.detail = detail;
  }

  public ServerException(String message) {
    this(message, null);
  }

  @Override
  public ErrorResponse buildErrorResponse() {
    return ErrorResponse.builder()
        .message(getMessage())
        .detail(detail)
        .build();
  }
}
