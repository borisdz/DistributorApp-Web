package mk.ukim.finki.db.distributorapp.service.impl;

import mk.ukim.finki.db.distributorapp.model.Orders;
import mk.ukim.finki.db.distributorapp.model.ProForma;
import mk.ukim.finki.db.distributorapp.model.statuses.Pro_Forma_Status;
import mk.ukim.finki.db.distributorapp.repository.ProFormaRepository;
import mk.ukim.finki.db.distributorapp.service.ProFormaService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProFormaServiceImpl implements ProFormaService {
    private final ProFormaRepository proFormaRepository;

    public ProFormaServiceImpl(ProFormaRepository proFormaRepository) {
        this.proFormaRepository = proFormaRepository;
    }

    @Override
    public List<ProForma> getAllPro_Forma() {
        return this.proFormaRepository.listAll();
    }

    @Override
    public Optional<ProForma> findProFormaById(Long id) {
        return this.proFormaRepository.findById(id);
    }

    @Override
    public Optional<ProForma> create(LocalDate pf_deadline, LocalDate pf_create_date, Pro_Forma_Status pf_status, Orders order) {
        return this.proFormaRepository.create(
                pf_deadline,
                pf_create_date,
                pf_status.getPro_forma_status_id(),
                order.getOrderId()
        );
    }

    @Override
    public Optional<ProForma> edit(Long id, LocalDate pf_deadline, LocalDate pf_create_date, Pro_Forma_Status pf_status, Orders order) {
        return this.proFormaRepository.edit(
                id,
                pf_deadline,
                pf_create_date,
                pf_status.getPro_forma_status_id(),
                order.getOrderId()
        );
    }

    @Override
    public void delete(Long id) {
        this.proFormaRepository.deleteById(id);
    }
}
