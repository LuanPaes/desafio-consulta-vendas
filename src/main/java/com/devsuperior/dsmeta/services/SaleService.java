package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	private SaleSummaryDTO getSummary;

	private SaleReportDTO getReport;
	
	public SaleMinDTO findById(Long id) {
		Sale entity = repository.findById(id)
	.orElseThrow(()-> new RuntimeException("Venda não encontrada!"));
		return new SaleMinDTO(entity);
	}

	public List<SaleSummaryDTO>getSummary(String minDateStr, String maxDateStr){
		LocalDate today = LocalDate.now();

		LocalDate maxDate = (maxDateStr == null || maxDateStr.isEmpty())
				? today : LocalDate.parse(maxDateStr, DateTimeFormatter.ISO_DATE);

		LocalDate minDate = (minDateStr == null || minDateStr.isEmpty())
				? maxDate.minusYears(1) : LocalDate.parse(minDateStr,DateTimeFormatter.ISO_DATE);

		return repository.sumary(minDate, maxDate);
	}

	public Page<SaleReportDTO>getReport(String minDateStr, String maxDateStr, String name, Pageable pageable){

		LocalDate today = LocalDate.now();
		
		LocalDate maxDate = (maxDateStr == null || maxDateStr.isEmpty())
				? today : LocalDate.parse(maxDateStr, DateTimeFormatter.ISO_DATE);
		
		LocalDate minDate = (minDateStr == null || minDateStr.isEmpty())
				? maxDate.minusYears(1) : LocalDate.parse(minDateStr, DateTimeFormatter.ISO_DATE);
		
		String nameFilter = (name == null) ? "" : name;
		
		return repository.report(minDate, maxDate, nameFilter, pageable);
	}
}
