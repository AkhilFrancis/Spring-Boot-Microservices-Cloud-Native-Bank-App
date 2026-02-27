package com.akhilfrancis.accounts.service;

import com.akhilfrancis.accounts.dto.AccountsDto;
import com.akhilfrancis.accounts.dto.CardsDto;
import com.akhilfrancis.accounts.dto.CustomerDetailsDto;
import com.akhilfrancis.accounts.dto.LoansDto;
import com.akhilfrancis.accounts.entity.Accounts;
import com.akhilfrancis.accounts.entity.Customer;
import com.akhilfrancis.accounts.exception.ResourceNotFoundException;
import com.akhilfrancis.accounts.mapper.AccountsMapper;
import com.akhilfrancis.accounts.mapper.CustomerMapper;
import com.akhilfrancis.accounts.repository.AccountsRepository;
import com.akhilfrancis.accounts.repository.CustomerRepository;
import com.akhilfrancis.accounts.service.client.CardsFeignClient;
import com.akhilfrancis.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomersService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationID) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationID, mobileNumber);
        if(null != loansDtoResponseEntity) {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationID, mobileNumber);
        if(null != cardsDtoResponseEntity) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }

        return customerDetailsDto;
    }
}
