package com.example.myshop.repository.predicate;

import com.example.myshop.entity.QAttribute;
import org.springframework.util.StringUtils;

import java.util.List;

public class AttributePredicate extends BasePredicate {
    private final static QAttribute qAttribute = QAttribute.attribute;

    /**
     * @param type
     * @return
     */
    public AttributePredicate type(String type) {
        if (StringUtils.hasText(type)) {
            criteria.and(qAttribute.type.containsIgnoreCase(type));
        }

        return this;
    }

    public AttributePredicate inIds(List<String> ids) {
        if (!ids.isEmpty()) {
            criteria.and(qAttribute.id.in(ids));
        }

        return this;
    }
}
