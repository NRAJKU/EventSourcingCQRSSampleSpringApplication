package com.fictbank.account.query.api.queries;

import com.fictbank.account.query.api.dto.EqualityType;
import com.fictbank.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountWithBalanceQuery extends BaseQuery {
    private EqualityType equalityType;
    private double balance;
}
