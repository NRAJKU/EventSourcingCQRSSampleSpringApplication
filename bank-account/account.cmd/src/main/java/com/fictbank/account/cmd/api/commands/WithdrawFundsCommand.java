package com.fictbank.account.cmd.api.commands;

import com.fictbank.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class WithdrawFundsCommand extends BaseCommand {
    private double amount;
}
