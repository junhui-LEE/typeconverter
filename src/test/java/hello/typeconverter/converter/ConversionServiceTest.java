package hello.typeconverter.converter;

import hello.typeconverter.converter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;

class ConversionServiceTest {
    // Converter를 ConversionService에 등록하면 ConversionService는
    // 등록한 Converter들에 대해서 개발자가 편리하게 변환하도록 기능을 제공하는데
    // Converter을  ConversionService에 어떻게 등록하고 ConversionService가
    // 제공하는 편리한 변환기능에 대해서 테스트 코드를 작성하면서 알아보자.
    @Test
    void conversionService(){
        // 등록
        // 먼저 우리가 사용할 Converter을 ConversionService에 등록해야 한다.
        // DefaultConversionService는 ConversionService인터페이스를 구현한 구현체라고 보면 된다.
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        // 사용
        // 사용법은 그냥 conversionService.convert(); 하면 된다.
        // conversionService.convert(); 안에 바뀔대상을 넣고 바뀌게 될 대상의 타입정보를 넣으면
        // convert(); 메소드 내에서 인자로 들어온 타입 정보를 보고 (등록된) 어떤 Converter을 쓸지
        // 찾은 다음에 찾은 Converter를 통해서 타입 변환을 해준다. 그래서 개발자는 그냥 conversionService.convert();
        // 만을 사용하면 된다.
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














