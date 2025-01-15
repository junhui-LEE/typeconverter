package hello.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIntegerConverter implements Converter<String, Integer>{
    // 먼저 가장 단순한 형태인 문자를 숫자로 바꾸는 타입 컨버터를 만들어 보자
    @Override
    public Integer convert(String source){
        log.info("convert source={}", source);
        return Integer.valueOf(source);
    }

}
