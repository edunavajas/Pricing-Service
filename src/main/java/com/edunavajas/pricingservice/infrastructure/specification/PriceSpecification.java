package com.edunavajas.pricingservice.infrastructure.specification;

import com.edunavajas.pricingservice.infrastructure.persistence.h2.jpa.entities.PriceEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.OffsetDateTime;

@AllArgsConstructor
public class PriceSpecification implements Specification<PriceEntity> {

    private final OffsetDateTime dateTime;
    private final Integer productId;
    private final Integer brandId;

    @Override
    public Predicate toPredicate(Root<PriceEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (dateTime != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), dateTime));
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("endDate"), dateTime));
        }
        if (productId != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("productId"), productId));
        }
        if (brandId != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("brandId"), brandId));
        }

        query.orderBy(criteriaBuilder.desc(root.get("priority")));

        return predicate;
    }

}
