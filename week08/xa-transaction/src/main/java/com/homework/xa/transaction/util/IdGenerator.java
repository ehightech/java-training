package com.homework.xa.transaction.util;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * @author bob
 */
@Component
public class IdGenerator implements IdentifierGenerator {
    final IdGeneratorOptions options = new IdGeneratorOptions((short) 1);

    @PostConstruct
    public void init() {
        YitIdHelper.setIdGenerator(options);
    }

    @Override
    public Long nextId(Object entity) {
        return YitIdHelper.nextId();
    }
}
