package com.akhilfrancis.accounts.service;

import com.akhilfrancis.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationID);
}
