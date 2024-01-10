package com.spring.redduck.managebills.service.impl;

import com.spring.redduck.managebills.dto.CashDto;
import com.spring.redduck.managebills.entity.Cash;
import com.spring.redduck.managebills.mapper.CashMapper;
import com.spring.redduck.managebills.repository.CashRepository;
import com.spring.redduck.managebills.service.CashService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CashServiceImpl implements CashService {

    private CashRepository cashRepository;

    public CashServiceImpl(CashRepository cashRepository) {
        this.cashRepository = cashRepository;
    }

    @Override
    public List<CashDto> findAllCash() {
        List<Cash> cashList = cashRepository.findAll();
        return cashList.stream().map((cash) -> CashMapper.mapToCashDto(cash)).collect(Collectors.toList());
    }

    @Override
    public void createCash(CashDto cashDto) {
        cashRepository.save(CashMapper.mapToCash(cashDto));
    }

    @Override
    public CashDto findCashById(Long cashId) {
        Cash cash = cashRepository.findById(cashId).get();
        return CashMapper.mapToCashDto(cash);
    }

    @Override
    public void deleteCash(Long cashId) {
        cashRepository.deleteById(cashId);
    }

    @Override
    public void updateCash(CashDto cashDto) {
        Cash cash = CashMapper.mapToCash(cashDto);
        cashRepository.save(cash);
    }

    @Override
    public List<CashDto> findCashByMonth(Long month) {
        List<Cash> cashList = cashRepository.findCashByMonth(month);
        return cashList.stream().map((cash) -> CashMapper.mapToCashDto(cash)).collect(Collectors.toList());
    }

    @Override
    public Optional<CashDto> findCodiasaCashByMonth(Long month) {
        Cash cash = cashRepository.findCodiasaCashByMonth(month);
        if(cash == null)
            return Optional.empty();
        else
            return Optional.of(CashMapper.mapToCashDto(cash));
    }
}
