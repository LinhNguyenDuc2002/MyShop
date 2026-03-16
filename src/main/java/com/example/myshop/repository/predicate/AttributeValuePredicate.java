package com.example.myshop.repository.predicate;

import com.example.myshop.entity.QAttributeValue;
import org.springframework.util.StringUtils;

public class AttributeValuePredicate extends BasePredicate {
    private final static QAttributeValue qAttributeValue = QAttributeValue.attributeValue;

    /**
     * @param key
     * @return
     */
    public AttributeValuePredicate search(String key) {
        if (StringUtils.hasText(key)) {
            criteria.and(qAttributeValue.value.containsIgnoreCase(key));
        }

        return this;
    }

    /**
     * @param id
     * @return
     */
    public AttributeValuePredicate id(String id) {
        if (id != null) {
            criteria.and(qAttributeValue.attribute.id.eq(id));
        }

        return this;
    }
}
