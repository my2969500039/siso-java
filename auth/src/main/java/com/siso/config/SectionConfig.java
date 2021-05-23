package com.siso.config;

import com.VT.config.ColumnConfig;
import com.VT.section.aop.AccessUtil;
import com.VT.section.service.SectionColumnService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SectionConfig extends AccessUtil implements SectionColumnService {
    @Override
    public Class getEntityClass() {
        return null;
    }

    @Override
    public ColumnConfig getConfig() {
        return null;
    }

    @Override
    public String sectionId(String sectionId) {
        return null;
    }

    @Override
    public String value(String value) {
        return null;
    }

    @Override
    public String params(String params) {
        return null;
    }

    @Override
    public void save(Object o) {

    }
}
