package com.example.onlinecourses.utils;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Optional;

public class QueryDslUtil {
    private QueryDslUtil() {
    }

    public static Optional<BooleanExpression> optEqPredicate(StringExpression stringExpression, String word) {
        return Optional.ofNullable(word)
                .filter(StringUtils::hasText)
                .map(stringExpression::eq);
    }

    public static Optional<BooleanExpression> optEqOrEmptyWordPredicate(StringExpression stringExpression, String word, BooleanExpression emptyExpression) {
        return Optional.ofNullable(word)
                .filter(StringUtils::hasText)
                .map(it -> {
                    if ("EMPTY".equalsIgnoreCase(word)) {
                        return emptyExpression.or(stringExpression.eq(word));
                    } else {
                        return stringExpression.eq(word);
                    }
                });
    }

    public static Optional<BooleanExpression> optContainsPredicate(StringExpression stringExpression, String word) {
        return Optional.ofNullable(word)
                .filter(StringUtils::hasText)
                .map(stringExpression::contains);
    }

    public static Optional<BooleanExpression> optEnumEqPredicate(EnumPath enumPath, String word) {
        return Optional.ofNullable(word)
                .filter(StringUtils::hasText)
                .map(it -> enumPath.stringValue().eq(word));
    }

    public static Optional<BooleanExpression> optBooleanPredicate(StringExpression stringExpression, Boolean bol) {
        return Optional.ofNullable(bol)
                .map(it -> {
                    if (it) return stringExpression.isNotNull().or(stringExpression.isNotEmpty());
                    else return stringExpression.isNull().or(stringExpression.isEmpty());
                });
    }

    public static Optional<BooleanExpression> optIfTruePredicate(BooleanExpression booleanExpression,
                                                                 Boolean bool) {
        return Optional.ofNullable(bool)
                .filter(it -> bool)
                .map(it -> booleanExpression);
    }

    public static Optional<BooleanExpression> optIfFalsePredicate(BooleanExpression booleanExpression, Boolean bool) {
        return Optional.ofNullable(bool)
                .filter(it -> !bool)
                .map(it -> booleanExpression);
    }

    public static Optional<BooleanExpression> optBooleanPredicate(BooleanExpression booleanExpression, Boolean bool) {
        return Optional.ofNullable(bool)
                .map(booleanExpression::eq);
    }

    public static Optional<BooleanExpression> optContainsIgnoreCasePredicate(StringExpression stringExpression, String word) {
        return Optional.ofNullable(word)
                .filter(StringUtils::hasText)
                .map(stringExpression::containsIgnoreCase);
    }

    public static <T extends Number & Comparable<?>> Optional<BooleanExpression> optGtNumberPredicate(NumberExpression<T> numberExpression, T num) {
        return Optional.ofNullable(num)
                .map(it -> numberExpression.gt(num));
    }

    public static <T extends Number & Comparable<?>> Optional<BooleanExpression> optGoeNumberPredicate(NumberExpression<T> numberExpression, T num) {
        return Optional.ofNullable(num)
                .map(it -> numberExpression.goe(num));
    }
    public static <T extends Number & Comparable<?>> Optional<BooleanExpression> optLoeNumberPredicate(NumberExpression<T> numberExpression, T num) {
        return Optional.ofNullable(num)
                .map(it -> numberExpression.loe(num));
    }

    public static <T extends Number & Comparable<?>> Optional<BooleanExpression> optLtNumberPredicate(NumberExpression<T> numberExpression, T num) {
        return Optional.ofNullable(num)
                .map(it -> numberExpression.lt(num));
    }

    public static <T extends Number & Comparable<?>> Optional<BooleanExpression> optEqNumberPredicate(NumberExpression<T> numberExpression, T num) {
        return Optional.ofNullable(num)
                .map(it -> numberExpression.eq(num));
    }

    public static <T extends Comparable> Optional<BooleanExpression> optEqDatePredicate(TemporalExpression<T> temporalExpression, T temporal) {
        return Optional.ofNullable(temporal)
                .map(it -> temporalExpression.eq(temporal));
    }

    public static <T extends Comparable> Optional<BooleanExpression> optAfterDatePredicate(TemporalExpression<T> temporalExpression, T temporal) {
        return Optional.ofNullable(temporal)
                .map(it -> temporalExpression.after(temporal));
    }

    public static <T extends Comparable> Optional<BooleanExpression> optBeforeDatePredicate(TemporalExpression<T> temporalExpression, T temporal) {
        return Optional.ofNullable(temporal)
                .map(it -> temporalExpression.before(temporal));
    }

    public static <T extends Comparable> Optional<BooleanExpression> optBetweenDatePredicate(TemporalExpression<T> temporalExpression, T start, T end) {
        if (start == null || end == null) return Optional.empty();

        return Optional.of(temporalExpression.between(start, end));
    }

    public static <T extends Number & Comparable<?>> Optional<BooleanExpression> optNumberInListPredicate(NumberPath<T> numberExpression, Collection<T> collection) {
        if (CollectionUtils.isEmpty(collection)) return Optional.empty();

        return Optional.of(numberExpression.in(collection));
    }

    public static BooleanExpression containsIgnoreCasePredicate(StringExpression path, String word) {
        return path.containsIgnoreCase(word);
    }

    public static BooleanExpression startOrEndWithPredicate(StringExpression path, String word) {
        return path.toLowerCase().startsWith(word.toLowerCase())
                .or(path.toLowerCase().endsWith(word.toLowerCase()))
                .or(path.toLowerCase().eq(word.toLowerCase()));
    }

    public static <T> JPAQuery<T> pageableQuery(JPAQuery<T> query, Pageable pageable) {
        return query.limit(pageable.getPageSize())
                .offset((long) pageable.getPageNumber() * (long) pageable.getPageSize());
    }

    public static <T> JPAQuery<T> pageableQuery(JPAQuery<T> query, Pageable pageable, OrderSpecifier<?>... order) {
        return pageableQuery(query, pageable).orderBy(order);
    }
}
