package com.ipub.common.base.config;

import com.ipub.common.base.model.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * ResponseHandlerConfiguration
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@AutoConfiguration
@RestControllerAdvice(annotations = RestController.class)
public class ResponseHandlerConfiguration implements ResponseBodyAdvice<Object> {
    /**
     * 服务名
     */
    @Value("${spring.application.name:unknown}")
    private String svc;

    @Override
    public boolean supports(MethodParameter returnType,
                            @Nullable Class<? extends HttpMessageConverter<?>> converterType) {
        return !Void.TYPE.equals(returnType.getParameterType());
    }

    @Override
    @Nullable
    public Object beforeBodyWrite(Object body, @Nullable MethodParameter returnType,
                                  @Nullable MediaType selectedContentType,
                                  @Nullable Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @Nullable ServerHttpRequest request, @Nullable ServerHttpResponse response) {
        R<?> r = body instanceof R<?> inst ? inst : R.success(body);
        r.setSvc(this.svc);
        return r;
    }
}
