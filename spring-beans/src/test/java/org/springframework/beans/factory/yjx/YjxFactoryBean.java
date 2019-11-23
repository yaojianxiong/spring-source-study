package org.springframework.beans.factory.yjx;

import org.springframework.beans.factory.FactoryBean;

/**
 * 2019/11/19 17:08
 *
 * @author: yjx
 * @since 1.0
 */
public class YjxFactoryBean implements FactoryBean<YjxBean> {
    @Override
    public YjxBean getObject() throws Exception {
        return new YjxBean();
    }

    @Override
    public Class<?> getObjectType() {
        return YjxBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
