package com.ipub.common.base.config;

import com.ipub.common.base.constant.DateFmt;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.ipub.common.base.constant.DateFmt.DT;
import static com.ipub.common.base.constant.DateFmt.DT_TM;
import static com.ipub.common.base.constant.DateFmt.TM;
import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

/**
 * WebMvcConfiguration
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@AutoConfiguration
@ConditionalOnWebApplication(type = SERVLET)
public class WebMvcConfiguration implements WebMvcConfigurer {
    /**
     * 增加GET请求参数中时间类型转换
     * <ul>
     * <li>HH:mm:ss -> LocalTime</li>
     * <li>yyyy-MM-dd -> LocalDate</li>
     * <li>yyyy-MM-dd HH:mm:ss -> LocalDateTime</li>
     * </ul>
     *
     * @param registry FormatterRegistry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setTimeFormatter(DateFmt.crtDtFmt(DT));
        registrar.setDateFormatter(DateFmt.crtDtFmt(TM));
        registrar.setDateTimeFormatter(DateFmt.crtDtFmt(DT_TM));
        registrar.registerFormatters(registry);
    }
}
