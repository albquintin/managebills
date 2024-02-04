package com.spring.redduck.managebills.service;

import com.spring.redduck.managebills.dto.CashDto;

import java.util.List;
import java.util.Optional;

public interface CashService {
    List<CashDto> findAllCash();
    void createCash(CashDto cashDto);
    CashDto findCashById(Long cashId);
    void deleteCash(Long cashId);
    void updateCash(CashDto cashDto);
    List<CashDto> findCashByMonth(Long month);
    Optional<CashDto> findCodiasaCashByMonth(Long month);
    List<CashDto> findCashOfCurrentYear();
    List<CashDto> findOldCash();
}
