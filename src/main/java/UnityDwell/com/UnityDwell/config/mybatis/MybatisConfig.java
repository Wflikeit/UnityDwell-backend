package UnityDwell.com.UnityDwell.config.mybatis;

import UnityDwell.com.UnityDwell.UnityDwellApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackageClasses = UnityDwellApplication.class)
public class MybatisConfig {}