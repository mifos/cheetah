package org.mifos.user.service;

import java.util.List;

import org.mifos.user.repository.OfficeDao;

public interface OfficeService {

    List<OfficeDto> getAll();

    OfficeDto getHeadOffice();

    void setOfficeDao(OfficeDao officeDao);

}