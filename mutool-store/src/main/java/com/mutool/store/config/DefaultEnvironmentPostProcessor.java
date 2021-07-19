package com.mutool.store.config;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.SystemUtil;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.Properties;

/**
 * 描述：默认配置环境变量设置<br>
 * 作者：les<br>
 * 日期：2021/4/15 09:14<br>
 */
public class DefaultEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @SneakyThrows
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        //项目启动优先加载的配置项
        Resource[] configResource = new PathMatchingResourcePatternResolver()
                .getResources("classpath*:default_config.properties");

        //String defaultDbPath = "jdbc:h2:file:"+SystemUtil.getUserInfo().getHomeDir()+"/mutool/data";
        String mutoolPath = SystemUtil.getUserInfo().getHomeDir()+"/mutool/";
        if(!FileUtil.exist(mutoolPath)){
            FileUtil.mkdir(mutoolPath);
        }
        String defaultDbPath = "jdbc:sqlite:"+mutoolPath+"data.db";
        for(Resource resource : configResource){
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            //设置数据库默认连接地址
            if(StrUtil.isBlank(properties.getProperty("spring.datasource.url"))){
                properties.setProperty("spring.datasource.url", defaultDbPath);
            }
            PropertiesPropertySource proSource = new PropertiesPropertySource(resource.getURL().toString(), properties);
            environment.getPropertySources().addLast(proSource);
        }
    }

}
