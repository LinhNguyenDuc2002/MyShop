package com.example.myshop.repository.predicate;

import com.example.myshop.entity.QCategory;
import org.springframework.util.StringUtils;

public class CategoryPredicate extends BasePredicate {
    private final static QCategory qCategory = QCategory.category;

    public CategoryPredicate search(String key) {
        if (StringUtils.hasText(key)) {
            criteria.and(qCategory.name.containsIgnoreCase(key));
        }

        return this;
    }
}
