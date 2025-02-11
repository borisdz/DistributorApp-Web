package mk.ukim.finki.db.distributorapp.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.model.dto.ProFormaDto;
import mk.ukim.finki.db.distributorapp.model.entities.ProForma;
import mk.ukim.finki.db.distributorapp.repository.ProFormaRepository;
import mk.ukim.finki.db.distributorapp.service.ProFormaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProFormaServiceImpl implements ProFormaService {
    private final ProFormaRepository proFormaRepository;


    private List<ProFormaDto> buildDto(List<ProForma> proFormas) {
        List<ProFormaDto> proFormaDtos = new ArrayList<>();
        for (ProForma pf : proFormas) {
            ProFormaDto proFormaDto = new ProFormaDto(
                    pf.getProFormaId(),
                    pf.getProFormaDeadline(),
                    pf.getProFormaDateCreated(),
                    pf.getProFormaStatus().getProFormaStatusId(),
                    pf.getProFormaStatus().getProFormaStatusName(),
                    pf.getOrder().getOrderId(),
                    pf.getOrder().getCustomer().getUserId(),
                    pf.getOrder().getCustomer().getCustomerCompanyName(),
                    pf.getOrder().getCustomer().getUserEmail(),
                    pf.getOrder().getCustomer().getUserMobile()
            );
            proFormaDtos.add(proFormaDto);
        }
        return proFormaDtos;
    }

    @Override
    @Transactional
    public List<ProFormaDto> getAllProForma() {
        return this.proFormaRepository.listAll();
    }

    @Override
    public ProFormaDto findProFormaById(Long id) {
        ProForma pf = this.proFormaRepository.findById(id).get();
        return new ProFormaDto(
                pf.getProFormaId(),
                pf.getProFormaDeadline(),
                pf.getProFormaDateCreated(),
                pf.getProFormaStatus().getProFormaStatusId(),
                pf.getProFormaStatus().getProFormaStatusName(),
                pf.getOrder().getOrderId(),
                pf.getOrder().getCustomer().getUserId(),
                pf.getOrder().getCustomer().getCustomerCompanyName(),
                pf.getOrder().getCustomer().getUserEmail(),
                pf.getOrder().getCustomer().getUserMobile()
        );
    }

    @Override
    public Integer create(ProFormaDto proFormaDto) {
        return this.proFormaRepository.create(
                proFormaDto.getPfDeadline(),
                proFormaDto.getPfDateCreated(),
                proFormaDto.getStatusId()
        );
    }

    @Override
    public Integer edit(ProFormaDto proFormaDto) {
        return this.proFormaRepository.edit(
                proFormaDto.getId(),
                proFormaDto.getPfDeadline(),
                proFormaDto.getPfDateCreated(),
                proFormaDto.getStatusId()
        );
    }

    @Override
    public void deleteById(Long id) {
        this.proFormaRepository.deleteById(id);
    }
}
