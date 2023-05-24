package com.fictbank.cqrs.core.infrastructure;

import com.fictbank.cqrs.core.domain.BaseEntity;
import com.fictbank.cqrs.core.queries.BaseQuery;
import com.fictbank.cqrs.core.queries.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    <U extends BaseEntity> List<U> send(BaseQuery query);
}
