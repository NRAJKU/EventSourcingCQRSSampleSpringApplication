package com.fictbank.account.cmd.api.commands;

import com.fictbank.account.common.dto.AccountType;
import com.fictbank.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}