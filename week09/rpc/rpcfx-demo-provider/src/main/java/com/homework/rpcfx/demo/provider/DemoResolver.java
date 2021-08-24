package com.homework.rpcfx.demo.provider;

import com.homework.rpcfx.core.api.RpcfxResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DemoResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object resolve(String serviceClass) {
        try {
            return this.applicationContext.getBean(Class.forName(serviceClass));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return this.applicationContext.getBean(serviceClass);
    }
}
