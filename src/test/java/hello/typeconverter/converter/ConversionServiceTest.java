package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;

public class ConversionServiceTest {

    @Test
    void conversionService() {
        //1. 컨버터 등록
        //DefaultConversionService은 ConversionService, ConverterRegistry 구현체, 컨버터를 등록해주는 기능 제공
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        
        //2. 컨버터 사용
        //반환 값에 맞는 Converter를 자동으로 선택해 실행
        assertThat(conversionService.convert("10", Integer.class))
                .isEqualTo(10);
        assertThat(conversionService.convert(10, String.class))
                .isEqualTo("10");

        assertThat(conversionService.convert("127.0.0.1:8080", IpPort.class))
                .isEqualTo(new IpPort("127.0.0.1", 8080));
        assertThat(conversionService.convert(new IpPort("127.0.0.1", 8080), String.class))
                .isEqualTo("127.0.0.1:8080");
    }
}
