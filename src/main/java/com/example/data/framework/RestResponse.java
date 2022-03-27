package com.example.data.framework;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

@Getter
@Setter
@ApiModel(value = "RestResponse", description = "基础响应信息返")
public class RestResponse<T> {

    @ApiModelProperty(example = "200", value = "响应编码")
    private Integer code;

    @ApiModelProperty(example = "success", value = "响应消息")
    private String message;

    @ApiModelProperty(example = "uuid", value = "请求标识")
    private String requestId;

    @ApiModelProperty(example = "2020-01-01T00:00:00.100", value = "响应时间戳")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp;

    private RestResponse() {
        this.code = 200;
        this.message = "SUCCESS";
        this.requestId = UUID.randomUUID().toString().replace("-", "").toLowerCase(Locale.ROOT);
        this.timestamp = LocalDateTime.now();
    }

    private RestResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.requestId = UUID.randomUUID().toString().replace("-", "").toLowerCase(Locale.ROOT);
        this.timestamp = LocalDateTime.now();
    }

    public static <T> RestResponse<T> success(T data) {
        return new SuccessEntity<>(data);
    }
    public static RestResponse<Void> success() {
        return new SuccessEntity<>();
    }


    public static RestResponse<Void> error(Integer code, String message) {
        return new FailureEntity<>(code, message);
    }

    public static RestResponse<Void> error(Integer code, String message, Object error, String path) {
        return new FailureEntity<>(code, message, error, path);
    }


    @Getter
    @Setter
    private static class SuccessEntity<T> extends RestResponse<T> {

        private T data;

        private SuccessEntity(T data) {
            super();
            this.data = data;
        }

        private SuccessEntity(Integer code, String message) {
            super(code, message);
        }

        private SuccessEntity(Integer code, String message, T data) {
            super(code, message);
            this.data = data;
        }

        private SuccessEntity() {
        }
    }


    @Getter
    @Setter
    private static class FailureEntity<Void> extends RestResponse<Void> {
        private Object error;
        private String path;

        private FailureEntity(Integer code, String message) {
            super(code, message);
        }

        private FailureEntity(Integer code, String message, Object error, String path) {
            super(code, message);
            this.error = error;
            this.path = path;
        }
    }

}





