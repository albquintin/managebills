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
    public List<CashDto> findCashByMonth(Long month, Long year) {
        List<Cash> cashList = cashRepository.findCashByMonth(month, year);
        return cashList.stream().map((cash) -> CashMapper.mapToCashDto(cash)).collect(Collectors.toList());
    }

    @Override
    public Optional<CashDto> findCodiasaCashByMonth(Long month, Long year) {
        Cash cash = cashRepository.findCodiasaCashByMonth(month, year);
        if(cash == null)
            return Optional.empty();
        else
            return Optional.of(CashMapper.mapToCashDto(cash));
    }

    @Override
    public List<CashDto> findCashOfCurrentYear() {
        List<Cash> cashList = cashRepository.findCashOfCurrentYear();
        return cashList.stream().map((cash) -> CashMapper.mapToCashDto(cash)).collect(Collectors.toList());
    }

    @Override
    public List<CashDto> findOldCash() {
        List<Cash> cashList = cashRepository.findOldCash();
        return cashList.stream().map((cash) -> CashMapper.mapToCashDto(cash)).collect(Collectors.toList());
    }

    @Override
    public List<CashDto> findGraphicData() {
        List<Cash> cashList = cashRepository.findGraphicData();
        return cashList.stream().map((cash) -> CashMapper.mapToCashDto(cash)).collect(Collectors.toList());
    }
}
