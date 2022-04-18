package com.charlie.aclservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.alibaba.nacos.registry.NacosAutoServiceRegistration;
import org.springframework.cloud.alibaba.nacos.registry.NacosRegistration;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import java.lang.management.ManagementFactory;
import java.util.Set;

/**
 * 项目打包war情况下部署外部tomcat，需该方式注册nacos
 *
 * @author gblfy
 * @date 2022-03-29
 */
@Component
@Slf4j
public class NacosRegisterOnWar implements ApplicationRunner {

    @Autowired
    private NacosRegistration registration;

    @Autowired
    private NacosAutoServiceRegistration nacosAutoServiceRegistration;

    @Value("${server.port}")
    Integer port;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (registration != null && port != null) {
            Integer tomcatPort = port;
            try {
                tomcatPort = new Integer(getTomcatPort());
            } catch (Exception e) {
                log.warn("获取外部Tomcat端口异常：", e);
            }
            registration.setPort(port);
            nacosAutoServiceRegistration.start();
        }
    }

    /**
     * 获取外部tomcat端口
     */
    public String getTomcatPort() throws Exception {
        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"), Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
        String port = objectNames.iterator().next().getKeyProperty("port");
        return port;
    }
}

